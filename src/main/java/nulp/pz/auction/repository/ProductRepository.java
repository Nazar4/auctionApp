package nulp.pz.auction.repository;

import nulp.pz.auction.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds Product by name
     * @param name of the product
     * @return product if present
     */
    Optional<Product> findByName(final String name);

    /**
     * Finds all products without owner
     * @return list of products
     */
    List<Product> findAllByOwnerIsNull();
}
