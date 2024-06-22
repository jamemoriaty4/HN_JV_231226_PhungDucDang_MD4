package exam.examrf.service;

import exam.examrf.model.dto.request.CatalogRequestDTO;
import exam.examrf.model.dto.respone.CatalogResponeDTO;
import exam.examrf.model.entity.Catalog;
import exam.examrf.repository.CatalogRepository;
import exam.examrf.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogSerivceImpl implements CatalogService {
    private final CatalogRepository catalogRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CatalogSerivceImpl(CatalogRepository catalogRepository, ProductRepository productRepository) {
        this.catalogRepository = catalogRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<CatalogResponeDTO> findAll(Long id) {
        return catalogRepository.findById(id).stream().map(
                catalog -> CatalogResponeDTO.builder()
                        .id(catalog.getId())
                        .name(catalog.getName())
                        .description(catalog.getDescription())
                        .products(productRepository.findAllByCatalogId(catalog.getId()))
                        .build()
                        ).toList();
    }

    @Override
    public Catalog save(Catalog catalog) {
        return catalogRepository.save(catalog);
    }


    @Override
    public Catalog saveOrUpdate(CatalogRequestDTO catalogRequestDTO) {

        return null;
    }

    @Override
    public Catalog findById(Long id) {
        return catalogRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        catalogRepository.deleteById(id);
    }

    @Override
    public Page<Catalog> paginate(Pageable pageable) {
        return catalogRepository.findAll(pageable);
    }
}
