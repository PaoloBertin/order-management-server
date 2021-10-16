package eu.opensource.ordermanagement.web;

import eu.opensource.ordermanagement.domain.Category;
import eu.opensource.ordermanagement.domain.Product;
import eu.opensource.ordermanagement.service.CatalogService;
import eu.opensource.ordermanagement.web.util.ProductForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/catalog")
@RestController
public class CatalogAdminController {

    private final CatalogService catalogService;

    @PostMapping("/categories/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductForm productForm) {

        log.debug("REST request to save Product : {}", productForm);

        if (productForm.getProductId() != null) {
            // throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");  // TODO
        }

        Category category = catalogService.getCategoryByName(productForm.getCategoryName());
        Product product = new Product();
        product.setName(productForm.getName());
        product.setProductCode(productForm.getProductCode());
        product.setPrice(productForm.getPrice());
        product.setDescription(productForm.getDescription());
        product.setCategory(category);

        log.debug("Product: {}", product);

        // rende persistenti dati prodotto
        product = catalogService.saveProduct(product);

        return ResponseEntity.ok(product);
    }

    @PutMapping("/categories/products")
    public ResponseEntity<Product> updteProduct(@Valid @RequestBody ProductForm productForm) {

        log.debug("REST request to update Product : {}", productForm);
        if (productForm.getProductId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");  TODO
            log.debug("invalid id");
        }

        Category category = catalogService.getCategoryByName(productForm.getCategoryName());
        Product product = new Product();
        product.setId(productForm.getProductId());
        product.setName(productForm.getName());
        product.setProductCode(productForm.getProductCode());
        product.setPrice(productForm.getPrice());
        product.setDescription(productForm.getDescription());
        product.setCategory(category);

        // rende persistenti dati prodotto
        Product result = catalogService.saveProduct(product);
        log.info("Updated product: {}", result);

       return ResponseEntity.ok(result);
    }

    // @DeleteMapping("/categories/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {

        log.debug("REST request to delete Product : {}", productId);

        catalogService.deleteProduct(productId);

        return ResponseEntity.noContent().build();
    }
}
