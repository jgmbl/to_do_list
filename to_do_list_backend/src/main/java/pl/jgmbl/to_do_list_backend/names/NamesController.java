package pl.jgmbl.to_do_list_backend.names;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
public class NamesController {

    private final NamesService namesService;

    public NamesController(NamesService namesService) {
        this.namesService = namesService;
    }

    @GetMapping("/names")
    public ResponseEntity<Iterable<Names>> getAllNames() {
        Iterable<Names> allNames = namesService.getAllNames();

        return ResponseEntity.ok(allNames);
    }

    @GetMapping("/names/id/{id}")
    public ResponseEntity<Names> getNameById(@PathVariable Integer id) {
        Optional<Names> nameById = namesService.getNameById(id);

        return nameById
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/names/name/{name}")
    public ResponseEntity<Names> getIdByName(@PathVariable String name) {
        Optional<Names> idByName = namesService.getIdByName(name.toLowerCase());

        return idByName
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/names")
    public ResponseEntity<Object> postName(@RequestBody Names name) {
        try {
            Object[] nameAndUri = namesService.addNewName(name);

            Names savedName = (Names) nameAndUri[0];
            URI uri = (URI) nameAndUri[1];

            return ResponseEntity.created(uri).body(savedName);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Name should be unique.");
        }
    }

    @DeleteMapping("/names/{id}")
    public ResponseEntity<Void> deleteName(@PathVariable Integer id) {
        boolean nameDeleted = namesService.isNameDeleted(id);

        if (!nameDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
