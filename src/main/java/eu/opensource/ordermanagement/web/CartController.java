package eu.opensource.ordermanagement.web;

import eu.opensource.ordermanagement.domain.Cart;
import eu.opensource.ordermanagement.domain.Product;
import eu.opensource.ordermanagement.service.CatalogService;
import eu.opensource.ordermanagement.web.util.CartSingleton;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/cart")
@RestController
public class CartController {

    private final Cart cart;

    private final CatalogService catalogService;

    @GetMapping("/add")
    public ResponseEntity<Void> addProductToCart(@RequestBody String productCode) {

        Product product = catalogService.getProductByCode(productCode);
        cart.addProductToCart(product);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/clear")
    public ResponseEntity<Void> clearCart() {

        cart.clearCart();

        return ResponseEntity.ok().build();
    }
}
