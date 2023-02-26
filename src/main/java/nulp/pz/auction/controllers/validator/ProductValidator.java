package nulp.pz.auction.controllers.validator;

import nulp.pz.auction.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "Not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "Not empty");
    }
}
