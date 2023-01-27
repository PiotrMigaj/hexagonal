package pl.migbud.hexagonal.infrastructure.application.rest.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    @Length(min = 4)
    private String name;
    @Length(min = 4)
    private String description;
}
