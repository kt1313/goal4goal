package pl.com.k1313.goal4goal.controllers;


import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.k1313.goal4goal.components.TimeComponent;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.Tactics;
import pl.com.k1313.goal4goal.domain.Team;
import pl.com.k1313.goal4goal.domain.UserInformation;
import pl.com.k1313.goal4goal.services.PlayerService;
import pl.com.k1313.goal4goal.services.TacticsService;
import pl.com.k1313.goal4goal.services.TeamService;

import java.util.List;

@Controller
public class TeamController {

    @Autowired
    PlayerService playerService;

    @Autowired
    TeamService teamService;

    @Autowired
    Team team;

    @Autowired
    TacticsService tacticsService;

    @Autowired
    UserInformation userInformation;
    @Autowired
    TimeComponent timeComponent;

    @RequestMapping("/team")
    public String getPlayers(Model model) throws ExecutionControl.NotImplementedException {
        List<Tactics> allTactics = tacticsService.getAllTactics();
        model.addAttribute("tactics", allTactics);
        model.addAttribute("userInformation", userInformation);
        model.addAttribute("team", team);
        model.addAttribute("timeComponent", timeComponent);
        return "team";
    }

    //proba odebrania wartości z checkboxa:
    @RequestMapping(value = "/players", method = RequestMethod.POST)
    public void editCustomer(@RequestParam(value = "firstSquadChoice", required = false) String checkboxValue) {
        if (checkboxValue != null) {
            System.out.println("checkbox is checked");
        } else {
            System.out.println("checkbox is not checked");
        }
    }
}

}
