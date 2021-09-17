package pl.com.k1313.goal4goal.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Game {

    @Autowired
    @Qualifier(value = "player2")
    Player player;

    public Game() {
    }

    public void playGame() {
        player.setAge(player.getAge() + 1);

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Zawodnik " + player + " rozegrał mecz.";
    }
}
