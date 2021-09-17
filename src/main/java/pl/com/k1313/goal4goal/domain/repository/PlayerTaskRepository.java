package pl.com.k1313.goal4goal.domain.repository;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Repository;
import pl.com.k1313.goal4goal.domain.PlayerTask;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerTaskRepository {

    List<PlayerTask> taskList=new ArrayList<>();

    public void createPlayerTask(String description){
        taskList.add(new PlayerTask(description));
    }

    public List<PlayerTask> getAllPlayerTasks(){
        return taskList;
    }

    public void removePlayerTask(PlayerTask playerTask){
        taskList.remove(playerTask);
    }

    @PostConstruct
    public  void init(){
        createPlayerTask("Biegaj jak kon.");
        createPlayerTask("Obij sciane pilka po stokroc.");
        createPlayerTask("Obij sciane pilka po stokroc.");


    }

    @Override
    public String toString() {
        return "PlayerTaskRepository{" +
                "taskList=" + taskList +
                '}';
    }
}

