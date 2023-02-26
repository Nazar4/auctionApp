package nulp.pz.auction.service;

import nulp.pz.auction.domain.Statement;
import nulp.pz.auction.domain.User;
import nulp.pz.auction.service.dto.StatementDTO;

public interface StatementService {

    /**
     * Saves statement into database for specific user
     * @param statement statement to be saved
     * @param user owner
     * @return saved statement
     */
    Statement submitStatement(final Statement statement, final User user);

    /**
     * cancels statement for current user
     * @param id id of statement
     */
    void cancelStatement(final Long id);

    /**
     * Verifies that user is not registered for the same product
     * @param statementDTO statement DTO representing statement
     * @return true if registered otherwise false
     */
    boolean verifyUserIsNotRegisteringForTheSameProduct(final StatementDTO statementDTO);

    /**
     * Conducts auction
     */
    void conductAuction();
}
