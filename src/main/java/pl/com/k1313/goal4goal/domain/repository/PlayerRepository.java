package pl.com.k1313.goal4goal.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.com.k1313.goal4goal.domain.Player;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PlayerRepository {

    Map<String, Player> players = new HashMap<>();

    public PlayerRepository() {
    }

    public void hirePlayer(String name, int age) {
        players.put(name, new Player(name, age));
    }

    public Collection<Player> getAllPlayers() {
        return players.values();
    }

    public Player getPlayer(String name) {
        return players.get(name);
    }

    public void firePlayer(String name) {
        players.remove(name);
    }

        @PostConstruct
    public void create() {
        hirePlayer("Zenon", 22);
        hirePlayer("Sebix", 24);
    }

    @Override
    public String toString() {
        return "PlayerRepository {" +
                "players=" + players +
                '}';
    }
}
