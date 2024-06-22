package exam.examrf.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name",length = 100)
    private String name;

    @Column(name="description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "catalog",fetch = FetchType.LAZY)
    Set<Product> products;

}
