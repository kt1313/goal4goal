package pl.com.k1313.goal4goal.services;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public List<Player> getAllPlayers() throws ExecutionControl.NotImplementedException {
        return new ArrayList<>(playerRepository.getAllPlayers());
    }

    public void savePlayer(Player player) throws ExecutionControl.NotImplementedException {
playerRepository.hirePlayer(player);
    }

    public Player getPlayer(Integer id) throws ExecutionControl.NotImplementedException {
        return playerRepository.getPlayerById(id);
    }

    public void firePlayer(Integer playerId) throws ExecutionControl.NotImplementedException {
        playerRepository.firePlayer(playerId);
    }
}
