package pl.com.k1313.goal4goal.domain.team;


import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.team.Tactics;
import pl.com.k1313.goal4goal.domain.team.TacticsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TacticsService {

    @Autowired
    TacticsRepository tacticsRepository;

    public List<Tactics> getAllTactics() throws ExecutionControl.NotImplementedException {
        return new ArrayList<>(tacticsRepository.getAllTactics());
    }


}
