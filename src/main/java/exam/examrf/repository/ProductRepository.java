package exam.examrf.repository;

import exam.examrf.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Set<Product> findAllByName(String name);
    Set<Product> findAllByCatalogId(Long catalogId);
}
