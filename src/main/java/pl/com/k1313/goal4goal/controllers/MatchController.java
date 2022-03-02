package pl.com.k1313.goal4goal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.k1313.goal4goal.domain.match.Match;
import pl.com.k1313.goal4goal.domain.match.MatchRepository;
import pl.com.k1313.goal4goal.domain.match.MatchScore;
import pl.com.k1313.goal4goal.domain.match.MatchService;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.team.*;

import java.util.ArrayList;
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
    @Autowired
    public MatchRepository matchRepository;
    @Autowired
    public MatchTeamRepository matchTeamRepository;

    @GetMapping
    public String match(Model m) {

        //tu utworz Match i go zasejwuj na repo
        Match match = this.teamService.createMatch();
        //ponizej 5 linijek do testu
        //----------------------------------------------
        List<MatchTeam> matchTeamList=this.matchRepository
                .findAll().stream()
                .filter(match1 -> match1.isInProgress())
                .findFirst().get().getMatchTeams();
        System.out.println("MatchContr, match, Stworzone mecze: "+matchTeamList);
        //----------------------------------------------
        String hostTeamName = match.getMatchTeams().get(0).getTeamName();
        String guestTeamName = match.getMatchTeams().get(1).getTeamName();
        Integer hostTeamScore = match.getHostScore();
        Integer guestTeamScore = match.getGuestScore();

        m.addAttribute("hostTeamName", hostTeamName);
        m.addAttribute("guestTeamName", guestTeamName);
        m.addAttribute("hostTeamScore", hostTeamScore);
        m.addAttribute("guestTeamScore", guestTeamScore);

        return "match";
    }

    @PostMapping("/matchInProgress")
    public String handleMatch(Match match, Model model) throws IllegalArgumentException, InterruptedException {

        this.matchService.handleMatchEngine(match);


        return "match";
    }
}
