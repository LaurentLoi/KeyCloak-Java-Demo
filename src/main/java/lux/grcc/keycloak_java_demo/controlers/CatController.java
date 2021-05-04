package lux.grcc.keycloak_java_demo.controlers;

import lux.grcc.keycloak_java_demo.models.dtos.CatDTO;
import lux.grcc.keycloak_java_demo.models.forms.CatForm;
import lux.grcc.keycloak_java_demo.services.CatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cat")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public ResponseEntity<List<CatDTO>> getAll() {
        return ResponseEntity.ok(this.catService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CatDTO> getOne(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(this.catService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<CatDTO> insert(@Valid @RequestBody CatForm catForm) throws Exception {
        return ResponseEntity.ok(this.catService.insertWithReturnValue(catForm));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(this.catService.delete(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CatDTO> update(@Valid @PathVariable(name = "id") long id, @RequestBody CatForm catForm) {
        return ResponseEntity.ok(this.catService.update(id, catForm));
    }
}
