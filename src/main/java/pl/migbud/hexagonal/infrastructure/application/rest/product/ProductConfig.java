package pl.migbud.hexagonal.infrastructure.application.rest.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.migbud.hexagonal.domain.common.emailnotification.EmailNotificationPort;
import pl.migbud.hexagonal.domain.product.adapter.ProductServiceAdapter;
import pl.migbud.hexagonal.domain.product.port.in.ProductServicePort;
import pl.migbud.hexagonal.domain.product.port.out.ProductRepositoryPort;
import pl.migbud.hexagonal.domain.product.service.ProductService;
import pl.migbud.hexagonal.infrastructure.notification.inmemory.email.EmailNotificationAdapter;
import pl.migbud.hexagonal.infrastructure.persistence.database.product.ProductDatabaseAdapter;
import pl.migbud.hexagonal.infrastructure.persistence.database.product.repository.ProductRepository;
import pl.migbud.hexagonal.infrastructure.persistence.inmemory.product.ProductInMemoryAdapter;

@Configuration
class ProductConfig {

    @Bean
    ProductServicePort productServicePort(ProductRepositoryPort productRepositoryPort,
                                          EmailNotificationPort emailNotificationPort){
        return new ProductServiceAdapter(
                new ProductService(productRepositoryPort,emailNotificationPort)
        );
    }
}
