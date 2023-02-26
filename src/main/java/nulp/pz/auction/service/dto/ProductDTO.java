package nulp.pz.auction.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nulp.pz.auction.domain.Genre;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductDTO {

    private String name;
    private Genre genre;
    private String author;
    private BigDecimal bottomRange;
}