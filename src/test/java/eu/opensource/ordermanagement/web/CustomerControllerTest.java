package eu.opensource.ordermanagement.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import eu.opensource.ordermanagement.web.util.CustomerForm;
import eu.opensource.ordermanagement.web.util.PasswordForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String url = "/customers";

    @Test
    void createCustomer() {

    }

    @Test
    void updateCustomer() throws Exception {

        CustomerForm customerForm = new CustomerForm();
        customerForm.setCustomerId(4L);
        customerForm.setFirstname("Antonio");
        customerForm.setLastname("Ferrari");
        customerForm.setUsername("ferrari@email.com");
        customerForm.setNickname("Andelante");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String requestJson = objectMapper.writeValueAsString(customerForm);

        mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson)
                            .with(httpBasic("admin@email.com", "admin")))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.firstname", equalTo("Antonio")))
        ;
    }

    @Test
    void changePasswordTest() throws Exception {

        PasswordForm passwordForm = new PasswordForm();
        passwordForm.setPassword("giulietta");
        passwordForm.setPassword2("giulietta");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String requestJson = objectMapper.writeValueAsString(passwordForm);

        mvc.perform(put(url + "/changePassword").contentType(MediaType.APPLICATION_JSON)
                                                .content(requestJson)
                                                .with(httpBasic("ferrari@email.com", "ferrari")))
           .andExpect(status().isOk())
        ;
    }

    @Test
    void changeAddressesHolderTest() {

    }

    @Test
    void changeAddressesDeliveryTest() {

    }

    @Test
    void testChangeAddressesDeliveryTest() {

    }
}