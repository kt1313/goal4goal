package pl.com.k1313.goal4goal.config;


import org.springframework.context.annotation.*;
import pl.com.k1313.goal4goal.domain.repository.DBPlayerRepository;
import pl.com.k1313.goal4goal.domain.repository.InMemoryPlayerRepository;
import pl.com.k1313.goal4goal.domain.repository.PlayerRepository;

@Configuration
public class MainConfig {

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
