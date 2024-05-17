package pl.jgmbl.to_do_list_backend.tasks;

import org.springframework.stereotype.Service;
import pl.jgmbl.to_do_list_backend.names.Names;
import pl.jgmbl.to_do_list_backend.names.NamesRepository;

import java.util.Optional;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;
    private final NamesRepository namesRepository;

    public TasksService(TasksRepository tasksRepository, NamesRepository namesRepository) {
        this.tasksRepository = tasksRepository;
        this.namesRepository = namesRepository;
    }

    protected Iterable<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    protected Iterable<Tasks> getTasksByNameId(Integer nameId) {
        Optional<Names> names = doesNameIdExists(nameId);
        if (names.isEmpty()) {
            return null;
        }
        return tasksRepository.findByNamesId(nameId);
    }

    private Optional<Names> doesNameIdExists(Integer nameId) {
        return namesRepository.findById(nameId);
    }
}
