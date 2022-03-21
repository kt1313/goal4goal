package pl.com.k1313.goal4goal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ssetrial")
public class SSEController {

//        private StarService starService;
//
//        @Autowired
//        public StarController(StarService starService) {
//            this.starService = starService;
//        }

//    @GetMapping
//    public String match(Model m) {
//        return "ssetrial";
//    }
//
    @GetMapping
    public String getStyledPage() {
//        model.addAttribute("name", "Baeldung Reader");
        return "/styledPage";
    }
}

