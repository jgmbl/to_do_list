package pl.jgmbl.to_do_list_backend.names;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class NamesServiceUnitTest {

    @Mock
    NamesRepository namesRepository;
    @InjectMocks
    NamesService namesService;

    @Test
    void getAllNames() {
        Names names = new Names(1, "XYZ");
        Names names1 = new Names(2, "ABC");

        when(namesRepository.findAll()).thenReturn(Arrays.asList(names, names1));
        Iterable<Names> allNames = namesService.getAllNames();

        List<Names> allNamesList = new ArrayList<>();
        for (Names name : allNames) {
            allNamesList.add(name);
        }

        Assertions.assertEquals(allNamesList.size(), 2);
        Assertions.assertEquals(allNamesList.get(0).getId(), 1);
        Assertions.assertEquals(allNamesList.get(0).getName(), "XYZ");
        Assertions.assertEquals(allNamesList.get(1).getId(), 2);
        Assertions.assertEquals(allNamesList.get(1).getName(), "ABC");

    }

    @Test
    void getNameById() {
        Names names = new Names(1, "XYZ");

        when(namesRepository.findById(1)).thenReturn(Optional.of(names));
        Optional<Names> nameById = namesService.getNameById(1);
        Names nameById1 = nameById.orElse(new Names());

        Assertions.assertNotEquals(nameById, null);
        Assertions.assertEquals(nameById1.getId(), 1);
        Assertions.assertEquals(nameById1.getName(), "XYZ");
    }

    @Test
    void addNewName() {
    }

    @Test
    void isNameDeleted() {
    }
}