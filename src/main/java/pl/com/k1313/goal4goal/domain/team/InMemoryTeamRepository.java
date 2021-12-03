package pl.com.k1313.goal4goal.domain.team;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.team.Tactics;
import pl.com.k1313.goal4goal.domain.team.Team;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryTeamRepository implements TeamRepository {

    Map<Integer, Tactics> tactics = new HashMap<>();
    Map<Integer, Player> firstSquad=new HashMap<>();

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    Team team;

    private int getNewId() {
        if (firstSquad.isEmpty()) {
            return 0;
        } else {
            Integer integer = firstSquad.keySet().stream().max((o1, o2) -> o1.compareTo(o2)).get();
            integer=integer+1;
            return integer;
        }
    }

    //dostaje po kliknieciu z checkboxa (template teams) info,
// ze nalezy dodac (usunac) tego zawodnika do pierwszego skladu
    @Override
    public void addToFirstSquad(Integer id) throws ExecutionControl.NotImplementedException {
        Player firstSquadPlayer = new Player();
        firstSquadPlayer = playerRepository.getPlayerById(id);
        Integer firstSquadId;
        firstSquadId=getNewId();
        firstSquad.put(firstSquadId,firstSquadPlayer);
    }

    // dla potrzeb cwiczen firstSquad = allPlayers
    //z czasem trzeba przy wybranych Playerach zrobic checkboxy zmieniajace flage, a ta
    //metoda odczytywalaby tylko ich do ustalania firstSquadu
    @Override
    public Map<Long, Player> setUpFirstSquad() throws ExecutionControl.NotImplementedException {
        Map<Long, Player> firstSquad = playerRepository.getAllPlayers()
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
    public Collection<Tactics> getAllTactics() throws ExecutionControl.NotImplementedException {
        return tactics.values();
    }

    @Override
    public Collection<Player> getAllGamePlayers() {
        return null;
    }

//    @Override
//    public Collection<Tactics> getAllTactics() {
//        return tactics.values();
//    }

    //ma z utworzonej "11" pobrac sume defendingu wszystkich, nie tylko obroncow
    //TO TRZEBA ZROBIC JAKO METODE WSPOLNA a jako parametr podawac ceche
    // np. getTotalDefence (String getDefending) -> i wtedy player.getDefending
    @Override
    public Integer getTotalDefence() throws ExecutionControl.NotImplementedException {
        String x = "getDefending";
        int totalDefence = setUpFirstSquad().values()
                .stream().mapToInt(Player::getDefending).sum();
        System.out.println("calkowita obrona zespołu wynosi: " + totalDefence);
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
}