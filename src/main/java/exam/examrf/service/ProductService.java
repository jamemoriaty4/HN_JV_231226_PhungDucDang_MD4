package exam.examrf.service;

import exam.examrf.model.dto.request.ProductRequestDTO;
import exam.examrf.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ProductService {
    Product saveOrUpdate(ProductRequestDTO productRequestDTO);
    Product findById(Long id);
    void deleteById(Long id);
    Page<Product> paginate(Pageable pageable);
    Set<Product> findByName(String name);
}
