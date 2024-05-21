package pl.jgmbl.to_do_list_backend.tasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.jgmbl.to_do_list_backend.names.Names;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    void getAllTasks() throws Exception {
        Tasks task = taskBuilder();

        when(tasksService.getAllTasks()).thenReturn(Collections.singletonList(task));

        mockMvc.perform(get("/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getTasksByNameId() throws Exception {
        Tasks task = taskBuilder();
        List<Tasks> tasksList = Arrays.asList(task);

        when(tasksService.getTasksByNameId(1)).thenReturn(tasksList);

        mockMvc.perform(get("/tasks/{nameId}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // JSON response is a table
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].names.id", Matchers.is(task.getNames().getId())))
                .andExpect(jsonPath("$[0].names.name", Matchers.is(task.getNames().getName())))
                .andExpect(jsonPath("$[0].content", Matchers.is("ABC")))
                .andExpect(jsonPath("$").isNotEmpty());
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