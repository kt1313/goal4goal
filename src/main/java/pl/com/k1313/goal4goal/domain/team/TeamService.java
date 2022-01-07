package pl.com.k1313.goal4goal.domain.team;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
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
    private PlayerService playerService;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public String[][] setUpFirst11(List <Player> firstsquadplayers) {
        String[][] first11Table = new String[5][4];
        first11Table[0][0]="0";
        first11Table[0][1]="Right Wingback";
        first11Table[0][2]="2";
        first11Table[0][3]="3";
        first11Table[1][0]="4";
        first11Table[1][1]="5";
        first11Table[1][2]="6";
        first11Table[1][3]="7";
        first11Table[2][0]="Goalkeeper";
        first11Table[2][1]="Centre Back";
        first11Table[2][2]="Centre Midfielder";
        first11Table[2][3]="11";
        first11Table[3][0]="12";
        first11Table[3][1]="13";
        first11Table[3][2]="14";
        first11Table[3][3]="Left Forward";
        first11Table[4][0]="17";
        first11Table[4][1]="18";
        first11Table[4][2]="19";
        first11Table[4][3]="20";

        String[][] first11FinalTable = new String[5][4];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 4; y++) {

                for (Player player : firstsquadplayers) {
//                    System.out.println("x x x x x x x x x");
//                    System.out.println("x = " + x);
//                    System.out.println("y = " + y);
                    System.out.println(player.getFirstName() + " " + player.getLastName() + " " + player.getPosition());
                    if (first11FinalTable[x][y] != null) {
                        Position playerPosition=player.getPosition();
                        String playerPos=Position.toString(playerPosition);
                        if (first11FinalTable[x][y].equals(playerPos)) {
                            first11FinalTable[x][y] = player.getFirstName() + " " + player.getLastName();
                            System.out.println(player);
                        }
                    }
                }
            }
        }
        return first11FinalTable;
    }
}