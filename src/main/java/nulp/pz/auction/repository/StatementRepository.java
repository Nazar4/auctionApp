package nulp.pz.auction.repository;

import nulp.pz.auction.domain.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {

    /**
     * Verifies that statement exists by owner id and product id
     * @param ownerId the owner id
     * @param productId the product id
     * @return true if exists otherwise false
     */
    boolean existsByOwnerIdAndProductId(final Long ownerId, final Long productId);
}
