package pl.migbud.hexagonal.infrastructure.application.rest.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.migbud.hexagonal.domain.product.model.Product;
import pl.migbud.hexagonal.domain.product.port.in.ProductServicePort;
import pl.migbud.hexagonal.infrastructure.application.rest.product.dto.ProductDto;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
class ProductController {

    private static final Long EMPTY_ID = null;
    private final ProductServicePort productServicePort;

    @GetMapping
    ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productServicePort.getProducts());
    }

    @PostMapping
    ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        Product product = new Product(
                EMPTY_ID,
                productDto.getName(),
                productDto.getDescription()
        );

        Product created = productServicePort.addProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ProductDto(
                        created.getId(),
                        created.getName(),
                        created.getDescription()
                )
        );
    }

}
