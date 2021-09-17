package pl.com.k1313.goal4goal.domain.repository;

import jdk.jshell.spi.ExecutionControl;
import pl.com.k1313.goal4goal.domain.Player;

import javax.annotation.PostConstruct;
import java.util.Collection;

public interface PlayerRepository {
    void hirePlayer(String name, int age);

    Collection<Player> getAllPlayers() throws ExecutionControl.NotImplementedException;

    Player getPlayer(String name) throws ExecutionControl.NotImplementedException;

    void firePlayer(String name) throws ExecutionControl.NotImplementedException;

    void create();
}
