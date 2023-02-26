package nulp.pz.auction.service;

import nulp.pz.auction.domain.User;
import nulp.pz.auction.service.exceptions.EntityNotFoundException;

import java.math.BigDecimal;

public interface UserService {

    /**
     * Verifies that user is able to submit statement
     * @param username username of user
     * @return true if able otherwise false
     */
    boolean isUserAbleToPay(final String username);

    /**
     * Finds user by greatest sum for statement if one exists
     * @return found user
     */
    User findByGreatestSumForStatement();

    /**
     * Saves user in database
     * @param user user to be saved
     */
    void registerNewUser(final User user);

    /**
     * Finds user by username and password
     * @param userName username of user
     * @param password password of user
     * @return found user
     */
    User findByUsernameAndPassword(final String userName, final String password);

    /**
     * Gets user by username
     * @param username username of user
     * @return found user
     */
    User getByUsername(final String username);

    /**
     * Tops up balance for user
     * @param user user to top up balance
     * @param value value to top up
     */
    void topUpBalance(final User user, final BigDecimal value);
}
