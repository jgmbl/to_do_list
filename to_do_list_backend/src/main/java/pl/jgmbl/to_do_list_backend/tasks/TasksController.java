package pl.jgmbl.to_do_list_backend.tasks;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TasksController {
    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<Iterable<Tasks>> getAllTasks() {
        Iterable<Tasks> allTasks = tasksService.getAllTasks();

        return ResponseEntity.ok(allTasks);
    }
}
