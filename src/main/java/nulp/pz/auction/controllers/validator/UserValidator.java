package nulp.pz.auction.controllers.validator;

import nulp.pz.auction.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private static final String EMAIL_PATTERN = "^(.+)@(.+)$";

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty");

        final boolean result = Pattern.compile(EMAIL_PATTERN).matcher(user.getEmail()).matches();
        if (!result) {
            errors.rejectValue("email", "email");
        }

        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "password", "Password length has to be 6 or more characters");
        }
    }
}
