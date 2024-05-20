package pl.jgmbl.to_do_list_backend.names;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NamesController.class)
class NamesControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private NamesService namesService;

    @Test
    void getAllNames() {
    }

    @Test
    void getNameById() {
    }

    @Test
    void postName() {
    }

    @Test
    void deleteName() {
    }
}