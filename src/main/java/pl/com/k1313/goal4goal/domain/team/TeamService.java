package pl.com.k1313.goal4goal.domain.team;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
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

    public String[][] setUpfirst11() {
        String[][] first11Table = new String[5][4];
        List<Player> firstsquadplayers = this.playerService.findAllPlayers().stream()
                .filter(Player::isFirstSquadPlayer)
                .collect(Collectors.toList());
        for (int x=0;x<5;x++){
            for (int y=0;y<4;y++){
                for (Player player:firstsquadplayers) {
                    if (first11Table[x][y].equals(player.getPosition())){
                        first11Table[x][y]=player.getPosition().toString();
                    }
                }
            }
        }
        return first11Table;
    }


}
