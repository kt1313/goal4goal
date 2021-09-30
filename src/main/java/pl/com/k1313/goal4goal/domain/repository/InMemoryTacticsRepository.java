package pl.com.k1313.goal4goal.domain.repository;

import pl.com.k1313.goal4goal.domain.Tactics;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTacticsRepository implements TacticsRepository {

    Map<Integer, Tactics> tacticsHashMap = new HashMap<>();

    public InMemoryTacticsRepository() {
    }


    @Override
    public Collection<Tactics> getAllTactics() {
        return tacticsHashMap.values();
    }


    @Override
    public void setTactics(Tactics tactics) {
        tactics.setTacticsId(getNewTacticsId());
        tacticsHashMap.put(tactics.getTacticsId(),tactics);
    }
    @Override
    @PostConstruct
    public void createTactics() {
        setTactics(new Tactics("Counter-Attack", 1));
        setTactics(new Tactics("Pressing", 1));
        setTactics(new Tactics("Long Ball", 1));

    }

    @Override
    public void setTacticsname(String tacticsName) {

    }

    private int getNewTacticsId() {
        if (tacticsHashMap.isEmpty()) {
            return 0;
        } else {
            Integer integer = tacticsHashMap.keySet().stream().max((o1, o2) -> o1.compareTo(o2)).get();
            integer=integer+1;
            return integer;
        }
    }
}
