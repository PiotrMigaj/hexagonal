package pl.migbud.hexagonal.domain.product.service;

import lombok.RequiredArgsConstructor;
import pl.migbud.hexagonal.domain.common.emailnotification.EmailNotificationPort;
import pl.migbud.hexagonal.domain.product.model.Product;
import pl.migbud.hexagonal.domain.product.port.out.ProductRepositoryPort;

import java.util.List;

@RequiredArgsConstructor
public class ProductService {

    private final ProductRepositoryPort productRepositoryPort;
    private final EmailNotificationPort emailNotificationPort;
    public List<Product> getProducts(){
        return productRepositoryPort.findAll();
    }

    public Product addProduct(Product product){
        Product result = productRepositoryPort.save(product);
        emailNotificationPort.send(List.of("admin@admin.pl"),String.format("Product o id: %s został dodany",result.getId()));
        return result;
    }
}
