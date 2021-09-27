package pl.com.k1313.goal4goal.domain.repository;

import jdk.jshell.spi.ExecutionControl;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.Tactics;

import java.util.Collection;

public interface TacticsRepository {

    Collection<Tactics> getAllTactics() throws ExecutionControl.NotImplementedException;

}
