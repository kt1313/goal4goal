package pl.com.k1313.goal4goal.domain.player;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.player.PlayerTask;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerTaskRepository;

import java.util.List;
import java.util.Random;

@Service
public class PlayerTaskService {

    @Autowired
    PlayerTaskRepository playerTaskRepository;

    @Autowired
    PlayerRepository playerRepository;

    final static Random rand = new Random();


    public void assignRandomTask(String playerName) throws ExecutionControl.NotImplementedException {
        List<PlayerTask> allTasks = playerTaskRepository.getAllPlayerTasks();
        PlayerTask randomTask = allTasks.get(rand.nextInt(allTasks.size()));
        playerRepository.getPlayer(playerName).ifPresent(player ->player.setPlayerTask(randomTask));
        playerTaskRepository.removePlayerTask(randomTask);

    }
}
