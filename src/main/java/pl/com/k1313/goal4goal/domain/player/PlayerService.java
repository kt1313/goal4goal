package pl.com.k1313.goal4goal.domain.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.controllers.dto.First11DTO;
import pl.com.k1313.goal4goal.controllers.dto.PlayerContractingDTO;
import pl.com.k1313.goal4goal.controllers.dto.PlayerUpdateDTO;
//import pl.com.k1313.goal4goal.domain.team.First11;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private PlayerRepository repository;
    private TeamRepository teamRepository;


    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> findAllPlayers() {
        return this.repository.findAll();
    }

    public void createNewPlayer(PlayerContractingDTO playerDTO) {
        Player newOne = new Player(playerDTO.getFirstName()
                , playerDTO.getLastName()
                , playerDTO.getBirthDate()
                , playerDTO.getPosition()
                , playerDTO.isFirstSquadPlayer()
                , playerDTO.getAttacking());
        this.repository.save(newOne);
    }

    public void removeById(Long id) {
        this.repository.deleteById(id);
    }

    public Player getPlayerById(Long id) {
        return this.repository.getById(id);
    }

    public void update(PlayerUpdateDTO updatedPlayer) {
        Player byId = this.repository.getById(updatedPlayer.getId());
        byId.update(
                updatedPlayer.getFirstName()
                , updatedPlayer.getLastName()
                , updatedPlayer.getBirthDate()
                , updatedPlayer.getPosition()
                , updatedPlayer.isFirstSquadPlayer()
                , updatedPlayer.getAttacking());

//                updatedPlayer.isFirstSquadPlayer());
        this.repository.save(byId);
    }

    public void setFirstSquadPlayer(Long id) {
        Player player = this.repository.getById(id);

    }
}



