package pl.com.k1313.goal4goal.config;


import org.springframework.context.annotation.*;
import pl.com.k1313.goal4goal.domain.player.DBPlayerRepository;
import pl.com.k1313.goal4goal.domain.player.InMemoryPlayerRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.team.InMemoryTacticsRepository;
import pl.com.k1313.goal4goal.domain.team.InMemoryTeamRepository;
import pl.com.k1313.goal4goal.domain.team.TacticsRepository;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;

@Configuration
public class MainConfig {

    @Bean(name = "InMemoryTacticsRepository")
    @Profile("dev")
    public TacticsRepository createInMemoryTacticsRepo() {
        TacticsRepository repo = new InMemoryTacticsRepository();
        return repo;
    }
    @Bean(name = "InMemoryTeamRepository")
    @Profile("dev")
    public TeamRepository createInMemoryTeamRepo() {
        TeamRepository repo = new InMemoryTeamRepository();
        return repo;
    }

    @Bean(name = "inMemoryPlayerRepository")
    @Profile("dev")
    public PlayerRepository createInMemoryRepo() {
        PlayerRepository repo = new InMemoryPlayerRepository();
        return repo;
    }

    @Bean(name = "DBPlayerRepository")
    @Profile("prod")
//    @Profile("dev")
    public PlayerRepository createDBPlayerRepo() {
        PlayerRepository repo = new DBPlayerRepository();
        return repo;
    }

}
