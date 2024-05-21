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

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                // first record 0
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].names.id", Matchers.is(task.getNames().getId())))
                .andExpect(jsonPath("$[0].names.name", Matchers.is(task.getNames().getName())))
                .andExpect(jsonPath("$[0].content", Matchers.is("ABC")))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void postTask() throws Exception {
        Tasks newTask = taskBuilder();
        Object[] taskAndUri = new Object[2];
        URI uri = URI.create("/tasks/1");
        taskAndUri[0] = newTask;
        taskAndUri[1] = uri;

        when(tasksService.addNewTask(any(Tasks.class))).thenReturn(taskAndUri);
        mockMvc.perform(
                        post("/tasks")
                                .content(objectMapper.writeValueAsString(newTask))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.names.id", Matchers.is(1)))
                .andExpect(jsonPath("$.names.name", Matchers.is("XYZ")))
                .andExpect(jsonPath("$.content", Matchers.is("ABC")))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void patchTask() {
    }

    @Test
    void deleteTask() throws Exception {
        Tasks task = taskBuilder();

        when(tasksService.isTaskDeleted(task.getId())).thenReturn(true);
        mockMvc.perform(delete("/tasks/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
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