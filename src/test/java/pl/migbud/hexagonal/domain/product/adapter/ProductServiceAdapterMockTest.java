package pl.migbud.hexagonal.domain.product.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.migbud.hexagonal.domain.common.emailnotification.EmailNotificationPort;
import pl.migbud.hexagonal.domain.product.model.Product;
import pl.migbud.hexagonal.domain.product.port.in.ProductServicePort;
import pl.migbud.hexagonal.domain.product.port.out.ProductRepositoryPort;
import pl.migbud.hexagonal.domain.product.service.ProductService;
import pl.migbud.hexagonal.infrastructure.persistence.inmemory.product.ProductInMemoryAdapter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductServiceAdapterMockTest {

    private static final Long EMPTY_ID = null;
    private ProductRepositoryPort productRepositoryPort = Mockito.mock(ProductRepositoryPort.class);
    private EmailNotificationPort emailNotificationPort = Mockito.mock(EmailNotificationPort.class);
    private ProductServicePort productServicePort = new ProductServiceAdapter(new ProductService(productRepositoryPort,emailNotificationPort));

    @Test
    void shouldAddProduct(){
        //given
        Product product = new Product(EMPTY_ID,"Product 1","Prod 1 desc");
        Mockito.when(productRepositoryPort.save(any())).then(invocationOnMock -> {
            product.setId(1L);
            return product;
        });
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
        Mockito.when(productRepositoryPort.findAll()).then(invocationOnMock -> List.of(new Product(EMPTY_ID,"Product 1","Prod 1 desc")));
        //when
        List<Product> result = productServicePort.getProducts();
        //then
        assertThat(result).isNotEmpty();
    }

}