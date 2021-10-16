package eu.opensource.ordermanagement.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
class WelcomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void homePageTest() throws Exception {

        mvc.perform(get("/"))
           .andExpect(status().isOk())
           .andExpect(view().name("welcome"))
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void deliveryPage() throws Exception {

        mvc.perform(get("/delivery"))
           .andExpect(status().isOk())
           .andExpect(view().name("delivery"));
    }

}