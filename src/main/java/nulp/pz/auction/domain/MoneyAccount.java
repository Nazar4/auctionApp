package nulp.pz.auction.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
public class MoneyAccount {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal balance;

    private String name;

}
