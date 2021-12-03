package pl.com.k1313.goal4goal.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.k1313.goal4goal.domain.team.TacticsService;

@Controller
public class TacticsController {

    @Autowired
    TacticsService tacticsService;

}
