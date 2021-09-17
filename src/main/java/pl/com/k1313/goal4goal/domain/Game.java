package pl.com.k1313.goal4goal.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Game {

    @Autowired
    Player player;

    public Game() {
    }

    public void playGame(){
        player.setAge(player.getAge()+1);

    }

    @Override
    public String toString() {
        return "Zawodnik " + player + " rozegrał mecz.";
    }
}
