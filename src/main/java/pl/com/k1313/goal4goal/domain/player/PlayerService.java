package pl.com.k1313.goal4goal.domain.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.controllers.PlayerController;
import pl.com.k1313.goal4goal.controllers.dto.PlayerContractingDTO;
import pl.com.k1313.goal4goal.controllers.dto.PlayerUpdateDTO;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PlayerService {

    private PlayerRepository repository;
    private TeamRepository teamRepository;
    private PlayerController playerController;


    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> findAllPlayers() {
        return this.repository.findAll();
    }

    //unit test done-  working
    public Player createNewPlayer(PlayerContractingDTO playerDTO) {
        Player newOne = new Player(playerDTO.getFirstName()
                , playerDTO.getLastName()
                , playerDTO.getBirthDate()
                , playerDTO.getPosition()
                , playerDTO.isFirstSquadPlayer()
                , playerDTO.getAttacking()
                , playerDTO.getBallControl()
                , playerDTO.getPassing()
                , playerDTO.getTackling()
                , playerDTO.getGoalkeeping()
        );
        this.repository.save(newOne);
        return newOne;
    }

    public void removeById(Long id) {
        this.repository.deleteById(id);
    }

    public Player getPlayerById(Long id) {
        return this.repository.getById(id);
    }

    //unit test done-  working
    public Player update(PlayerUpdateDTO updatedPlayer) {
        Player byId = getPlayerById(updatedPlayer.getId());
        byId.update(
                updatedPlayer.getFirstName()
                , updatedPlayer.getLastName()
                , updatedPlayer.getBirthDate()
                , updatedPlayer.getPosition()
                , updatedPlayer.isFirstSquadPlayer()
                , updatedPlayer.getAttacking()
                , updatedPlayer.getBallControl()
                , updatedPlayer.getPassing()
                , updatedPlayer.getTackling()
                , updatedPlayer.getGoalkeeping()
        );
        this.repository.save(byId);
        return byId;
    }

    //unit test done-  working
    public List<Player> createFirst11(List<String> ids) {
        List<Player> firstsquadplayers = new ArrayList<>();

        if (ids != null) {
            for (String idplayer : ids) {
                long l = Long.parseLong(idplayer);
                Player first11Player = getPlayerById(l);
                first11Player.setFirstSquadPlayer(true);
                firstsquadplayers.add(first11Player);
                System.out.println("save1?");
                this.repository.save(first11Player);
                System.out.println("save2?");
            }
        }
        return firstsquadplayers;
    }

    //--------------------------------------------------------------------
    //czy podawac na jakiej pozycji szukmy? wtedy zmienne dla odpowiednich umiejetnosci
    public Player createPlayer(Position position) {
        String firstName = randomFirstName();
        String lastName = randomLastName();
        //wiek zalezny bedzie czy Mlodzik czy nie. ustaw w metodzie randomBirthDate
        LocalDate birthDate = randomBirthDate();
        int attacking = randomFrom100();
        int ballControl = randomFrom100();
        int passing = randomFrom100();
        int tackling = randomFrom100();
        int goalkeeping = randomFrom100();

        Player newPlayer = new Player(firstName, lastName, birthDate, attacking, ballControl, passing, tackling, goalkeeping);

        this.repository.save(newPlayer);
        return newPlayer;
    }

    public int randomFrom100() {
        Random r = new Random();
        int result = r.nextInt(100);
        return result;
    }

    public String randomFirstName() {
        Random r = new Random();
        List firstNamesList = new ArrayList<>(List.of("Adam", "Obi-Wan", "Frankie", "Janusz"));
        String newFirstName = (String) firstNamesList.get(r.nextInt(4));
        return newFirstName;
    }

    public String randomLastName() {
        Random r = new Random();
        List firstNamesList = new ArrayList<>(List.of("Kalekostopski", "McWolny", "Anemikus", "Ci-Ho-Pek"));
        String newFirstName = (String) firstNamesList.get(r.nextInt(4));
        return newFirstName;
    }

    public LocalDate randomBirthDate() {
        //jesli młodzik to dopasowac ponizej o ile mlodszy niż TERAZ
        //nie starszy niz
        int startDate = 19 * 365;
        //nie młodszy niż
        int endDate = 17 * 365;
        return LocalDate.ofEpochDay(ThreadLocalRandom
                .current().nextInt(-startDate, endDate));
    }
}




