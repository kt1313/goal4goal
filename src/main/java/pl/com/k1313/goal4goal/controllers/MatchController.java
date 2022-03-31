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
import pl.com.k1313.goal4goal.domain.match.MatchService;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.team.*;

import java.util.HashMap;

@Controller
@RequestMapping("/match")
public class MatchController {

    int matchMinute = 0;
    private MatchService matchService;
    private MatchService matchService2;

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

        //tutaj ma odczytac dane do naglowka
        this.matchService.readDataForHeader();

            Match match = this.teamService.createMatch();

//tu naglowek, nazwy druzyn i wynik
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

//    @RequestMapping(value = "/matchIn-Progress", method = RequestMethod.GET)
    @PostMapping("/matchInProgress")
    public String handleMatch(ModelMap map, Model m) throws InterruptedException {
//ma pobrac JUŻ utworzony match z druzynami
        Match matchReal = this.matchRepository.findAll()
                .stream().filter(Match::isInProgress).findFirst().get();

        //ma teraz ROZEGRAC ten mecz
        HashMap<Integer, String> matchCommentary = this.matchService.handleMatchEngine(matchReal);
        map.addAttribute("matchCommentary", matchCommentary);

//tu naglowek, nazwy druzyn i wynik
        String hostTeamName = matchReal.getMatchTeams().get(0).getTeamName();
        String guestTeamName = matchReal.getMatchTeams().get(1).getTeamName();
        Integer hostTeamScore = matchReal.getHostScore();
        Integer guestTeamScore = matchReal.getGuestScore();
        m.addAttribute("hostTeamName", hostTeamName);
        m.addAttribute("guestTeamName", guestTeamName);
        m.addAttribute("hostTeamScore", hostTeamScore);
        m.addAttribute("guestTeamScore", guestTeamScore);

        return "match";
    }


}
