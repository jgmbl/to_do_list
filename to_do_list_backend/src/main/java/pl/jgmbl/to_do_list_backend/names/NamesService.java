package pl.jgmbl.to_do_list_backend.names;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NamesService {
    private final NamesRepository namesRepository;

    public NamesService(NamesRepository namesRepository) {
        this.namesRepository = namesRepository;
    }

    protected Iterable<Names> getAllNames() {
        return namesRepository.findAll();
    }

    protected Optional<Names> getNameById(Integer id) {
        return namesRepository.findById(id);
    }

}
