package nulp.pz.auction.service.mapper;

import nulp.pz.auction.domain.Statement;
import nulp.pz.auction.repository.ProductRepository;
import nulp.pz.auction.repository.UserRepository;
import nulp.pz.auction.service.dto.StatementDTO;

public class StatementMapper implements Mapper<Statement, StatementDTO> {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public StatementMapper(final UserRepository userRepository, final ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Statement toEntity(final StatementDTO statementDTO) {
        final Statement statement = new Statement();
        statement.setProduct(productRepository.findByName(statementDTO.getProductName()).get());
        statement.setOwner(userRepository.findByUsername(statementDTO.getUsername()).get());
        statement.setSum(statementDTO.getSum());
        return statement;
    }
}
