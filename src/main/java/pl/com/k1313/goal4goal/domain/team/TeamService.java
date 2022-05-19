package pl.com.k1313.goal4goal.domain.team;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.controllers.PlayerController;
import pl.com.k1313.goal4goal.controllers.dto.First11DTO;
import pl.com.k1313.goal4goal.domain.match.Match;
import pl.com.k1313.goal4goal.domain.match.MatchRepository;
import pl.com.k1313.goal4goal.domain.match.MatchService;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
import pl.com.k1313.goal4goal.domain.player.Position;
import pl.com.k1313.goal4goal.domain.team.Tactics;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private TeamRepository teamRepository;
    @Autowired
    private MatchTeamRepository matchTeamRepository;
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

//    @Autowired
//    public MatchTeam matchTeam;

    @Autowired
    public MatchService matchService;

    public String[][] setUpFirst11(List<Player> firstsquadplayers) {
        String[][] first11Table = new String[5][4];
        first11Table[0][0] = "0";
        first11Table[0][1] = "right Wingback";
        first11Table[0][2] = "right Winger";
        first11Table[0][3] = "3";
        first11Table[1][0] = "4";
        first11Table[1][1] = "right Centreback";
        first11Table[1][2] = "centre Midfielder Defending";
        first11Table[1][3] = "right Forward";
        first11Table[2][0] = "goalkeeper";
        first11Table[2][1] = "centreback";
        first11Table[2][2] = "centre Midfielder";
        first11Table[2][3] = "centre Forward";
        first11Table[3][0] = "12";
        first11Table[3][1] = "left Centreback";
        first11Table[3][2] = "centre Midfielder Attacking";
        first11Table[3][3] = "left Forward";
        first11Table[4][0] = "17";
        first11Table[4][1] = "left Wingback";
        first11Table[4][2] = "left Winger";
        first11Table[4][3] = "20";

        String[][] first11FinalTable = new String[5][4];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 4; y++) {

                for (Player player : firstsquadplayers) {
                    if (first11FinalTable[x][y] == null) {
                        Position playerPosition = player.getPosition();
                        String playerPos = String.valueOf(playerPosition);
                        if (first11Table[x][y] == playerPos) {
                            first11FinalTable[x][y] = player.getFirstName() + " " + player.getLastName();
                            System.out.println("TeamService_setUpFirst11: " + player);
                            System.out.println(first11FinalTable);
                        }
                    }
                }
            }
        }
        calculateFirst11FormationsValues();
        return first11FinalTable;
    }

    //oblicza sumę ataku (i nie tylko) pierwszej 11
    public List<Integer> calculateFirst11FormationsValues() {

        int first11Attack = 0;
        int first11Defence = 0;
        int first11Midfield = 0;

        //pobiera tylko zawodników z pierwszej 11
        List<Player> first11players = this.playerRepository.findAll().stream()
                .filter(Player::isFirstSquadPlayer)
                .collect(Collectors.toList());
        //dla każdego sprawdza czy jest w ataku, pomocy czy obronie lub bramkarz
        // i w zależności od tego sumuje procent jego umiejętności attacking
        for (Player player : first11players
        ) {
            if (player.getPosition().equals(Position.LF) ||
                    (player.getPosition().equals(Position.CF)) ||
                    (player.getPosition().equals(Position.RF))) {
                first11Attack += player.getAttacking();
                first11Midfield += (player.getBallControl() * 0.35 + player.getPassing() * 0.35);
                first11Defence += player.getTackling() * 0.5;
            }
            if (player.getPosition().equals(Position.LW) ||
                    (player.getPosition().equals(Position.CMA)) ||
                    (player.getPosition().equals(Position.CM)) ||
                    (player.getPosition().equals(Position.CMD)) ||
                    (player.getPosition().equals(Position.RW))) {
                first11Attack += (player.getAttacking() * 0.75);
                first11Midfield += (player.getBallControl() * 0.5 + player.getPassing() * 0.5);
                first11Defence += player.getTackling() * 0.5;
            }
            if (player.getPosition().equals(Position.LWB) ||
                    (player.getPosition().equals(Position.LCB)) ||
                    (player.getPosition().equals(Position.CB)) ||
                    (player.getPosition().equals(Position.RCB)) ||
                    (player.getPosition().equals(Position.RWB))) {
                first11Attack += (player.getAttacking() * 0.5);
                first11Midfield += (player.getBallControl() * 0.25 + player.getPassing() * 0.25);
                first11Defence += player.getTackling() * 0.5;
            }
            if (player.getPosition().equals(Position.GK)) {
                first11Attack += (player.getAttacking() * 0.1);
                first11Midfield += (player.getBallControl() * 0.1 + player.getPassing() * 0.1);
                first11Defence += player.getTackling() * 0.5;
            }
        }
        List<Integer> formationsValues = new ArrayList<Integer>(List.of(first11Attack, first11Midfield, first11Defence
        ));
        System.out.println("TeamServ, calculateFirst11FormVal, Wartość formacji: "
                + " Atak: " + formationsValues.get(0)
                + " Pomoc: " + formationsValues.get(1)
                + " Obrona: " + formationsValues.get(2));

        return formationsValues;
    }

    public List<Team> findAllTeams() {
        return this.teamRepository.findAll();
    }

    public List<MatchTeam> findAllMatchTeams() {
        return this.matchTeamRepository.findAll();
    }


    public Match createMatch() {
        Match match;
        MatchTeam userTeam = this.matchService.createUserTeam();
        MatchTeam oppTeam = this.matchService.createAutoOpponent();


//        Optional<MatchTeam> hostTeamOpt = findAllMatchTeams().stream()
//                .filter(matchTeam1 -> matchTeam1.getTeamName()
//                        .equals("Tres Tigres"))
//                .findFirst();
//        String hostTeamName = hostTeamOpt.get().getTeamName();
        Optional<MatchTeam> hostTeamOpt = Optional.ofNullable(userTeam);
        String hostTeamName = userTeam.getTeamName();

//        Optional<MatchTeam> guestTeamOpt = findAllMatchTeams()
//                .stream().filter(matchTeam -> matchTeam.getTeamName().equals("Cream Team FC"))
//                .findFirst();
//        String guestTeamName = guestTeamOpt.get().getTeamName();
        Optional<MatchTeam> guestTeamOpt = Optional.ofNullable(oppTeam);
        String guestTeamName = oppTeam.getTeamName();


        List<MatchTeam> matchTeamList = new ArrayList<>();
        matchTeamList.add(hostTeamOpt.get());
        matchTeamList.add(guestTeamOpt.get());

        match = new Match(matchTeamList, true);
        this.matchRepository.save(match);

        return match;
    }

    //wywolujemy metode tworzaca nowego auto przeciwnika
    public MatchTeam autoCreateOpponentTeam() {
        MatchTeam autoOpponentTeam = new MatchTeam();
        autoOpponentTeam.setTeamName("Auto Oppo Cream-Team ");
        MatchTeam autoOppFirst11 = new MatchTeam();
        autoOppFirst11.setTeamName(autoOpponentTeam.getTeamName());
//        List<Player> list = new ArrayList<Player>();
//        list=autoOpponentTeam.getMatchTeamPlayers();

        //on tworzy swoich zawodnikow(czy ma ich zapisywac w repo???hmmm) i 2 goalkeeperow
        // i przypisac do zespolu
        for (int i = 0; i < 13; i++) {
            Player newPlayer = this.playerService.autoCreatePlayer();
            autoOpponentTeam.getMatchTeamPlayers().add(newPlayer);
        }
        System.out.println(autoOpponentTeam);

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
        first11goalkeeper.setFirstSquadPlayer(true);
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
            //dodaj do FirstSquad
            p.setFirstSquadPlayer(true);

        }

        listOfDefendors.forEach(System.out::println);

        // potem 4 pomocnikow . i tu trzeba znalezc 4 gdy suma ich BallControl i Passingu jest max

        List<Player> listOfMidfieldersPre = autoOpponentTeam.getMatchTeamPlayers().stream()
                .sorted(Comparator.comparingInt(p -> p.getBallControl() + p.getPassing()))
                .collect(Collectors.toList());
        Collections.reverse(listOfMidfieldersPre);
        List<Player> listOfMidfielders = listOfMidfieldersPre.stream().limit(4).collect(Collectors.toList());

        listOfMidfielders.get(0).setPosition(Position.CMA);
        listOfMidfielders.get(1).setPosition(Position.CMD);
        listOfMidfielders.get(2).setPosition(Position.RW);
        listOfMidfielders.get(3).setPosition(Position.LW);

        for (Player p :
                listOfMidfielders) {
            autoOppFirst11.getMatchTeamPlayers().add(p);
            //dodaj do FirstSquad
            p.setFirstSquadPlayer(true);
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
            //dodaj do FirstSquad
            p.setFirstSquadPlayer(true);
        }
        listOfAttackers.forEach(System.out::println);
        System.out.println(autoOppFirst11);
        return autoOppFirst11;
    }
}