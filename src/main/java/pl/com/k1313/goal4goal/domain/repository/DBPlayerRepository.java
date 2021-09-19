package pl.com.k1313.goal4goal.domain.repository;

import jdk.jshell.spi.ExecutionControl;
import pl.com.k1313.goal4goal.domain.Player;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//@Repository
public class DBPlayerRepository implements PlayerRepository {

    Map<String, Player> players = new HashMap<>();

    @Override
    public void hirePlayer(String name, int age) {
        System.out.println("Uzywam DB");
        ;
    }

    @Override
    public Collection<Player> getAllPlayers() throws ExecutionControl.NotImplementedException {
        System.out.println("Uzywam DB");
        throw new ExecutionControl.NotImplementedException("not implemented");

    }

    @Override
    public Optional<Player> getPlayer(String name) throws ExecutionControl.NotImplementedException {
        System.out.println("Uzywam DB");
        throw new ExecutionControl.NotImplementedException("not implemented");

    }

    @Override
    public void firePlayer(Integer id) throws ExecutionControl.NotImplementedException {
        System.out.println("Uzywam DB");
        throw new ExecutionControl.NotImplementedException("not implemented");

    }

    @Override
    @PostConstruct
    public void create() {

    }

    @Override
    public void hirePlayer(Player player) throws ExecutionControl.NotImplementedException {
        System.out.println("Uzywam DB");
        throw new ExecutionControl.NotImplementedException("not implemented");

    }

    @Override
    public Player getPlayerById(Integer id) throws ExecutionControl.NotImplementedException {
        System.out.println("Uzywam DB");
        throw new ExecutionControl.NotImplementedException("not implemented");
    }


}
