package nulp.pz.auction.service.mapper;

import nulp.pz.auction.domain.Product;
import nulp.pz.auction.service.dto.ProductDTO;

public class ProductMapper implements Mapper<Product, ProductDTO> {

    @Override
    public Product toEntity(final ProductDTO productDTO) {
        final Product product = new Product();
        product.setName(productDTO.getName());
        product.setAuthor(productDTO.getAuthor());
        product.setGenre(productDTO.getGenre());
        product.setPrice(productDTO.getBottomRange());
        return product;
    }
}
