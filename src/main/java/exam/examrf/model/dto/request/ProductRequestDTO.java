package exam.examrf.model.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequestDTO {
    private Long id;
    private String description;
    private MultipartFile imageUrl;
    private String name;
    private Double price;
    private Boolean status;
    private Long CatalogId;
}
