package eu.opensource.ordermanagement.web;

import eu.opensource.ordermanagement.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class WelcomeController {

    private final Cart cart;

    @GetMapping
    public String homePage() {

        return "welcome";
    }

    @GetMapping("/delivery")
    public String deliveryPage() {

        return "delivery";
    }
}
