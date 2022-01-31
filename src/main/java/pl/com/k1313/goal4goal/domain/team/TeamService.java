package pl.com.k1313.goal4goal.domain.team;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.controllers.dto.First11DTO;
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
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public String[][] setUpFirst11(List <Player> firstsquadplayers) {
        String[][] first11Table = new String[5][4];
        first11Table[0][0]="0";
        first11Table[0][1]="right Wingback";
        first11Table[0][2]="right Winger";
        first11Table[0][3]="3";
        first11Table[1][0]="4";
        first11Table[1][1]="right Centreback";
        first11Table[1][2]="centre Midfielder Defending";
        first11Table[1][3]="right Forward";
        first11Table[2][0]="goalkeeper";
        first11Table[2][1]="centreback";
        first11Table[2][2]="centre Midfielder";
        first11Table[2][3]="centre Forward";
        first11Table[3][0]="12";
        first11Table[3][1]="left Centreback";
        first11Table[3][2]="centre Midfielder Attacking";
        first11Table[3][3]="left Forward";
        first11Table[4][0]="17";
        first11Table[4][1]="left Wingback";
        first11Table[4][2]="left Winger";
        first11Table[4][3]="20";

        String[][] first11FinalTable = new String[5][4];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 4; y++) {

                for (Player player : firstsquadplayers) {
                    if (first11FinalTable[x][y]==null) {
                        Position playerPosition=player.getPosition();
                        String playerPos=String.valueOf(playerPosition);
                        if (first11Table[x][y]==playerPos) {
                            first11FinalTable[x][y] = player.getFirstName() + " " + player.getLastName();
                            System.out.println("TeamService_setUpFirst11: "+player);
                            System.out.println(first11FinalTable);
                        }
                    }
                }
            }
        }
        return first11FinalTable;
    }

}