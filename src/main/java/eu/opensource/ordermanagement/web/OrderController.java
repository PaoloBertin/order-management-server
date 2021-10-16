package eu.opensource.ordermanagement.web;

import eu.opensource.ordermanagement.domain.Cart;
import eu.opensource.ordermanagement.domain.Customer;
import eu.opensource.ordermanagement.domain.Order;
import eu.opensource.ordermanagement.service.OrderService;
import eu.opensource.ordermanagement.service.impl.dto.OrderDto;
import eu.opensource.ordermanagement.web.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderController {

    private final MessageSource messageSource;

    private final Cart cart;

    private final OrderService orderService;

    @GetMapping("/{orderId}/customers")
    public ResponseEntity<OrderDto> viewOrderById(@PathVariable Long orderId) {

        OrderDto orderDto = orderService.getOrderById(orderId);

        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<OrderDto>> viewOrdersByCustomer(Authentication authentication) {

        Customer customer = (Customer) authentication.getPrincipal();
        Long customerId = customer.getId();

        List<OrderDto> orders = orderService.getOrderByCustomer(customerId);


        return ResponseEntity.ok(orders);
    }

    @GetMapping("/checkout")
    public String viewOrder(Authentication authentication) {

        Customer customer = (Customer) authentication.getPrincipal();

        return "orders/orderCheckout";
    }

    @PostMapping("/checkout")
    public ResponseEntity<Void> saveOrder(Authentication authentication) {

        Message message = null;

        Order order = orderService.saveOrder();

        cart.clearCart();

        return ResponseEntity.ok().build();
    }
}
