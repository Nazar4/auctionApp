package nulp.pz.auction.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@ToString

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String author;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private BigDecimal price;

    @OneToOne
    private User owner;

    private int frequency;
}
