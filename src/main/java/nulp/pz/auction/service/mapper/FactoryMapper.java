package nulp.pz.auction.service.mapper;

import nulp.pz.auction.repository.ProductRepository;
import nulp.pz.auction.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class FactoryMapper {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public FactoryMapper(final UserRepository userRepository, final ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Mapper<?, ?> createMapper(final MapperType type) {
        if (MapperType.PRODUCT.equals(type)) {
            return new ProductMapper();
        } else if (MapperType.STATEMENT.equals(type)) {
            return new StatementMapper(userRepository, productRepository);
        } else {
            return null;
        }
    }
}
