package pl.com.k1313.goal4goal.domain.team;

import jdk.jshell.spi.ExecutionControl;
import pl.com.k1313.goal4goal.domain.team.Tactics;

import java.util.Collection;

public interface TacticsRepository {

    Collection<Tactics> getAllTactics() throws ExecutionControl.NotImplementedException;

    void createTactics();
    void setTacticsname(String tacticsName);
    void setTactics(Tactics tactics);

}
