package pl.migbud.hexagonal.domain.product.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.migbud.hexagonal.domain.common.emailnotification.EmailNotificationPort;
import pl.migbud.hexagonal.domain.product.model.Product;
import pl.migbud.hexagonal.domain.product.port.out.ProductRepositoryPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private static final Long EMPTY_ID = null;

    @Mock
    private EmailNotificationPort emailNotificationPort;
    @Mock
    private ProductRepositoryPort productRepositoryPort;
    @InjectMocks
    private ProductService productService;

    @Test
    void shouldAddProduct(){
        //given
        Product product = new Product(EMPTY_ID,"Product 1","Prod 1 desc");
        Mockito.when(productRepositoryPort.save(any())).then(invocationOnMock -> {
            product.setId(1L);
            return product;
        });
        //when
        Product result = productService.addProduct(product);
        //then
        assertAll(
                ()->assertThat(result.getId()).isEqualTo(1L),
                ()->assertThat(result.getName()).isEqualTo("Product 1"),
                ()->assertThat(result.getDescription()).isEqualTo("Prod 1 desc")
        );
    }

}