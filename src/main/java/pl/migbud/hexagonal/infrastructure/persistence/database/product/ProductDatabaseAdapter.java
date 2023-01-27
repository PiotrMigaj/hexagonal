package pl.migbud.hexagonal.infrastructure.persistence.database.product;

import lombok.RequiredArgsConstructor;
import pl.migbud.hexagonal.domain.product.model.Product;
import pl.migbud.hexagonal.domain.product.port.out.ProductRepositoryPort;
import pl.migbud.hexagonal.infrastructure.persistence.database.product.entity.ProductEntity;
import pl.migbud.hexagonal.infrastructure.persistence.database.product.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProductDatabaseAdapter implements ProductRepositoryPort {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream()
                .map(productEntity -> new Product(
                        productEntity.getId(),
                        productEntity.getName(),
                        productEntity.getDescription()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Product save(Product product) {
        ProductEntity result = productRepository.save(new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription()
        ));
        return new Product(
                result.getId(),
                result.getName(),
                result.getDescription()
        );
    }
}
