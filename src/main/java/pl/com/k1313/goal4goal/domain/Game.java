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

    @Autowired
//    @Qualifier(value = "player2")
    List<Player> players;

    public Game() {
    }

    public void playGame() {
//        player.setAge(player.getAge() + 1);

    }

    public void setPlayer(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "W meczu zagrali: " + players.stream().map(Objects::toString).collect(Collectors.joining(","));
    }
}

