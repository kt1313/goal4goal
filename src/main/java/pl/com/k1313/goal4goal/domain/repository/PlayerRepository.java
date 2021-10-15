package pl.com.k1313.goal4goal.domain.repository;

import jdk.jshell.spi.ExecutionControl;
import pl.com.k1313.goal4goal.domain.Player;
import java.util.Collection;
import java.util.Optional;

public interface PlayerRepository {

    void hirePlayer(String name, int age);

    Collection<Player> getAllPlayers() throws ExecutionControl.NotImplementedException;

    Collection<Player> getFirstSquad(boolean firstSquadPlayerBoolean)throws ExecutionControl.NotImplementedException;;

    Optional<Player> getPlayer(String name) throws ExecutionControl.NotImplementedException;

    void firePlayer(Integer id) throws ExecutionControl.NotImplementedException;

    void create();

    void hirePlayer(Player player) throws ExecutionControl.NotImplementedException;

    Player getPlayerById(Integer id) throws ExecutionControl.NotImplementedException;

    void callPlayerTo11(Integer playerId)throws ExecutionControl.NotImplementedException;
}
