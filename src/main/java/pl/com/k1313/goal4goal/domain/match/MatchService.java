package pl.com.k1313.goal4goal.domain.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
import pl.com.k1313.goal4goal.domain.player.Position;
import pl.com.k1313.goal4goal.domain.team.MatchTeam;
import pl.com.k1313.goal4goal.domain.team.MatchTeamRepository;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;
import pl.com.k1313.goal4goal.domain.team.TeamService;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private MatchTeamRepository matchTeamRepository;
    @Autowired
    private MatchRepository matchRepository;


    public MatchTeam createDefaultOppTeam() {
        MatchTeam defaultOppTeam = new MatchTeam("Cream Team FC", 30, 40, 50, 60);
        System.out.println("MatchService, createDefaultOppTeam, defTeam: " + defaultOppTeam.toString());
        this.matchTeamRepository.save(defaultOppTeam);
        return defaultOppTeam;
    }

    //w przyszłości będzie podawane jako parametr nickname usera
    public MatchTeam createUserTeam() {

        List<Integer> userTeamValues = this.teamService.calculateFirst11FormationsValues();
        Player userGoalkeeper = this.playerRepository.findAll()
                .stream().filter(Player::isFirstSquadPlayer)
                .filter(player -> player.getPosition().equals(Position.GK))
                .findFirst().get();

        Integer userGoalkeeperSkillInt = userGoalkeeper.getGoalkeeping();

        MatchTeam userTeam = new MatchTeam(
                //tu bedzie z repository UserRepo wziete teamName
                "Tres Tigres"
                , userTeamValues.get(0)
                , userTeamValues.get(1)
                , userTeamValues.get(2)
                , userGoalkeeperSkillInt);
        this.matchTeamRepository.save(userTeam);
        return userTeam;
    }

    public MatchScore getResult(Match match) {

        Long id = match.getId();
        MatchScore result = this.matchRepository.getById(id).matchScore;
        return result;
    }

//    public MatchScore goalScored(MatchTeam matchTeam) throws IllegalArgumentException {
//
//        Match matchInProgress = this.matchRepository.findAll().stream()
//                .filter(match -> match.inProgress)
//                .findFirst().get();
//        MatchScore matchInProgressMatchScore = matchInProgress.getMatchScore();
//
//        int updatedHostScore = matchInProgressMatchScore.getHostScore();
//        int updatedGuestScore = matchInProgressMatchScore.getGuestScore();
//
//        if (matchInProgress.getHostTeam().equals(matchTeam)) {
//            updatedHostScore++;
//        } else if (matchInProgress.getGuestTeam().equals(matchTeam)) {
//            updatedGuestScore++;
//        }
//        MatchScore matchScore=new MatchScore(updatedHostScore, updatedGuestScore);
//        //zapisac teraz czy po meczu??? teraz, bo bedzie pobierany przy nast bramce
//        this.matchRepository.save(matchInProgress);
//        return matchScore;
//    }
}
