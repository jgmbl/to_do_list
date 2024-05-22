package pl.jgmbl.to_do_list_backend.tasks;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class TasksServiceUnitTest {
    @Mock
    TasksRepository tasksRepository;
    @InjectMocks
    TasksService tasksService;

    @Test
    void getAllTasks() {
    }

    @Test
    void getTasksByNameId() {
    }

    @Test
    void addNewTask() {
    }

    @Test
    void updateTaskContent() {
    }

    @Test
    void isTaskDeleted() {
    }
}