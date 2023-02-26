package nulp.pz.auction.controllers;

import nulp.pz.auction.domain.Product;
import nulp.pz.auction.service.ProductService;
import nulp.pz.auction.service.dto.ProductDTO;
import nulp.pz.auction.service.mapper.FactoryMapper;
import nulp.pz.auction.service.mapper.MapperType;
import nulp.pz.auction.service.mapper.ProductMapper;
import nulp.pz.auction.service.mapper.SortParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    private final Validator productValidator;
    private final ProductService productService;
    private final FactoryMapper productMapper;

    public ProductController(final Validator productValidator, final ProductService productService,
                             final FactoryMapper productMapper) {
        this.productValidator = productValidator;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("/add-product")
    public String addProduct(@Valid @ModelAttribute("productForm") final Product product, final BindingResult bindingResult) {
        productValidator.validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        productService.saveProduct(product);
        return "home";
    }

    @GetMapping("/get-product")
    public String sortProducts(@RequestParam(value = "parameter", defaultValue = "AUTHOR") final SortParameter parameter, final Model model) {
        final List<Product> products = productService.sortDependingOnTypeOfSort(parameter);
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/search-product")
    public String searchProduct(@Valid @ModelAttribute("productDTOForm") final ProductDTO productDTO, final Model model) {
        final ProductMapper mapper = (ProductMapper) productMapper.createMapper(MapperType.PRODUCT);
        final Product product = mapper.toEntity(productDTO);
        final List<Product> products = productService.getAllBySpecificCriteria(product);
        model.addAttribute("products", products);
        return "search";
    }
}
