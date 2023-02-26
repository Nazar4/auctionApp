package nulp.pz.auction.controllers;

import nulp.pz.auction.controllers.validator.UserValidator;
import nulp.pz.auction.domain.Statement;
import nulp.pz.auction.domain.User;
import nulp.pz.auction.service.ProductService;
import nulp.pz.auction.service.StatementService;
import nulp.pz.auction.service.UserService;
import nulp.pz.auction.service.dto.StatementDTO;
import nulp.pz.auction.service.mapper.StatementMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

@Controller
public class UserController {

    private final Validator userValidator;
    private final UserService userService;

    public UserController(final UserValidator userValidator, final UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userForm") final User user, final BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.registerNewUser(user);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password,
                        final HttpSession session, final Model model) {
        final User user = userService.findByUsernameAndPassword(username, password);
        if (Objects.isNull(user)) {
            model.addAttribute("credentialsError", "Wrong username or password");
            return "login";
        }
        session.setAttribute("username", username);
        session.setAttribute("balance", user.getMoneyAccount().getBalance());
        session.setAttribute("authenticated", true);
        return "home";
    }

    @PostMapping("/top-up")
    public String topUp(@RequestParam("value") final BigDecimal value, final HttpSession session, final Model model) {
        if (value.doubleValue() < 0) {
            model.addAttribute("wrongValue", "You can not top up balance with negative value");
            return "top-up";
        }
        final User user = userService.getByUsername((String) session.getAttribute("username"));
        userService.topUpBalance(user, value);
        session.setAttribute("balance", user.getMoneyAccount().getBalance());
        return "home";
    }

    @GetMapping("/greatest-sum")
    public String greatestSum(final Model model) {
        final User user = userService.findByGreatestSumForStatement();
        model.addAttribute("sum", user);
        return "sum";
    }
}
