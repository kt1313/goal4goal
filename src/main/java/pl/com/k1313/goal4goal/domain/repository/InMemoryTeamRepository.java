package pl.com.k1313.goal4goal.domain.repository;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.Team;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryTeamRepository implements TeamRepository {

    @Autowired
    PlayerRepository playerRepository;


    // dla potrzeb cwiczen firstSquad = allPlayers
    //z czasem trzeba przy wybranych Playerach zrobic checkboxy zmieniajace flage, a ta
    //metoda odczytywalaby tylko ich do ustalania firstSquadu
    @Override
    public Map<Integer, Player> setUpFirstSquad() throws ExecutionControl.NotImplementedException {
        Map<Integer, Player> firstSquad = playerRepository.getAllPlayers()
                .stream().parallel()
                .collect(Collectors.toMap(Player::getId, Function.identity()));
        return firstSquad;
    }

    @Override
    public String getTeamName() throws ExecutionControl.NotImplementedException {

        return getTeamName();
    }

    @Override
    public String getManagerName() throws ExecutionControl.NotImplementedException {
        return getManagerName();
    }

    @Override
    public Collection<Player> getAllGamePlayers() {
        return null;
    }


    //ma z utworzonej "11" pobrac sume defendingu wszystkich, nie tylko obroncow
    //TO TRZEBA ZROBIC JAKO METODE WSPOLNA a jako parametr podawac ceche
    // np. getTotalDefence (String getDefending) -> i wtedy player.getDefending
    @Override
    public Integer getTotalDefence() throws ExecutionControl.NotImplementedException {
        String x= "getDefending";
        int totalDefence = setUpFirstSquad().values()
                .stream().mapToInt(Player::getDefending).sum();
        System.out.println("calkowita obrona wynosi: " + totalDefence);
        return totalDefence;
    }

    @Override
    public Integer getTeamPlaymaking() throws ExecutionControl.NotImplementedException {
//        public Integer getTotalDefence() throws ExecutionControl.NotImplementedException {
//            int totalDefence=setUpGameSquad().values().stream().mapToInt(player ->player.getDefending()).sum();
//            System.out.println("calkowita obrona wynosi: "+totalDefence);
//            return totalDefence;
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
