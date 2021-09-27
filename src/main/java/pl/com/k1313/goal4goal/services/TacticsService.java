package pl.com.k1313.goal4goal.services;


import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.Tactics;
import pl.com.k1313.goal4goal.domain.repository.TacticsRepository;

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
