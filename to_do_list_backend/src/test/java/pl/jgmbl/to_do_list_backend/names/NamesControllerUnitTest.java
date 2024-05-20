package pl.jgmbl.to_do_list_backend.names;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NamesController.class)
class NamesControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NamesService namesService;

    @Test
    void getAllNames() throws Exception {
        when(namesService.getAllNames()).thenReturn(Collections.singletonList(new Names()));

        mockMvc.perform(get("/names"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getNameById() {
        when(namesService.getNameById(1)).thenReturn(Optional.of(names));
    }

    @Test
    void postName() {
    }

    @Test
    void deleteName() {
    }

    private Names nameBuilder() {
        Names name = Names.builder()
                .id(1)
                .name("XYZ")
                .build();

        return name;
    }
}