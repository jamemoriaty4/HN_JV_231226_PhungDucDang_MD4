package exam.examrf.model.dto.respone;

import exam.examrf.model.entity.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CatalogResponeDTO {
    private Long id;
    private String name;
    private String description;
    Set<Product> products;
}
