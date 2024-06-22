package exam.examrf.model.dto.request;

import exam.examrf.model.entity.Product;
import lombok.*;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CatalogRequestDTO {
    private String name;
    private String description;
    Set<Product> products;
}
