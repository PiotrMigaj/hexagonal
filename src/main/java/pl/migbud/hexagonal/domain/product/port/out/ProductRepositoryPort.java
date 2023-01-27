package pl.migbud.hexagonal.domain.product.port.out;

import pl.migbud.hexagonal.domain.product.model.Product;

import java.util.List;

public interface ProductRepositoryPort {
    List<Product> findAll();

    Product save(Product product);
}
