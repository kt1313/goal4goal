package pl.com.k1313.goal4goal.domain.player;

import jdk.jshell.spi.ExecutionControl;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

//@Repository
public class InMemoryPlayerRepository implements PlayerRepository {

    Map<Long, Player> players = new HashMap<>();

    public InMemoryPlayerRepository() {
    }

    @Override
    public void hirePlayer(String name, int age) {
        Player newPlayer = new Player(name, age);
        newPlayer.setId(getNewId());
        players.put(newPlayer.getId(), newPlayer);
    }

    @Override
    public void hirePlayer(Player player) {
        player.setId(getNewId());
        players.put(player.getId(), player);
    }

    private long getNewId() {
        if (players.isEmpty()) {
            return 0;
        } else {
            Long longId = players.keySet()
                    .stream().max((o1, o2) -> o1.compareTo(o2)).get();
            longId = longId + 1;
            return longId;
//            Integer integer=players.size(); - nie dziala, bo gdy usuwamy wczesniejszego to rozmiar nie pasuje do Id
        }
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return players.values();
    }

    //dla templatki firstSquad tworzy listę zawodnikow z zaznaczonym checkboxem
    @Override
    public Collection<Player> getFirstSquad(boolean firstSquadPlayerBoolean) throws ExecutionControl.NotImplementedException {
        System.out.println(firstSquadPlayerBoolean);
        List<Player> firstSquad = players.values()
                .stream().filter(Player::isFirstSquadPlayer)
                .collect(Collectors.toList());
        System.out.println("Zawodnicy z 11: " + firstSquad);
        return firstSquad;
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        Optional<Player> playerByName = players.values()
                .stream()
                .filter(player -> player.getFirstName().equals(name)).findAny();
        return playerByName;
    }

    @Override
    public Player getPlayerById(long id) throws ExecutionControl.NotImplementedException {
        return players.get(id);
    }
//przycisk Submit call for 11 zmienia flage player.firstSquadPlayer na true
//    @Override
    public void callPlayerTo11(long playerId) throws ExecutionControl.NotImplementedException {
        System.out.println(playerId);
        Optional<Player>  playerCalledFor11= players.values()
                .stream()
                .filter(player -> player.getId()==playerId).findAny();
//        playerCalledFor11.get().setFirstSquadPlayer(true);

    }

    @Override
    public void firePlayer(Integer id) {
        players.remove(id);
    }

    @Override
    @PostConstruct
    public void create() {
        hirePlayer("Zenon Lewangoalski", 22);
        hirePlayer("Thomassist Mueller", 24);
    }


    @Override
    public String toString() {
        return "PlayerRepository {" +
                "players=" + players +
                '}';
    }
}