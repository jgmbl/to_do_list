package pl.jgmbl.to_do_list_backend.names;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NamesController.class)
class NamesControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NamesService namesService;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAllNames() throws Exception {
        Names name = nameBuilder();
        when(namesService.getAllNames()).thenReturn(Collections.singletonList(name));

        mockMvc.perform(get("/names"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getNameById() throws Exception {
        Names name = nameBuilder();
        when(namesService.getNameById(1)).thenReturn(Optional.ofNullable(name));

        mockMvc.perform(get("/names/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("XYZ")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void postName() throws Exception {
        Names newName = nameBuilder();
        Object[] nameAndUri = new Object[2];
        URI uri = URI.create("/names/1");
        nameAndUri[0] = newName;
        nameAndUri[1] = uri;

        when(namesService.addNewName(any(Names.class))).thenReturn(nameAndUri);
        mockMvc.perform(
                        post("/names")
                                .content(objectMapper.writeValueAsString(newName))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("XYZ")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void deleteName() throws Exception {
        Names name = nameBuilder();

        when(namesService.isNameDeleted(name.getId())).thenReturn(true);

        mockMvc.perform(delete("/names/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    private Names nameBuilder() {
        Names name = Names.builder()
                .id(1)
                .name("XYZ")
                .build();

        return name;
    }
}