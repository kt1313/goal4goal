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
public class InMemoryPlayerRepository implements PlayerRepository {

    Map<Integer, Player> players = new HashMap<>();

    public InMemoryPlayerRepository() {
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
            Integer integer = players.keySet().stream().max((o1, o2) -> o1.compareTo(o2)).get();
            integer=integer+1;
            return integer;
//            Integer integer=players.size(); - nie dziala, bo gdy usuwamy wczesniejszego to rozmiar nie pasuje do Id
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
    public Player getPlayerById(Integer id) throws ExecutionControl.NotImplementedException {
        return players.get(id);
    }

    @Override
    public void firePlayer(Integer id) {
        players.remove(id);
    }

    @Override
    @PostConstruct
    public void create() {
        hirePlayer("Zenon Lewangoalski", 22);
        hirePlayer("Thomassist Mueller", 24);
    }


    @Override
    public String toString() {
        return "PlayerRepository {" +
                "players=" + players +
                '}';
    }
}