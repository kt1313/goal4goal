package pl.com.k1313.goal4goal.services;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.Team;
import pl.com.k1313.goal4goal.domain.repository.PlayerRepository;
import pl.com.k1313.goal4goal.domain.repository.TeamRepository;

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


}
