package pl.com.k1313.goal4goal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.k1313.goal4goal.domain.match.MatchService;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
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
    public TeamService teamService;

    @GetMapping
    public String match(Model m) {
//        m.addAttribute("defaultOppTeam", this.matchService.createDefaultOppTeam());
//        m.addAttribute("userTeam", this.matchService.createUserTeam());
        return "match";
    }


}
