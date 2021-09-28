package pl.com.k1313.goal4goal.controllers;


import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.Tactics;
import pl.com.k1313.goal4goal.domain.Team;
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

    @RequestMapping("/team")
    public String getPlayers(Model model) throws ExecutionControl.NotImplementedException {
        List<Tactics> allTactics = tacticsService.getAllTactics();
//        String teamName = teamService.getTeamName();
//        String managerName = teamService.getManagerName();
        model.addAttribute("tactics", allTactics);
//        model.addAttribute("teamName", teamName);
//        model.addAttribute("teamManager", managerName);
        return "team";
    }


}