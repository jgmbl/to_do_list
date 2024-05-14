package pl.jgmbl.to_do_list_backend.names;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NamesService {
    private final NamesRepository namesRepository;
    private final Names names;

    public NamesService(NamesRepository namesRepository, Names names) {
        this.namesRepository = namesRepository;
        this.names = names;
    }

    protected Iterable<Names> getAllNames() {
        return namesRepository.findAll();
    }

    protected Optional<Names> getNameById(Integer id) {
        return namesRepository.findById(id);
    }

    protected boolean isNewNameAdded (Names name) {
        Names newName = new Names();
        if (name.getName() != null || !name.getName().isBlank()) {
            newName.setName(name.getName());
            namesRepository.save(newName);
            return true;
        } else {
            return false;
        }
    }
}
