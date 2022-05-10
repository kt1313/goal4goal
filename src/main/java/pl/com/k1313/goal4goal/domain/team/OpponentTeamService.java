package pl.com.k1313.goal4goal.domain.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerService;

@Service
public class OpponentTeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerService playerService;
    //    @Autowired
    private MatchTeam matchTeam;

    public OpponentTeamService(TeamRepository teamRepository, PlayerRepository playerRepository, MatchTeam) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    //wywolujemy metode tworzaca nowego auo przeciwnika
    public void autoCreateOpponentTeam() {
        MatchTeam autoOpponentTeam = new MatchTeam();
        autoOpponentTeam.setTeamName("Auto Oppo Cream-Team ");
        //on tworzy swoich zawodnikow (czy ma ich zapisywac w repo???hmmm) i 2 goalkeeperow
        Player newPlayer = this.playerService.autoCreatePlayer();
        autoOpponentTeam.getMatchTeamPlayers().add(newPlayer);

        for (int i = 0; i < 2; i++) {
            Player newGoalkeeper = this.playerService.autoCreateGoalkeeper();
            autoOpponentTeam.getMatchTeamPlayers().add(newGoalkeeper);
        }


    }

}
