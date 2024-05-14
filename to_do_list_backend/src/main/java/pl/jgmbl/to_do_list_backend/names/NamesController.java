package pl.jgmbl.to_do_list_backend.names;

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
    public ResponseEntity<Iterable<Names>> displayAllNames() {
        Iterable<Names> allNames = namesService.getAllNames();

        return ResponseEntity.ok(allNames);
    }

    @GetMapping("/names/{id}")
    public ResponseEntity<Names> displayNameById(@PathVariable Integer id) {
        Optional<Names> nameById = namesService.getNameById(id);

        return nameById
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/names")
    public ResponseEntity<Names> createName(@RequestBody Names name) {
        Object[] nameAndUri = namesService.addNewName(name);

        Names savedName = (Names) nameAndUri[0];
        URI uri = (URI) nameAndUri[1];

        return ResponseEntity.created(uri).body(savedName);
    }
}
