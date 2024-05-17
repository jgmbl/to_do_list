package pl.jgmbl.to_do_list_backend.tasks;

import org.springframework.stereotype.Service;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    protected Iterable<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }
}
