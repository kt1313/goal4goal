package pl.com.k1313.goal4goal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String match(Model m) throws InterruptedException {

        //tu utworz Match i go zasejwuj na repo (to do testow tylko, bo0 nazwa hosta "na sile" i
        // sztuczny opponent
        Match match = this.teamService.createMatch();

        //to mozna wykorzystac w przyszlosci kiedy bedzie cala baza danych
        Match matchReal = this.matchRepository.findAll()
                .stream().filter(match1 -> match1.isInProgress()).findFirst().get();

//tu naglowek, nazwy druzyn i wynik
        String hostTeamName = match.getMatchTeams().get(0).getTeamName();
        String guestTeamName = match.getMatchTeams().get(1).getTeamName();
        Integer hostTeamScore = match.getHostScore();
        Integer guestTeamScore = match.getGuestScore();


        m.addAttribute("hostTeamName", hostTeamName);
        m.addAttribute("guestTeamName", guestTeamName);
        m.addAttribute("hostTeamScore", hostTeamScore);
        m.addAttribute("guestTeamScore", guestTeamScore);

//silnik meczowy
        List<String> matchCommentary = this.matchService.handleMatchEngine(matchReal);
        m.addAttribute("matchCommentary", matchCommentary);

        return "match";
    }

    @RequestMapping(value="/matchIn-Progress", method = RequestMethod.GET)
    public String handleMatch(ModelMap map) throws InterruptedException {

        Match match = this.matchRepository.findAll()
                .stream().filter(Match::isInProgress).findFirst().get();
        List<String> matchCommentary=this.matchService.handleMatchEngine(match);
         map.addAttribute("matchCommentary", matchCommentary);

        return "match :: #matchInProgress1";
    }
//    @RequestMapping(value="/matchInProgress", method= RequestMethod.GET)
//    public String getEventCount(ModelMap map) {
//        // TODO: retrieve the new value here so you can add it to model map
//        map.addAttribute("numDeviceEventsWithAlarm", count);
//
//        // change "myview" to the name of your view
//        return "match :: #matchInProgress";
//    }
}
