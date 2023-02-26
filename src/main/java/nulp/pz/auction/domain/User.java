package nulp.pz.auction.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @Min(6)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne
    private MoneyAccount moneyAccount;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Statement> statements = new HashSet<>();
}