package eu.opensource.ordermanagement.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CatalogControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String url = "/catalog";

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void productByCategoryTest() throws Exception {

        mvc.perform(get(url + "/{categoryId}", 2))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.length()",  equalTo(9)))
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void productById() throws Exception {

        mvc.perform(get(url + "/categories/{productId}", 1))
           .andExpect(jsonPath("$.name", equalTo("Florinda")))
           .andExpect(status().isOk());
    }
}
