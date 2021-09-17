package pl.com.k1313.goal4goal.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.PlayerTask;

@Configuration
public class MainConfig {

    @Autowired
    PlayerTask playerTask;


    @Bean(name="player1")
    @Primary
    public Player player1() {
        Player player1 = new Player("Zenon", 22);
        return player1;
    }

    @Bean(name="player2")
    public Player player2() {
        Player player2 = new Player("Sebix", 24);
        return player2;
    }

}
