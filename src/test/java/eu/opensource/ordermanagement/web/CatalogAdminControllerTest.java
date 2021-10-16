package eu.opensource.ordermanagement.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import eu.opensource.ordermanagement.service.CatalogService;
import eu.opensource.ordermanagement.web.util.ProductForm;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CatalogAdminControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String url = "/admin/catalog";

    @Autowired
    private CatalogService catalogService;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void createProductTest() throws Exception {

        ProductForm productForm = new ProductForm();
        productForm.setName("Quattro Stagioni");
        productForm.setProductCode("P0035");
        productForm.setPrice(BigDecimal.valueOf(8.0));
        productForm.setDescription("Funghi, prosciutto, olive, carciofi, pomodori e basilico.");
        productForm.setCategoryName("Pizze");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String requestJson = objectMapper.writeValueAsString(productForm);

        mvc.perform(post(url + "/categories/products").contentType(MediaType.APPLICATION_JSON)
                                                      .content(requestJson)
                                                      .with(httpBasic("admin@email.com", "admin")))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name", equalTo("Quattro Stagioni")))
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void updateProductTest() throws Exception {

        ProductForm productForm = new ProductForm();
        productForm.setProductId(6L);
        productForm.setName("Prosciutto cotto e funghi");
        productForm.setProductCode("P0006");
        productForm.setPrice(BigDecimal.valueOf(7.0));
        productForm.setDescription("Pomodoro, mozzarella, funghi champignon, prosciutto cotto al naturale.");
        productForm.setCategoryName("Pizze");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String requestJson = objectMapper.writeValueAsString(productForm);

        mvc.perform(put(url + "/categories/products").contentType(MediaType.APPLICATION_JSON)
                                                     .content(requestJson)
                                                     .with(httpBasic("admin@email.com", "admin")))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name", equalTo("Prosciutto cotto e funghi")))
        ;

    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void deleteProductTest() throws Exception {

        mvc.perform(delete(url + "/categories/{productId}", 1L).contentType(MediaType.APPLICATION_JSON)
                                                               .with(httpBasic("admin@email.com", "admin")))
        ;

        long numProducts = catalogService.getNumberOfProducts();

        assertThat(numProducts).isEqualTo(34);
    }
}