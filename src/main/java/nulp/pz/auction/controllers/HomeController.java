package nulp.pz.auction.controllers;

import nulp.pz.auction.domain.Product;
import nulp.pz.auction.domain.User;
import nulp.pz.auction.service.ProductService;
import nulp.pz.auction.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final UserService userService;
    private final ProductService productService;

    public HomeController(final UserService userService, final ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @GetMapping("/add-product")
    public String addProduct() {
        return "add-product";
    }

    @GetMapping("/top-up")
    public String topUp() {
        return "top-up";
    }

    @GetMapping("/contest-register")
    public String statement(final Model model, final HttpSession session) {
        final User user = userService.getByUsername((String) session.getAttribute("username"));
        model.addAttribute("credentials", user.getMoneyAccount().getName());
        session.setAttribute("credentials", user.getMoneyAccount().getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("productNames", productService.getAll().stream().map(Product::getName).collect(Collectors.toList()));
        return "statement";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }
}
