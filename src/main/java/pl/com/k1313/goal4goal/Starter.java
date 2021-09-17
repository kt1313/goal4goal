package pl.com.k1313.goal4goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.com.k1313.goal4goal.domain.repository.PlayerRepository;
import pl.com.k1313.goal4goal.domain.repository.PlayerTaskRepository;
import pl.com.k1313.goal4goal.services.PlayerTastService;

@Component
public class Starter implements CommandLineRunner {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerTaskRepository playerTaskRepository;

    @Autowired
    PlayerTastService playerTaskService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(playerRepository);
        System.out.println(playerTaskRepository);


        playerTaskRepository.createRandomPlayerTask();
        System.out.println(playerTaskRepository);
        playerTaskRepository.createRandomPlayerTask();
        System.out.println(playerTaskRepository);
        playerTaskRepository.createRandomPlayerTask();
        System.out.println(playerTaskRepository);


        playerTaskService.assignRandomTask("Zenon");
        playerTaskService.assignRandomTask("Sebix");






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
