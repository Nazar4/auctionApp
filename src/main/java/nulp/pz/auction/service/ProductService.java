package nulp.pz.auction.service;

import nulp.pz.auction.domain.Product;
import nulp.pz.auction.service.mapper.SortParameter;

import java.util.List;

public interface ProductService {

    /**
     * Saves product to database
     * @param product product to be saved
     */
    void saveProduct(final Product product);

    /**
     * Sorts products depending on sort parameter provided
     * @param parameter sort parameter
     * @return list of sorted products
     */
    List<Product> sortDependingOnTypeOfSort(final SortParameter parameter);

    /**
     * Gets all products fulfilling specific criteria
     * @param product product to extract criteria
     * @return list of products
     */
    List<Product> getAllBySpecificCriteria(final Product product);

    /**
     * Gets all products
     * @return list of products
     */
    List<Product> getAll();
}
