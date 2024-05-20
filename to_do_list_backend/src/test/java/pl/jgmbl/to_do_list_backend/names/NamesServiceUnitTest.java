package pl.jgmbl.to_do_list_backend.names;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class NamesServiceUnitTest {

    @Mock
    NamesRepository namesRepository;
    @InjectMocks
    NamesService namesService;

    @Test
    void getAllNames() {
    }

    @Test
    void getNameById() {
    }

    @Test
    void addNewName() {
    }

    @Test
    void isNameDeleted() {
    }
}