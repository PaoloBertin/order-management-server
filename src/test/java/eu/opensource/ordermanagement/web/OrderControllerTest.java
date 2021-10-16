package eu.opensource.ordermanagement.web;

import eu.opensource.ordermanagement.domain.Order;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String url = "/orders";

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewOrderByIdTest() throws Exception {

        mvc.perform(get(url + "/{orderId}/customers", 1L).with(httpBasic("admin@email.com", "admin")))
           .andExpect(status().isOk())
//           .andExpect(jsonPath("$.totalAmount", is(closeTo(new BigDecimal("17.00"), new BigDecimal("0.001")))))
           .andExpect(jsonPath("$.totalAmount", equalTo(17.00))) // TODO il confronto dovrebbe essere fra BigDecimal
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewOrdersByCustomerTest() throws Exception {

        mvc.perform(get(url + "/{customerId}", 3).with(httpBasic("rossi@email.com", "rossi")))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.length()", IsCollectionWithSize.hasSize(4)))
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewOrderTest() throws Exception {

        mvc.perform(get(url + "/checkout").with(httpBasic("rossi@email.com", "rossi")))
//           .andExpect(model().attribute("customer", hasProperty("username", equalTo("rossi@email.com"))))
           .andExpect(status().isOk());
    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void saveOrderTest() throws Exception {

        Order order = new Order();

        mvc.perform(post(url + "checkout"));
    }
}