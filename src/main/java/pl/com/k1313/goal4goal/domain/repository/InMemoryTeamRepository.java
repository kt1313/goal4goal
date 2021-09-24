package pl.com.k1313.goal4goal.domain.repository;

import jdk.jshell.spi.ExecutionControl;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.Team;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryTeamRepository implements TeamRepository {

    @Autowired
    PlayerRepository playerRepository;


    // dla potrzeb cwiczen firstSquad = allPlayers
    public void createExerciseSquad() throws ExecutionControl.NotImplementedException {
    }

    @Override
    public Map<Integer, Player> setUpGameSquad() throws ExecutionControl.NotImplementedException {
        Map<Integer, Player> firstSquad = playerRepository.getAllPlayers()
                .stream().parallel()
                .collect(Collectors.toMap(Player::getId, Function.identity()));
        return firstSquad;
    }

    @Override
    public Team getTeamName() throws ExecutionControl.NotImplementedException {
        //trzeba przy wybranych Playerach zrobic checkboxy zmieniajace flage, a ta
        //metoda odczytywala by tylko ich
//        return forGamePlayers;
        return null;
    }

    @Override
    public Collection<Player> getAllGamePlayers() {
        return null;
    }


    //ma z utworzonej "11" pobrac sume defendingu wszystkich, nie tylko obroncow
    @Override
    public Integer getTotalDefence() throws ExecutionControl.NotImplementedException {
     int totalDefence=setUpGameSquad().values().stream().mapToInt(player ->player.getDefending()).sum();
        System.out.println("calkowita obrona wynosi: "+totalDefence);
        return totalDefence;
    }

    @Override
    public Integer getTeamPlaymaking() throws ExecutionControl.NotImplementedException {
        return null;
    }

    @Override
    public Integer getTeamAttacking() throws ExecutionControl.NotImplementedException {
        return null;
    }

    @Override
    public Integer getTeamCounterAttacking() throws ExecutionControl.NotImplementedException {
        return null;
    }

    @Override
    public Integer getTeamSelfConfidence() throws ExecutionControl.NotImplementedException {
        return null;
    }

    @Override
    public Integer getTeamTactics() throws ExecutionControl.NotImplementedException {
        return null;
    }

////    ------------------------
//public InMemoryTeamRepository() {
//}
//
//    Map<Integer, Player> forGamePlayers = new HashMap<>();
//
//    @Override
//    public void setUpGameSquad() throws ExecutionControl.NotImplementedException {
//        //trzeba przy wybranych Playerach zrobic checkboxy zmieniajace flage, a ta
//        //metoda odczytywala by tylko ich
////        return forGamePlayers;
//
//    }
//
//    @Override
//    public Team getTeamname() throws ExecutionControl.NotImplementedException {
//        return null;
//    }
//
//    @Override
//    public Collection<Player> getAllGamePlayers() {
//        return forGamePlayers.values();
//    }
//
//    @Override
//    public Integer getTotalDefence() throws ExecutionControl.NotImplementedException {
////         forGamePlayers.keySet().stream().forEach(player ->player.getDefending().sum());
//        return null;
//    }
//
//    @Override
//    public Integer getTeamPlaymaking() throws ExecutionControl.NotImplementedException {
//        return null;
//    }
//
//    @Override
//    public Integer getTeamAttacking() throws ExecutionControl.NotImplementedException {
//        return null;
//    }
//
//    @Override
//    public Integer getTeamCounterAttacking() throws ExecutionControl.NotImplementedException {
//        return null;
//    }
//
//    @Override
//    public Integer getTeamSelfConfidence() throws ExecutionControl.NotImplementedException {
//        return null;
//    }
//
//    @Override
//    public Integer getTeamTactics() throws ExecutionControl.NotImplementedException {
//        return null;
//    }
//
//
//
}
