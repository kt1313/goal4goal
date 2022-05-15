package pl.com.k1313.goal4goal.domain.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
import pl.com.k1313.goal4goal.domain.player.Position;

import java.util.Collection;
import java.util.Collections;
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
        MatchTeam autoOppFirst11=new MatchTeam();

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
        //najpierw wybiera najlepszego goalkeepera, nadaj pozycje i dodaje do First11
        Player first11goalkeeper = autoOpponentTeam.getMatchTeamPlayers().stream()
                .max(Comparator.comparing(Player::getGoalkeeping))
                .get();
        //ustala mu Position
        first11goalkeeper.setPosition(Position.GK);
        //dodaje do First11
        autoOppFirst11.getMatchTeamPlayers().add(first11goalkeeper);
        
        //potem hmmm zakladamy ustawienie 4-4-2, wiec 4 defensorow, nadaj pozycje i dodaje do First11

        List<Player> listOfDefendors = autoOpponentTeam.getMatchTeamPlayers().stream()
                .sorted(Comparator.comparingInt(Player::getTackling)
                        .reversed()).limit(4)
                .collect(Collectors.toList());
        //ustalamy im pozycje
        listOfDefendors.get(0).setPosition(Position.RCB);
        listOfDefendors.get(1).setPosition(Position.LCB);
        listOfDefendors.get(2).setPosition(Position.RWB);
        listOfDefendors.get(3).setPosition(Position.LWB);
        //dodajemy do First11
        for (Player p :
                listOfDefendors) {
            autoOppFirst11.getMatchTeamPlayers().add(p);
        }

        listOfDefendors.forEach(System.out::println);

        // potem 4 pomocnikow . i tu trzeba znalezc 4 gdy suma ich BallControl i Passingu jest max

        List<Player> listOfMidfieldersPre = autoOpponentTeam.getMatchTeamPlayers().stream()
                .sorted(Comparator.comparingInt(p -> p.getBallControl() + p.getPassing()))
                .collect(Collectors.toList());
        Collections.reverse(listOfMidfieldersPre);
        List<Player> listOfMidfielders=listOfMidfieldersPre.stream().limit(4).collect(Collectors.toList());

        listOfMidfielders.get(0).setPosition(Position.CMA);
        listOfMidfielders.get(1).setPosition(Position.CMD);
        listOfMidfielders.get(2).setPosition(Position.RW);
        listOfMidfielders.get(3).setPosition(Position.LW);

        for (Player p :
                listOfMidfielders) {
            autoOppFirst11.getMatchTeamPlayers().add(p);
        }
        listOfMidfielders.forEach(System.out::println);

        // potem 2 napastnikow
        List<Player> listOfAttackers = autoOpponentTeam.getMatchTeamPlayers().stream()
                .sorted(Comparator.comparingInt(Player::getAttacking)
                .reversed()).limit(2)
                .collect(Collectors.toList());

        listOfAttackers.get(0).setPosition(Position.RF);
        listOfAttackers.get(1).setPosition(Position.LF);

        for (Player p :
                listOfAttackers) {
            autoOppFirst11.getMatchTeamPlayers().add(p);
        }
        listOfAttackers.forEach(System.out::println);

    }

}
