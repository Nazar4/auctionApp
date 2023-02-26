package nulp.pz.auction.service.impl;

import lombok.RequiredArgsConstructor;
import nulp.pz.auction.domain.MoneyAccount;
import nulp.pz.auction.domain.Statement;
import nulp.pz.auction.domain.User;
import nulp.pz.auction.repository.MoneyAccountRepository;
import nulp.pz.auction.repository.StatementRepository;
import nulp.pz.auction.repository.UserRepository;
import nulp.pz.auction.service.UserService;
import nulp.pz.auction.service.exceptions.EntityNotFoundException;
import nulp.pz.auction.service.exceptions.UserAlreadyExistException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StatementRepository statementRepository;
    private final MoneyAccountRepository moneyAccountRepository;

    @Override
    public boolean isUserAbleToPay(final String username) {
        final User user = userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("Could not find user with userName " + username));

        final BigDecimal sumOfAllStatements = user.getStatements().stream()
                    .map(Statement::getSum)
                    .reduce(BigDecimal.ZERO, (aDouble, bigDecimal) -> BigDecimal.valueOf(aDouble.doubleValue() + bigDecimal.doubleValue()));

        return user.getMoneyAccount().getBalance().doubleValue() > sumOfAllStatements.doubleValue();
    }

    @Override
    public User findByGreatestSumForStatement() {
        final BigDecimal maxSum = statementRepository.findAll().stream()
                .map(Statement::getSum)
                .max(Comparator.comparingDouble(BigDecimal::doubleValue))
                .orElseThrow((() -> new EntityNotFoundException("Could not find statement with greatest sum")));

        return userRepository.findUserByMoneyAccountBalance(maxSum)
                .orElseThrow((() -> new EntityNotFoundException("Could not find user with sum " + maxSum)));
    }

    @Override
    public void registerNewUser(final User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistException();
        }
        final MoneyAccount moneyAccount = new MoneyAccount();
        moneyAccount.setBalance(new BigDecimal(0));
        moneyAccount.setName(user.getUsername() + "_" + (ThreadLocalRandom.current().nextInt(900000) + 100000));
        moneyAccountRepository.save(moneyAccount);
        user.setMoneyAccount(moneyAccount);
        userRepository.save(user);
    }

    @Override
    public User findByUsernameAndPassword(final String userName, final String password) throws EntityNotFoundException {
        return userRepository.findByUsernameAndPassword(userName, password).orElse(null);
    }

    @Override
    public User getByUsername(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow((() -> new EntityNotFoundException("Could not find user with userName " + username)));
    }

    @Override
    public void topUpBalance(final User user, final BigDecimal value) {
        final MoneyAccount userMoneyAccount = user.getMoneyAccount();
        final BigDecimal balance = userMoneyAccount.getBalance();
        userMoneyAccount.setBalance(balance.add(value));
        moneyAccountRepository.save(userMoneyAccount);
        user.setMoneyAccount(userMoneyAccount);
        userRepository.save(user);
    }
}
