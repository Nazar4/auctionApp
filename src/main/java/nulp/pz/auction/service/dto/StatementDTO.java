package nulp.pz.auction.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class StatementDTO {

    private BigDecimal sum;
    private String productName;
    private String userMoneyAccountName;
    private String username;
}
