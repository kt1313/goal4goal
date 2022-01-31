package pl.com.k1313.goal4goal.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.k1313.goal4goal.domain.team.Team;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
import pl.com.k1313.goal4goal.domain.team.TacticsService;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;
import pl.com.k1313.goal4goal.domain.team.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {

private TeamService teamService;

    @Autowired
    PlayerService playerService;

    @Autowired
    public TeamController(TeamService teamService){this.teamService=teamService;}

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TacticsService tacticsService;

//    @PostMapping("/firstsquadplayers")
//    public String first11() {
//        this.teamService.setUpFirst11();
//        return "firstsquadplayers";
}


