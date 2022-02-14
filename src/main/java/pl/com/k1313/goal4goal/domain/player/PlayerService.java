package pl.com.k1313.goal4goal.domain.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.controllers.PlayerController;
import pl.com.k1313.goal4goal.controllers.dto.PlayerContractingDTO;
import pl.com.k1313.goal4goal.controllers.dto.PlayerUpdateDTO;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;

import java.util.ArrayList;
import java.util.List;

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
            }
        }
        return firstsquadplayers;
    }
}



