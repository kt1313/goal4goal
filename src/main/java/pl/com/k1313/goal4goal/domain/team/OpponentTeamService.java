package pl.com.k1313.goal4goal.domain.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public OpponentTeamService(TeamRepository teamRepository,
                               PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    //wywolujemy metode tworzaca nowego auto przeciwnika
    public void autoCreateOpponentTeam() {
        MatchTeam autoOpponentTeam = new MatchTeam();
        autoOpponentTeam.setTeamName("Auto Oppo Cream-Team ");

        //on tworzy swoich zawodnikow(czy ma ich zapisywac w repo???hmmm) i 2 goalkeeperow
        // i przypisac do zespolu
        for (int i = 0; i < 13; i++) {
            Player newPlayer = this.playerService.autoCreatePlayer();
            autoOpponentTeam.getMatchTeamPlayers().add(newPlayer);
        }
        for (int i = 0; i < 2; i++) {
            Player newGoalkeeper = this.playerService.autoCreateGoalkeeper();
            autoOpponentTeam.getMatchTeamPlayers().add(newGoalkeeper);
        }

        //teraz ma wybrac First 11
        //najpierw wybiera najlepszego goalkeepera
        Long goalkeeperID = autoOpponentTeam.getMatchTeamPlayers().stream()
                .max(Comparator.comparing(Player::getGoalkeeping))
                .get().getId();
        //potem hmmm zakladamy ustawienie 4-4-2, wiec 4 defensorow

        List<Player> sortedListByTackling = autoOpponentTeam.getMatchTeamPlayers().stream()
                .sorted(Comparator.comparingInt(Player::getTackling)
                        .reversed())
                .collect(Collectors.toList());
        Long defendorOneId = sortedListByTackling.get(0).getId();
        Long defendorTwoId = sortedListByTackling.get(1).getId();
        Long defendorThreeId = sortedListByTackling.get(2).getId();
        Long defendorFourId = sortedListByTackling.get(3).getId();

        sortedListByTackling.forEach(System.out::println);

        // potem 4 pomocnikow . i tu trzeba znalezc 4 gdy suma ich BallControl i Passingu jest max
        
        List<Player> sortedListByMidfield = autoOpponentTeam.getMatchTeamPlayers().stream()
                .sorted(Comparator.comparingInt(Player::getAttacking)
                        .reversed())
                .collect(Collectors.toList());

        // potem 2 napastnikow
        List<Player> sortedListByAttacking = autoOpponentTeam.getMatchTeamPlayers().stream()
                .sorted(Comparator.comparingInt(Player::getAttacking)
                        .reversed())
                .collect(Collectors.toList());
        Long attackerOneId=sortedListByAttacking.get(0).getId();
        Long attackerTwoId=sortedListByAttacking.get(1).getId();
    }

}
