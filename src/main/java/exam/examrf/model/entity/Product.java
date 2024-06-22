package exam.examrf.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="description")
    private String description;
    @Column(name="image_url")
    private String imageUrl;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private Double price;
    @Column(name="status")
    private Boolean status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "catalog_id",referencedColumnName = "id")
    Catalog catalog;
}
