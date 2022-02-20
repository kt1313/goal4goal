package pl.com.k1313.goal4goal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.k1313.goal4goal.domain.match.MatchService;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.team.MatchTeam;
import pl.com.k1313.goal4goal.domain.team.Team;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;
import pl.com.k1313.goal4goal.domain.team.TeamService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    public TeamRepository teamRepository;
    @Autowired
    public PlayerRepository playerRepository;

    @GetMapping
    public String match(Model m) {

        System.out.println("MatchContr, --------------------");
//        this.matchService.createUserTeam();
//        this.matchService.createDefaultOppTeam();
        MatchTeam oppTeam = this.teamService.createDefaultOppTim();
        System.out.println("MatchContr, match, oppTeam: " + oppTeam);
        Optional<Player> p = this.playerRepository.findAll().stream().findFirst();
        System.out.println("MatchContr, match, player w Repo:" + p.get().getLastName());
        this.matchService.proba();
        this.teamService.createUserTim();
        Optional<MatchTeam> hostTeamNameOpt = this.teamService.findAllMatchTeams().stream()
                .filter(matchTeam1 -> matchTeam1.getTeamName().equals("Tres Tigres"))
                .findFirst();
        String hostTeamName = hostTeamNameOpt.get().getTeamName();

        Optional<MatchTeam> guestTeamNameOpt = this.teamService.findAllMatchTeams()
                .stream().filter(matchTeam -> matchTeam.getTeamName().equals("Cream Team FC"))
                .findFirst();
        System.out.println("-------"+guestTeamNameOpt+"----------");
        String guestTeamName = guestTeamNameOpt.get().getTeamName();

        m.addAttribute("hostTeamName", hostTeamName);
        m.addAttribute("guestTeamName", guestTeamName);

        return "match";
    }


}
