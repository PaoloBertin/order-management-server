package eu.opensource.ordermanagement.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

// @RequestMapping("/default")
@RestController
public class DefaultController {

//     @GetMapping
    public String defaultAfterLogin(HttpServletRequest request) {

        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/";
        }

        return "redirect:/";
    }
}
