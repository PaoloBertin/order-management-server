package eu.opensource.ordermanagement.web;

import eu.opensource.ordermanagement.domain.Cart;
import eu.opensource.ordermanagement.domain.Category;
import eu.opensource.ordermanagement.domain.Product;
import eu.opensource.ordermanagement.service.CatalogService;
import eu.opensource.ordermanagement.web.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/catalog")
@RestController
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<Product>> productByCategory(@PathVariable Long categoryId) {

        List<Product> products = catalogService.getProductsByCategory(categoryId);

        return ResponseEntity.ok(products);
    }

    @GetMapping("/categories/{productId}")
    public ResponseEntity<Product> productById(@PathVariable Long productId) {

        Optional<Product> product = catalogService.getProductById(productId);

        return ResponseEntity.of(product);
    }
}
