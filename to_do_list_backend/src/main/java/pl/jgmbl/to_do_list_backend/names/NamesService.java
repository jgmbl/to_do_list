package pl.jgmbl.to_do_list_backend.names;

import org.springframework.stereotype.Service;

@Service
public class NamesService {
    private final NamesRepository namesRepository;

    public NamesService(NamesRepository namesRepository) {
        this.namesRepository = namesRepository;
    }

    public Iterable<Names> getAllNames () {
        return namesRepository.findAll();
    }
}
