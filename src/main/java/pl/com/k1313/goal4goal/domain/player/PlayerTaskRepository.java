package pl.com.k1313.goal4goal.domain.player;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import pl.com.k1313.goal4goal.domain.player.PlayerTask;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class PlayerTaskRepository {

    Random rand = new Random();

    List<PlayerTask> taskList = new ArrayList<>();

    public void createPlayerTask(String description) {
        taskList.add(new PlayerTask(description));
    }

    public List<PlayerTask> getAllPlayerTasks() {
        return taskList;
    }

    public void removePlayerTask(PlayerTask playerTask) {
        taskList.remove(playerTask);
    }

    @PostConstruct
    public void init() {


    }
@Scheduled(fixedDelayString = "${playerTaskCreationDelay}")
    public void createRandomPlayerTask() {
        List<String> descriptions = new ArrayList<>();
        descriptions.add("Biegaj jak kon.");
        descriptions.add("Obij scianke pilka po stokroc");
        descriptions.add("Slalom gigant z pilka przy nodze do zdychu.");
        descriptions.add("I pod gorke, az uda sie rozpekna.");

    String description = descriptions.get(rand.nextInt(descriptions.size()));
//    System.out.println("Utworzylem zadanie o opisie: " + description);
    createPlayerTask(description);

    }


    @Override
    public String toString() {
        return "PlayerTaskRepository{" +
                "taskList=" + taskList +
                '}';
    }
}

