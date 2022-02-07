package pl.com.k1313.goal4goal.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.k1313.goal4goal.domain.team.Team;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
import pl.com.k1313.goal4goal.domain.team.TacticsService;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;
import pl.com.k1313.goal4goal.domain.team.TeamService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {

    private TeamService teamService;

    @Autowired
    PlayerService playerService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TacticsService tacticsService;

    @PostMapping("/first11FormationsValues")
    public String first11AttackCalculation
            (Model model) {

        List<Integer> formationsValues=new ArrayList<>();

        this.teamService.calculateFirst11FormationsValues();
//        model.addAttribute();

        return "redirect:/firstsquadplayers";
    }
}


