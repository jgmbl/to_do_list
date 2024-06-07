package pl.jgmbl.to_do_list_backend.names;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    protected Optional<Names> getIdByName(String name) {
        return namesRepository.findByName(name.toLowerCase());
    }

    protected Object[] addNewName(Names name) {
        // save new object to database
        Names savedName = namesRepository.save(name);

        // create new full URL for POST request
        URI nameUri = ServletUriComponentsBuilder.fromCurrentRequest() // get informations about current host, port etc.
                .path("/{id}") // add path to the request
                .buildAndExpand(savedName.getId()) // add savedName id value to path
                .toUri(); // new full URL for request

        Object[] savedNameAndNameUri = new Object[2];
        savedNameAndNameUri[0] = savedName;
        savedNameAndNameUri[1] = nameUri;

        return savedNameAndNameUri;
    }

    protected boolean isNameDeleted(Integer id) {
        if (namesRepository.existsById(id)) {
            namesRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
