package exam.examrf.service;

import exam.examrf.model.dto.request.ProductRequestDTO;
import exam.examrf.model.entity.Catalog;
import exam.examrf.model.entity.Product;
import exam.examrf.repository.CatalogRepository;
import exam.examrf.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final FileService fileService;
    private final CatalogRepository catalogRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, FileService fileService, CatalogRepository catalogRepository) {
        this.productRepository = productRepository;
        this.fileService = fileService;
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Product saveOrUpdate(ProductRequestDTO productRequestDTO) {
        String image = fileService.uploadImage(productRequestDTO.getImageUrl());
        Catalog catalog = catalogRepository.findById(productRequestDTO.getCatalogId()).orElse(null);
        Product product = Product.builder()
                .id(productRequestDTO.getId())
                .description(productRequestDTO.getDescription())
                .imageUrl(image)
                .name(productRequestDTO.getName())
                .price(productRequestDTO.getPrice())
                .status(productRequestDTO.getStatus())
                .catalog(catalog)
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> paginate(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Set<Product> findByName(String name) {
        return productRepository.findAllByName(name);
    }
}
