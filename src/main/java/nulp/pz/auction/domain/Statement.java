package nulp.pz.auction.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
public class Statement {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private User owner;

    @OneToOne
    private Product product;

    private BigDecimal sum;

}
