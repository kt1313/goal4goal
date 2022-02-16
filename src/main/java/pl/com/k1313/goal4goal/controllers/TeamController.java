package pl.com.k1313.goal4goal.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.k1313.goal4goal.domain.match.MatchService;
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
    @Autowired
    MatchService matchService;

    @PostMapping("/first11FormationsValues")
    public String first11FormationValuesCalculation
            (List<Integer> values, Model model) {
        if (values == null) {
            System.out.println("Brak wartości dla formacji, sprawdz skład");
        } else {
            List<Integer> formationsValues = this.teamService.calculateFirst11FormationsValues();
            int attacking = 0;
            int ballControl = 1;
            int passing = 2;
            int first11Attack = formationsValues.get(attacking);
            int first11BallControl = formationsValues.get(ballControl);
            int first11Passing = formationsValues.get(passing);

            model.addAttribute("first11Attack", first11Attack);
            System.out.println("TeamContr , first11FormValCalculation: atak: "
                    + model.getAttribute("first11Attack"));
            model.addAttribute("first11BallControl", first11BallControl);
            model.addAttribute("first11Passing", first11Passing);
            this.matchService.createUserTeam();

        }
        return "redirect:/firstsquadplayers";
    }

}


