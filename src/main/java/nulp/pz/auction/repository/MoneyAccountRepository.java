package nulp.pz.auction.repository;

import nulp.pz.auction.domain.MoneyAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyAccountRepository extends JpaRepository<MoneyAccount, Long> {
}
