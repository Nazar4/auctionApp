package nulp.pz.auction.repository;

import nulp.pz.auction.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds user by username
     * @param username username of user
     * @return user if present
     */
    Optional<User> findByUsername(final String username);

    /**
     * Finds user by username and password
     * @param userName username of user
     * @param password password of user
     * @return user if present
     */
    Optional<User> findByUsernameAndPassword(final String userName, final String password);

    /**
     * Verifies that user exists by email
     * @param email user email
     * @return true if exists otherwise false
     */
    boolean existsByEmail(final String email);

    /**
     * Finds user by balance on money account
     * @param sum expected sum on money account
     * @return user if present
     */
    @Query("select s.owner from User as u join Statement as s on s.owner = u where s.sum = ?1")
    Optional<User> findUserByMoneyAccountBalance(final BigDecimal sum);
}
