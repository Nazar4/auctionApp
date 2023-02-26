package nulp.pz.auction.service.impl;

import lombok.RequiredArgsConstructor;
import nulp.pz.auction.domain.MoneyAccount;
import nulp.pz.auction.domain.Product;
import nulp.pz.auction.domain.Statement;
import nulp.pz.auction.domain.User;
import nulp.pz.auction.repository.MoneyAccountRepository;
import nulp.pz.auction.repository.ProductRepository;
import nulp.pz.auction.repository.StatementRepository;
import nulp.pz.auction.repository.UserRepository;
import nulp.pz.auction.service.StatementService;
import nulp.pz.auction.service.dto.StatementDTO;
import nulp.pz.auction.service.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StatementServiceImpl implements StatementService {

    private final StatementRepository statementRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final MoneyAccountRepository moneyAccountRepository;

    @Override
    public Statement submitStatement(final Statement statement, final User user) {
        final Product product = statement.getProduct();
        product.setFrequency(product.getFrequency() + 1);
        productRepository.save(product);
        statement.setProduct(product);
        statement.setOwner(user);
        final Set<Statement> userStatements = user.getStatements();
        statementRepository.save(statement);
        userStatements.add(statement);
        user.setStatements(userStatements);
        userRepository.save(user);
        return statementRepository.save(statement);
    }

    @Override
    public void cancelStatement(final Long id) {
        if (!statementRepository.existsById(id)) {
            throw new EntityNotFoundException("Statement with id: " + id + " is not present");
        }
        final Optional<Statement> byId = statementRepository.findById(id);
        if (byId.isPresent()) {
            final Statement statement = byId.get();
            final User owner = statement.getOwner();
            final Set<Statement> statements = owner.getStatements();
            statements.remove(statement);
            owner.setStatements(statements);
            userRepository.save(owner);
        } else {
            throw new EntityNotFoundException("Could not find statement with id " + id);
        }
        statementRepository.deleteById(id);
    }

    @Override
    public boolean verifyUserIsNotRegisteringForTheSameProduct(final StatementDTO statementDTO) {
        final User user = userRepository.findByUsername(statementDTO.getUsername()).orElseThrow((
                () -> new EntityNotFoundException("Could not find user with userName " + statementDTO.getUsername())));
        final Product product = productRepository.findByName(statementDTO.getProductName()).orElseThrow(
                () -> new EntityNotFoundException("Could not find product with name " + statementDTO.getProductName()));
        return statementRepository.existsByOwnerIdAndProductId(user.getId(), product.getId());
    }

    @Override
    public void conductAuction() {
        statementRepository.findAll().stream()
                .collect(Collectors.toMap(Statement::getProduct, Function.identity(), (s1, s2) -> (s1.getSum().doubleValue() > s2.getSum().doubleValue()) ? s1 : s2))
                .forEach((product, statement) -> {
                    final User owner = statement.getOwner();
                    final BigDecimal sum = statement.getSum();
                    final MoneyAccount moneyAccount = owner.getMoneyAccount();
                    final BigDecimal balance = moneyAccount.getBalance();
                    moneyAccount.setBalance(balance.subtract(sum));
                    moneyAccountRepository.save(moneyAccount);
                    userRepository.save(owner);
                    product.setOwner(owner);
                    productRepository.save(product);
                });
    }
}
