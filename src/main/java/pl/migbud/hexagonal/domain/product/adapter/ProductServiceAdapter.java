package pl.migbud.hexagonal.domain.product.adapter;

import lombok.RequiredArgsConstructor;
import pl.migbud.hexagonal.domain.product.model.Product;
import pl.migbud.hexagonal.domain.product.port.in.ProductServicePort;
import pl.migbud.hexagonal.domain.product.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
public class ProductServiceAdapter implements ProductServicePort {

    private final ProductService productService;

    @Override
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @Override
    public Product addProduct(Product product) {
        return productService.addProduct(product);
    }
}
