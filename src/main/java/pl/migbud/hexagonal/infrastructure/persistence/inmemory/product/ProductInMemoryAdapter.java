package pl.migbud.hexagonal.infrastructure.persistence.inmemory.product;

import pl.migbud.hexagonal.domain.product.model.Product;
import pl.migbud.hexagonal.domain.product.port.out.ProductRepositoryPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ProductInMemoryAdapter implements ProductRepositoryPort {

    private final Map<Long,Product> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator=new AtomicLong();

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Product save(Product product) {
        if (product.getId()==null){
            product.setId(idGenerator.incrementAndGet());
        }
        store.put(product.getId(), product);
        return product;
    }
}
