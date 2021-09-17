package pl.com.k1313.goal4goal.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
//@PropertySource("classpath:team.properties")
public class Team {

    @Value("${my.team.name:NoName Club}")
    private String name;

    Player player;

    @Autowired
    public Team(@Qualifier(value="player1") Player player) {
        this.player = player;
    }


    Team(Player player, String name) {
        this.player = player;
        this.name = name;
    }

//    @PostConstruct
    public void create() {
        System.out.println("Powstal klub: " + name);
    }

//    @PreDestroy
    public void destroy() {
        System.out.println("Zaraz zniszczymy klub: " + name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Zespol nazywa sie " +
                this.name +
                ". " + "Gra w nim zawodnik: " + player;
    }


}
