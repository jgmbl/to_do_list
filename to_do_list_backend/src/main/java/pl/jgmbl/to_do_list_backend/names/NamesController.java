package pl.jgmbl.to_do_list_backend.names;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
