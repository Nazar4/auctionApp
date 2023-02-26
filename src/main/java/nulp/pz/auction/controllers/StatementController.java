package nulp.pz.auction.controllers;

import nulp.pz.auction.domain.Statement;
import nulp.pz.auction.domain.User;
import nulp.pz.auction.service.ProductService;
import nulp.pz.auction.service.StatementService;
import nulp.pz.auction.service.UserService;
import nulp.pz.auction.service.dto.StatementDTO;
import nulp.pz.auction.service.mapper.FactoryMapper;
import nulp.pz.auction.service.mapper.Mapper;
import nulp.pz.auction.service.mapper.MapperType;
import nulp.pz.auction.service.mapper.StatementMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class StatementController {

    private final StatementService statementService;
    private final FactoryMapper statementMapper;
    private final UserService userService;

    public StatementController(final StatementService statementService, final FactoryMapper statementMapper,
                               final UserService userService) {
        this.statementService = statementService;
        this.statementMapper = statementMapper;
        this.userService = userService;
    }

    @PostMapping("/contest-register")
    public String registerForContest(@Valid @ModelAttribute("statementForm") final StatementDTO statementDTO,
                                     final HttpSession session, final Model model) {
        statementDTO.setUsername((String) session.getAttribute("username"));
        statementDTO.setUserMoneyAccountName((String) session.getAttribute("credentials"));

        if (!userService.isUserAbleToPay(statementDTO.getUsername()) || statementService.verifyUserIsNotRegisteringForTheSameProduct(statementDTO)) {
            model.addAttribute("unable",
                    "You are not able to register either because you do not have enough money or because you have already registered");
            return "statement";
        }
        final User user = userService.getByUsername((String) session.getAttribute("username"));
        final StatementMapper mapper = (StatementMapper) statementMapper.createMapper(MapperType.STATEMENT);
        final Statement submitStatement = statementService.submitStatement(mapper.toEntity(statementDTO), user);
        session.setAttribute("statement", submitStatement);
        model.addAttribute("statement", submitStatement);
        return "home";
    }

    @GetMapping("/statements")
    public String getStatementsForCurrentUser(final Model model, final HttpSession session) {
        final User user = userService.getByUsername((String) session.getAttribute("username"));
        model.addAttribute("statements", user.getStatements());
        return "my-statements";
    }

    @PostMapping("/delete-statement")
    public String deleteStatement(@RequestParam final Long id) {
        statementService.cancelStatement(id);
        return "my-statements";
    }

    @GetMapping("/auction")
    public String conductAuction() {
        statementService.conductAuction();
        return "home";
    }
}
