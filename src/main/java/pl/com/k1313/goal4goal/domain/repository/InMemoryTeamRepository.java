package pl.com.k1313.goal4goal.domain.repository;

import jdk.jshell.spi.ExecutionControl;
import pl.com.k1313.goal4goal.domain.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTeamRepository implements TeamRepository {

    public InMemoryTeamRepository() {
    }

    Map<Integer, Player> forGamePlayers = new HashMap<>();

    @Override
    public void setUpGameSquad() throws ExecutionControl.NotImplementedException {
        //trzeba przy wybranych Playerach zrobic checkboxy zmieniajace flage, a ta
        //metoda odczytywala by tylko ich
//        return forGamePlayers;

    }

    @Override
    public Collection<Player> getAllGamePlayers() {
        return forGamePlayers.values();
    }

    @Override
    public Integer getTotalDefence() throws ExecutionControl.NotImplementedException {
//         forGamePlayers.keySet().stream().forEach(player ->player.getDefending().sum());
        return null;
    }

    @Override
    public Integer getTeamPlaymaking() throws ExecutionControl.NotImplementedException {
        return null;
    }

    @Override
    public Integer getTeamAttacking() throws ExecutionControl.NotImplementedException {
        return null;
    }

    @Override
    public Integer getTeamCounterAttacking() throws ExecutionControl.NotImplementedException {
        return null;
    }

    @Override
    public Integer getTeamSelfConfidence() throws ExecutionControl.NotImplementedException {
        return null;
    }

    @Override
    public Integer getTeamTactics() throws ExecutionControl.NotImplementedException {
        return null;
    }
}
