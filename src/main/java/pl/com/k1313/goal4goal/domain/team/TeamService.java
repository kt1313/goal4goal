package pl.com.k1313.goal4goal.domain.team;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.controllers.PlayerController;
import pl.com.k1313.goal4goal.controllers.dto.First11DTO;
import pl.com.k1313.goal4goal.domain.match.MatchService;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
import pl.com.k1313.goal4goal.domain.player.Position;
import pl.com.k1313.goal4goal.domain.team.Tactics;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private TeamRepository teamRepository;

    @Autowired
    private PlayerController playerController;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Autowired
    public MatchTeam matchTeam;

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
                first11Midfield+=(player.getBallControl()*0.35+player.getPassing()*0.35);
                first11Defence+=player.getTackling()*0.5;
            }
            if (player.getPosition().equals(Position.LW) ||
                    (player.getPosition().equals(Position.CMA)) ||
                    (player.getPosition().equals(Position.CM)) ||
                    (player.getPosition().equals(Position.CMD)) ||
                    (player.getPosition().equals(Position.RW))) {
                first11Attack += (player.getAttacking() * 0.75);
                first11Midfield+=(player.getBallControl()*0.5+player.getPassing()*0.5);
                first11Defence+=player.getTackling()*0.5;
            }
            if (player.getPosition().equals(Position.LWB) ||
                    (player.getPosition().equals(Position.LCB)) ||
                    (player.getPosition().equals(Position.CB)) ||
                    (player.getPosition().equals(Position.RCB)) ||
                    (player.getPosition().equals(Position.RWB))) {
                first11Attack += (player.getAttacking() * 0.5);
                first11Midfield+=(player.getBallControl()*0.25+player.getPassing()*0.25);
                first11Defence+=player.getTackling()*0.5;
            }
            if (player.getPosition().equals(Position.GK)) {
                first11Attack += (player.getAttacking() * 0.1);
                first11Midfield+=(player.getBallControl()*0.1+player.getPassing()*0.1);
                first11Defence+=player.getTackling()*0.5;
            }
        }
        List<Integer> formationsValues = new ArrayList<Integer>(List.of(first11Defence
                , first11Midfield, first11Attack));
        System.out.println("TeamServ, calculateFirst11FormVal, Wartość formacji: "
                + " Defensywa: " + formationsValues.get(0)
                + " Pomoc: " + formationsValues.get(1)
                + " Atak: " + formationsValues.get(2));
        this.matchService.createDefaultOppTeam();
//        this.matchService.createUserTeam();
        return formationsValues;
    }
}