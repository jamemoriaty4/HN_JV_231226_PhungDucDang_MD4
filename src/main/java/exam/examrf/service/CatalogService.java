package exam.examrf.service;

import exam.examrf.model.dto.request.CatalogRequestDTO;
import exam.examrf.model.dto.respone.CatalogResponeDTO;
import exam.examrf.model.entity.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatalogService {
    Catalog saveOrUpdate(CatalogRequestDTO catalogRequestDTO);
    Catalog save(Catalog catalog);
    Catalog findById(Long id);
    void deleteById(Long id);
    Page<Catalog> paginate(Pageable pageable);
    List<CatalogResponeDTO> findAll(Long id);
}
