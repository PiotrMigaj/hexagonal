package pl.migbud.hexagonal.infrastructure.persistence.database.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.migbud.hexagonal.domain.product.port.out.ProductRepositoryPort;
import pl.migbud.hexagonal.infrastructure.persistence.database.product.repository.ProductRepository;

@Configuration
@Profile("!mem")
class ProductDatabaseConfig {

    @Bean
    ProductRepositoryPort productRepositoryPort(ProductRepository productRepository){
        return new ProductDatabaseAdapter(productRepository);
    }
}
