package pl.com.k1313.goal4goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerTaskRepository;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerTaskService;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerTaskRepository playerTaskRepository;

    @Autowired
    PlayerTaskService playerTaskService;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}
