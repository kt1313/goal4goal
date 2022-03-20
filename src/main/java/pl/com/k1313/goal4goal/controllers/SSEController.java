package pl.com.k1313.goal4goal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.k1313.goal4goal.domain.star.StarService;


@Controller
@RequestMapping("/ssetrial")
public class SSEController {

//        private StarService starService;
//
//        @Autowired
//        public StarController(StarService starService) {
//            this.starService = starService;
//        }

    @GetMapping
    public String match(Model m) {
        return "ssetrial";
    }
}

