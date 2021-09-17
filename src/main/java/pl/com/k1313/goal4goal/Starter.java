package pl.com.k1313.goal4goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.com.k1313.goal4goal.domain.Game;
import pl.com.k1313.goal4goal.domain.Team;

@Component
public class Starter implements CommandLineRunner {
    @Autowired
    Team team;

    @Autowired
    Game game;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(team);
        game.playGame();
        System.out.println(game);

////Wstrzykiwanie zaleznosci przez konstruktor
//        PlayerTask runLikeHell=new PlayerTask("Biegaj jak kon.");
//        Player player1=new Player("Zenon", 21, runLikeHell);
//        System.out.println(player1);
//
//
//        //Wstrzykiwanie zaleznosci przez settera
//        PlayerTask passTheBloodyBall=new PlayerTask("Obij ściankę po stokroć.");
//        Player player2=new Player("Sebix", 25);
//        System.out.println(player2);
//        player2.setPlayerTask(passTheBloodyBall);
//        System.out.println(player2);
    }
}
