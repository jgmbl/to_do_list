package pl.jgmbl.to_do_list_backend.names;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
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
    void getIdByName() {
        Names names = new Names(1, "XYZ");

        when(namesRepository.findByName("XYZ".toLowerCase())).thenReturn(Optional.of(names));
        Optional<Names> idByName = namesService.getIdByName("XYZ".toLowerCase());
        Names idByName1 = idByName.orElse(new Names());

        Assertions.assertNotEquals(idByName, null);
        Assertions.assertEquals(idByName1.getId(), 1);
        Assertions.assertEquals(idByName1.getName(), "XYZ");
    }

    @Test
    void addNewName() {
        Names newName = new Names(1, "XYZ");
        Names savedName = new Names(1, "XYZ");

        //mock
        when(namesRepository.save(newName)).thenReturn(savedName);

        Object[] nameObjects = namesService.addNewName(newName);

        Mockito.verify(namesRepository, times(1)).save(newName);

        // get mocked argument Names by argumentCaptor method
        ArgumentCaptor<Names> namesArgumentCaptor = ArgumentCaptor.forClass(Names.class);
        // capture gets value of argument saved in repository
        verify(namesRepository).save(namesArgumentCaptor.capture());
        Names createdName = namesArgumentCaptor.getValue();
        Assertions.assertNotNull(createdName.getId());
        Assertions.assertEquals("XYZ", createdName.getName());


    }

    @Test
    void isNameDeleted() {
        Names names = new Names(1, "XYZ");

        when(namesRepository.findById(1)).thenReturn(Optional.of(names));
        boolean nameDeleted = namesService.isNameDeleted(names.getId());

        if (nameDeleted) {
            verify(namesRepository, times(1)).deleteById(names.getId());
            ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

            verify(namesRepository).deleteById(integerArgumentCaptor.capture());
            Integer namesIdDeleted = integerArgumentCaptor.getValue();

            Assertions.assertNotNull(namesIdDeleted);
            Assertions.assertEquals(1, namesIdDeleted);
        }
    }
}