package pl.jgmbl.to_do_list_backend.tasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.jgmbl.to_do_list_backend.names.Names;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TasksController.class)
class TasksControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TasksService tasksService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllTasks() {

    }

    @Test
    void getTasksByNameId() {
    }

    @Test
    void postTask() {
    }

    @Test
    void patchTask() {
    }

    @Test
    void deleteTask() {
    }

    private Tasks taskBuilder() {
        Names name = Names.builder()
                .id(1)
                .name("XYZ")
                .build();

        Tasks tasks = Tasks.builder()
                .id(1)
                .names(name)
                .content("ABC")
                .build();

        return tasks;
    }
}