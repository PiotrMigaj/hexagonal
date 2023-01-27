package pl.migbud.hexagonal.domain.product.adapter;

import org.junit.jupiter.api.Test;
import pl.migbud.hexagonal.domain.common.emailnotification.EmailNotificationPort;
import pl.migbud.hexagonal.domain.product.model.Product;
import pl.migbud.hexagonal.domain.product.port.in.ProductServicePort;
import pl.migbud.hexagonal.domain.product.port.out.ProductRepositoryPort;
import pl.migbud.hexagonal.domain.product.service.ProductService;
import pl.migbud.hexagonal.infrastructure.notification.inmemory.email.EmailNotificationAdapter;
import pl.migbud.hexagonal.infrastructure.persistence.inmemory.product.ProductInMemoryAdapter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProductServiceAdapterTest {

    private static final Long EMPTY_ID = null;
    private ProductRepositoryPort productRepositoryPort = new ProductInMemoryAdapter();
    private EmailNotificationPort emailNotificationPort = new EmailNotificationAdapter();
    private ProductServicePort productServicePort = new ProductServiceAdapter(new ProductService(productRepositoryPort,emailNotificationPort));

    @Test
    void shouldAddProduct(){
        //given
        Product product = new Product(EMPTY_ID,"Product 1","Prod 1 desc");
        //when
        Product result = productServicePort.addProduct(product);
        //then
        assertAll(
                ()->assertThat(result.getId()).isNotNull(),
                ()->assertThat(result.getName()).isEqualTo("Product 1"),
                ()->assertThat(result.getDescription()).isEqualTo("Prod 1 desc")
        );
    }

    @Test
    void shouldGetProducts(){
        //given
        productRepositoryPort.save(new Product(EMPTY_ID,"Product 1","Prod 1 desc"));
        //when
        List<Product> result = productServicePort.getProducts();
        //then
        assertThat(result).isNotEmpty();
    }

}