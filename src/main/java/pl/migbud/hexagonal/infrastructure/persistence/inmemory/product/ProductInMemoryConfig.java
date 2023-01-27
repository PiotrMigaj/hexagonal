package pl.migbud.hexagonal.infrastructure.persistence.inmemory.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.migbud.hexagonal.domain.product.port.out.ProductRepositoryPort;

@Configuration
@Profile("mem")
class ProductInMemoryConfig {
    @Bean
    ProductRepositoryPort productRepositoryPort(){
        return new ProductInMemoryAdapter();
    }
}
