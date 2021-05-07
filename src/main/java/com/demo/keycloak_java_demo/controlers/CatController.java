package com.demo.keycloak_java_demo.controlers;

import com.demo.keycloak_java_demo.models.dtos.CatDTO;
import com.demo.keycloak_java_demo.models.forms.CatForm;
import com.demo.keycloak_java_demo.services.CatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cat")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @RolesAllowed({"admin", "manager"})
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
