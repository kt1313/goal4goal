package pl.com.k1313.goal4goal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.k1313.goal4goal.domain.match.MatchService;
import pl.com.k1313.goal4goal.domain.team.MatchTeam;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;
import pl.com.k1313.goal4goal.domain.team.TeamService;

@Controller
@RequestMapping("/match")
public class MatchController {

    private MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @Autowired
    public MatchTeam matchTeam;

    @Autowired
    public TeamService teamService;
    @Autowired
    public TeamRepository teamRepository;

    @GetMapping
    public String match(Model m) {

        System.out.println("MatchContr, --------------------");
        this.teamService.findAllMatchTeams().stream()
                .filter(matchTeam1 -> matchTeam1.)
        m.addAttribute("hostTeamName",
                this.matchService.createUserTeam().getTeamName());
        m.addAttribute("guestTeamName",
                this.matchService.createDefaultOppTeam().getTeamName());
        return "match";
    }


}
