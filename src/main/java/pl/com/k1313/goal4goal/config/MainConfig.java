package pl.com.k1313.goal4goal.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.PlayerTask;
import pl.com.k1313.goal4goal.domain.repository.DBPlayerRepository;
import pl.com.k1313.goal4goal.domain.repository.InMemoryRepository;
import pl.com.k1313.goal4goal.domain.repository.PlayerRepository;

@Configuration
public class MainConfig {

    @Bean(name = "inMemoryPlayerRepository")
    @Profile("dev")
    public PlayerRepository createInMemoryRepo() {
        PlayerRepository repo = new InMemoryRepository();
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
