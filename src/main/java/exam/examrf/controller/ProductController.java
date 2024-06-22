package exam.examrf.controller;

import exam.examrf.model.dto.request.ProductRequestDTO;
import exam.examrf.model.entity.Catalog;
import exam.examrf.model.entity.Product;
import exam.examrf.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<Page<Product>> index(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "2", name = "limit") int limit,
            @RequestParam(defaultValue = "ASC", name = "sort") String sort,
            @RequestParam(defaultValue = "id", name = "sortBy") String sortBy) {
        Pageable pageable;
        if (sort.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, limit, Sort.by(sortBy).descending());
        } else {
            pageable = PageRequest.of(page, limit, Sort.by(sortBy).ascending());
        }
        Page<Product> products = productService.paginate(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> create(@ModelAttribute ProductRequestDTO productRequestDTO) {
        Product productNew = productService.saveOrUpdate(productRequestDTO);
        return new ResponseEntity<>(productNew, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @ModelAttribute ProductRequestDTO productRequestDTO) {
        Product productUpdate = productService.saveOrUpdate(productRequestDTO);
        return new ResponseEntity<>(productUpdate, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        Set<Product> product = productService.findByName(name);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

}
