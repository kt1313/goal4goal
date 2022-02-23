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
        Match match=this.teamService.createMatch();

        String hostTeamName=match.getMatchteams().get(0).getTeamName();
        String guestTeamName=match.getMatchteams().get(1).getTeamName();
        Integer hostTeamScore=match.getHostScore();
        Integer guestTeamScore=match.getGuestScore();

        m.addAttribute("hostTeamName", hostTeamName);
        m.addAttribute("guestTeamName", guestTeamName);
        m.addAttribute("hostTeamScore", hostTeamScore);
        m.addAttribute("guestTeamScore", guestTeamScore);

        return "match";
    }

    @PostMapping("/goalScored")
    public String goalScored(MatchTeam matchTeam, Model model) throws IllegalArgumentException {

        MatchScore matchScore = new MatchScore();
        Match match = this.matchRepository.findAll().stream().filter(Match::isInProgress).findFirst().get();

        if (matchTeam.equals(match.getMatchteams().get(0))) {
            match.setHostScore(match.getHostScore() + 1);
        } else if (matchTeam.equals(match.getMatchteams().get(1))) {
            match.setGuestScore(match.getGuestScore() + 1);
        } else {
            throw new IllegalArgumentException("Błędny zespół");
        }
        model.addAttribute("matchScore", matchScore);
        this.matchRepository.save(match);
        return "match";
    }


}
