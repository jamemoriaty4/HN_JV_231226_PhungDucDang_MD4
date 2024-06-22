package exam.examrf.controller;

import exam.examrf.model.dto.respone.CatalogResponeDTO;
import exam.examrf.model.entity.Catalog;
import exam.examrf.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {
    private final CatalogService catalogService;
    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }
    @GetMapping
    public ResponseEntity<Page<Catalog>> index(
            @RequestParam(defaultValue = "0",name = "page")int page,
            @RequestParam(defaultValue = "2",name = "limit")int limit,
            @RequestParam(defaultValue = "ASC",name = "sort")String sort,
            @RequestParam(defaultValue = "id",name="sortBy")String sortBy){
        Pageable pageable;
        if(sort.equalsIgnoreCase("desc")){
            pageable= PageRequest.of(page,limit, Sort.by(sortBy).descending());
        }else{
            pageable=PageRequest.of(page,limit,Sort.by(sortBy).ascending());
        }
        Page<Catalog> catalogs =catalogService.paginate(pageable);
        return  new ResponseEntity<>(catalogs, HttpStatus.OK);
    }
    @PostMapping("/create")
    private ResponseEntity<?> create(@RequestBody Catalog catalog){
        Catalog catalogNew=  catalogService.save(catalog);
        return new ResponseEntity<>(catalogNew,HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Catalog> update(@PathVariable Long id,@RequestBody Catalog catalog){
        Catalog catalogUpdate1 = catalogService.findById(id);
        catalogUpdate1.setName(catalog.getName());
        catalogUpdate1.setDescription(catalog.getDescription());
        Catalog catalogUpdate= catalogService.save(catalogUpdate1);
        return new ResponseEntity<>(catalogUpdate,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> delete(@PathVariable Long id){
        catalogService.deleteById(id);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }
    @GetMapping("/findProduct/{id}")
    public ResponseEntity<List<CatalogResponeDTO>> index(@PathVariable Long id){
        List<CatalogResponeDTO> classes = catalogService.findAll(id);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }
}
