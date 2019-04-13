package nl.hyper42.kim.backend.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Rogier
 */
@Controller
public class SwaggerUI {

    /**
     * Redirecting swagger ui page
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
