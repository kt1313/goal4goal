package pl.com.k1313.goal4goal.controllers;


import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.k1313.goal4goal.components.TimeComponent;
import pl.com.k1313.goal4goal.domain.team.Tactics;
import pl.com.k1313.goal4goal.domain.team.Team;
import pl.com.k1313.goal4goal.domain.UserInformation;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
import pl.com.k1313.goal4goal.domain.team.TacticsService;
import pl.com.k1313.goal4goal.domain.team.TeamService;

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


}

