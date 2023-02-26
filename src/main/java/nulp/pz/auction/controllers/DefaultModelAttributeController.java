package nulp.pz.auction.controllers;

import nulp.pz.auction.domain.Genre;
import nulp.pz.auction.domain.Product;
import nulp.pz.auction.domain.User;
import nulp.pz.auction.service.dto.ProductDTO;
import nulp.pz.auction.service.dto.StatementDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class DefaultModelAttributeController {

    @ModelAttribute("generItems")
    public Set<String> getGenderItems() {
        final Set<String> genres = new HashSet<>();
        for (final Genre genre : Genre.values()) {
            genres.add(genre.toString());
        }
        return genres;
    }

    @ModelAttribute("productForm")
    public Product getProduct() {
        return new Product();
    }

    @ModelAttribute("userForm")
    public User getUser() {
        return new User();
    }

    @ModelAttribute("statementForm")
    public StatementDTO getStatement() {
        return new StatementDTO();
    }

    @ModelAttribute("productDTOForm")
    public ProductDTO getProductDTO() {
        return new ProductDTO();
    }
}
