package eu.opensource.ordermanagement.web;

import eu.opensource.ordermanagement.domain.Cart;
import eu.opensource.ordermanagement.service.OrderService;
import eu.opensource.ordermanagement.service.impl.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/orders")
@RestController
public class OrderAdminController {

    private final OrderService orderService;

    private final Cart cart;

    @GetMapping
    public String viewAllOrders(Model uiModel) {

        List<OrderDto> orders = orderService.getAllOrders();

        uiModel.addAttribute("orders", orders);
        uiModel.addAttribute("itemsInCart", cart.getNumberOfItems());

        return "orders/ordersList";
    }
}
