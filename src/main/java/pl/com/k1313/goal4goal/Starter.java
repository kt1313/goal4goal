package pl.com.k1313.goal4goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerTaskRepository;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerTaskService;

@Component
public class Starter implements CommandLineRunner {
//
//    @Autowired
//    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerTaskRepository playerTaskRepository;

    @Autowired
    PlayerTaskService playerTaskService;

    @Override
    public void run(String... args) throws Exception {

//        playerTaskRepository.createRandomPlayerTask();
//        playerTaskRepository.createRandomPlayerTask();
//        teamRepository.getTotalDefence();
//        System.out.println(playerRepository);




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
