package pl.com.k1313.goal4goal.domain.repository;

import jdk.jshell.spi.ExecutionControl;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.Tactics;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTacticsRepository implements TacticsRepository {

    Map<Integer, Tactics> tactics = new HashMap<>();

    public InMemoryTacticsRepository() {
    }


    @Override
    public Collection<Tactics> getAllTactics() {
        return tactics.values();
    }

}
