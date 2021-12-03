package pl.com.k1313.goal4goal.domain.team;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.team.Tactics;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    public List<Player> getAllPlayers() throws ExecutionControl.NotImplementedException {
        return new ArrayList<>(playerRepository.getAllPlayers());
    }
    public List<Tactics> getAllTactics() throws ExecutionControl.NotImplementedException {
        return new ArrayList<>(teamRepository.getAllTactics());
    }
    public String getTeamName() throws ExecutionControl.NotImplementedException {
        return teamRepository.getTeamName();
    }


    public String getManagerName() throws ExecutionControl.NotImplementedException {
        return teamRepository.getManagerName();
    }


}
