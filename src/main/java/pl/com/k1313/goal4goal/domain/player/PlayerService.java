package pl.com.k1313.goal4goal.domain.player;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public List<Player> getAllPlayers() throws ExecutionControl.NotImplementedException {
        return new ArrayList<>(playerRepository.getAllPlayers());
    }

    public void hirePlayer(Player player) throws ExecutionControl.NotImplementedException {
        playerRepository.hirePlayer(player);
    }

    public Player getPlayer(Integer id) throws ExecutionControl.NotImplementedException {
        return playerRepository.getPlayerById(id);
    }

    public void firePlayer(Integer playerId) throws ExecutionControl.NotImplementedException {
        playerRepository.firePlayer(playerId);
    }

    public List<Player> getFirstSquad(boolean firstSquadPlayerBoolean) throws ExecutionControl.NotImplementedException {
        return new ArrayList<>(playerRepository.getFirstSquad( firstSquadPlayerBoolean));
    }

    public void setPlayerFor11(Integer playerId) throws ExecutionControl.NotImplementedException{
        playerRepository.callPlayerTo11(playerId);
    }

    public Player getPlayerById(long id) throws ExecutionControl.NotImplementedException {
        return this.playerRepository.getPlayerById(id);
    }
}
