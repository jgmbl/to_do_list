package pl.jgmbl.to_do_list_backend.tasks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jgmbl.to_do_list_backend.names.Names;
import pl.jgmbl.to_do_list_backend.names.NamesRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TasksServiceUnitTest {
    @Mock
    TasksRepository tasksRepository;
    @Mock
    NamesRepository namesRepository;
    @InjectMocks
    TasksService tasksService;

    @Test
    void getAllTasks() {
        Names names = new Names(1, "ABC");
        Names names1 = new Names(2, "DEF");
        Tasks tasks = new Tasks(1, names, "XYZ");
        Tasks tasks1 = new Tasks(2, names1, "TEST");

        when(tasksRepository.findAll()).thenReturn(Arrays.asList(tasks, tasks1));
        Iterable<Tasks> allTasks = tasksService.getAllTasks();

        List<Tasks> allTasksList = new ArrayList<>();
        for (Tasks task : allTasks) {
            allTasksList.add(task);
        }

        assertEquals(allTasksList.size(), 2);
        assertEquals(allTasksList.get(0).getId(), 1);
        assertEquals(allTasksList.get(0).getNames().getName(), "ABC");
        assertEquals(allTasksList.get(0).getContent(), "XYZ");
        assertEquals(allTasksList.get(1).getId(), 2);
        assertEquals(allTasksList.get(1).getNames().getName(), "DEF");
        assertEquals(allTasksList.get(1).getContent(), "TEST");
    }

    @Test
    void getTasksByNameId() {
        Names names = new Names(1, "ABC");
        Tasks tasks = new Tasks(1, names, "XYZ");
        Tasks tasks1 = new Tasks(2, names, "TEST");

        when(tasksRepository.findByNamesId(1)).thenReturn(Arrays.asList(tasks, tasks1));
        when(namesRepository.findById(1)).thenReturn(Optional.of(names));
        Iterable<Tasks> tasksByNameId = tasksService.getTasksByNameId(1);
        Optional<Names> namesRepositoryById = namesRepository.findById(1);
        Names nameFromRepository = namesRepositoryById.orElseGet(null);

        List<Tasks> tasksByNameIdList = new ArrayList<>();
        for (Tasks task : tasksByNameId) {
            tasksByNameIdList.add(task);
        }

        assertNotEquals(tasksByNameId, empty());
        assertNotEquals(tasksByNameId, null);
        assertEquals(nameFromRepository.getId(), 1);
        assertEquals(nameFromRepository.getName(), "ABC");
        assertEquals(tasksByNameIdList.get(0).getId(), 1);
        assertEquals(tasksByNameIdList.get(0).getNames().getName(), "ABC");
        assertEquals(tasksByNameIdList.get(0).getNames().getId(), 1);
        assertEquals(tasksByNameIdList.get(0).getContent(), "XYZ");
        assertNotEquals(tasksByNameIdList.get(0).getContent(), "TEST");
        assertEquals(tasksByNameIdList.get(1).getId(), 2);
        assertEquals(tasksByNameIdList.get(1).getNames().getName(), "ABC");
        assertEquals(tasksByNameIdList.get(1).getNames().getId(), 1);
        assertEquals(tasksByNameIdList.get(1).getContent(), "TEST");

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