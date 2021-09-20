package pl.com.k1313.goal4goal.domain.repository;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.com.k1313.goal4goal.domain.Player;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;

//@Repository
public class InMemoryRepository implements PlayerRepository {

    Map<Integer, Player> players = new HashMap<>();

    public InMemoryRepository() {
    }

    @Override
    public void hirePlayer(String name, int age) {
        Player newPlayer = new Player(name, age);
        newPlayer.setId(getNewId());
        players.put(newPlayer.getId(), newPlayer);
    }

    @Override
    public void hirePlayer(Player player) {
        player.setId(getNewId());
        players.put(player.getId(), player);
    }

    private int getNewId() {
        if (players.isEmpty()) {
            return 0;
        } else {
//            Integer integer = players.keySet().stream().max(Integer::max).get();
//            integer=integer+1;
//            return integer;
            Integer integer=players.size();
            return integer;
        }
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return players.values();
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        Optional<Player> playerByName = players.values().stream().filter(player -> player.getFirstName().equals(name)).findAny();
        return playerByName;
    }

    @Override
    public void firePlayer(Integer id) {
        players.remove(id);
    }

    @Override
    @PostConstruct
    public void create() {
        hirePlayer("Zenon", 22);
        hirePlayer("Sebix", 24);
    }



    @Override
    public Player getPlayerById(Integer id) throws ExecutionControl.NotImplementedException {
        return players.get(id);
    }

    @Override
    public String toString() {
        return "PlayerRepository {" +
                "players=" + players +
                '}';
    }
}
