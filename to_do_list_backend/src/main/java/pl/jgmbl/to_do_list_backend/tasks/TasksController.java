package pl.jgmbl.to_do_list_backend.tasks;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

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

    @GetMapping("/tasks/{nameId}")
    public ResponseEntity<Iterable<Tasks>> getTasksByNameId(@PathVariable Integer nameId) {
        Iterable<Tasks> tasksByNameId = tasksService.getTasksByNameId(nameId);

        if (tasksByNameId == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tasksByNameId);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Object> postTask(@RequestBody Tasks tasks) {
        try {
            Object[] newTask = tasksService.addNewTask(tasks);

            Tasks savedTask = (Tasks) newTask[0];
            URI uri = (URI) newTask[1];

            return ResponseEntity.created(uri).body(savedTask);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Given name id does not exist.");
        }
    }

    @PatchMapping("/tasks/{taskId}")
    public ResponseEntity<Tasks> patchTask(@PathVariable Integer taskId, @RequestBody Tasks tasks) {
        Optional<Tasks> updatedTaskContent = tasksService.updateTaskContent(taskId, tasks);

        return updatedTaskContent
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        boolean taskDeleted = tasksService.isTaskDeleted(taskId);

        if (!taskDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
