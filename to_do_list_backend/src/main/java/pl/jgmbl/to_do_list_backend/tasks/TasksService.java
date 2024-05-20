package pl.jgmbl.to_do_list_backend.tasks;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.jgmbl.to_do_list_backend.names.Names;
import pl.jgmbl.to_do_list_backend.names.NamesRepository;

import java.net.URI;
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
        Optional<Names> names = namesRepository.findById(nameId);
        if (names.isEmpty()) {
            return null;
        }
        return tasksRepository.findByNamesId(nameId);
    }

    protected Object[] addNewTask(Tasks tasks) {
        Tasks savedTask = tasksRepository.save(tasks);

        URI nameIdUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(savedTask.getNames().getId())
                .toUri();

        Object[] savedTaskAndNameUri = new Object[2];
        savedTaskAndNameUri[0] = savedTask;
        savedTaskAndNameUri[1] = nameIdUri;

        return savedTaskAndNameUri;
    }

    protected Optional<Tasks> updateTaskContent(Integer taskId, Tasks tasks) {
        return tasksRepository.findById(taskId)
                .map(currentTask -> {
                    if (currentTask.getContent() != null) {
                        currentTask.setContent(tasks.getContent());
                    }
                    return tasksRepository.save(currentTask);
                });
    }

    protected boolean isTaskDeleted(Integer taskId) {
        if (!tasksRepository.existsById(taskId)) {
            return false;
        } else {
            tasksRepository.deleteById(taskId);
            return true;
        }
    }
}
