package nulp.pz.auction.service.impl;

import lombok.RequiredArgsConstructor;
import nulp.pz.auction.domain.Product;
import nulp.pz.auction.repository.ProductRepository;
import nulp.pz.auction.service.ProductService;
import nulp.pz.auction.service.mapper.SortParameter;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void saveProduct(final Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> sortDependingOnTypeOfSort(final SortParameter parameter) {
        final List<Product> products;
        if (parameter.toString().equals(SortParameter.GENRE.toString())) {
            products = getAllByGenreSorted();
        } else if (parameter.toString().equals(SortParameter.FREQUENCY.toString())) {
            products = getAllByFrequencySorted();
        } else {
            products = getAllByAuthorSorted();
        }
        return products;
    }

    private List<Product> getAllByGenreSorted() {
        return productRepository.findAllByOwnerIsNull().stream()
                .sorted(Comparator.comparing(p -> p.getGenre().toString()))
                .collect(Collectors.toList());
    }

    private List<Product> getAllByAuthorSorted() {
        return productRepository.findAllByOwnerIsNull().stream()
                .sorted(Comparator.comparing(Product::getAuthor))
                .collect(Collectors.toList());
    }

    private List<Product> getAllByFrequencySorted() {
        return productRepository.findAllByOwnerIsNull().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getFrequency(), p1.getFrequency()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getAllBySpecificCriteria(final Product product) {
        return productRepository.findAllByOwnerIsNull().stream()
                .filter(p -> Objects.nonNull(product.getName()) ? p.getName().contains(product.getName()) : Objects.nonNull(p.getName()))
                .filter(p -> Objects.nonNull(product.getGenre()) ? p.getGenre().toString().equals(product.getGenre().toString()) : Objects.nonNull(p.getGenre()))
                .filter(p -> Objects.nonNull(product.getAuthor()) ? p.getAuthor().contains(product.getAuthor()) : Objects.nonNull(p.getAuthor()))
                .filter(p -> Objects.nonNull(product.getPrice()) ? (p.getPrice().doubleValue() > product.getPrice().doubleValue())  : Objects.nonNull(p.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAllByOwnerIsNull();
    }
}
