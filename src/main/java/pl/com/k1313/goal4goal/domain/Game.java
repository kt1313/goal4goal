package pl.com.k1313.goal4goal.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Game {


    public Game() {
    }

    public void playGame() {
//        player.setAge(player.getAge() + 1);

    }


    @Override
    public String toString() {
        return "W meczu zagrali: ";}
}

