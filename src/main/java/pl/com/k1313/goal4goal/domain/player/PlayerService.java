package pl.com.k1313.goal4goal.domain.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.controllers.dto.PlayerContractingDTO;
import pl.com.k1313.goal4goal.controllers.dto.PlayerUpdateDTO;

import java.util.List;

@Service
public class PlayerService {

    private PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> findAllPlayers() {
        return this.repository.findAll();
    }

    public void createNewPlayer(PlayerContractingDTO playerDTO) {

        Player newOne = new Player(playerDTO.getFirstName(), playerDTO.getLastName(), playerDTO.getBirthDate(), playerDTO.getPosition());
        this.repository.save(newOne);
        System.out.println(repository.findAll());
    }

    public void removeById(long id) {
        this.repository.deleteById(id);
    }


    public Player getPlayerById(long id) {
        return this.repository.getById(id);
    }

    public void update(PlayerUpdateDTO updatedPlayer) {
        Player byId = this.repository.getById(updatedPlayer.getId());
        byId.update(
                updatedPlayer.getFirstName(),
                updatedPlayer.getLastName(),
                updatedPlayer.getBirthDate(),
                updatedPlayer.getPosition());

        this.repository.save(byId);
    }
}



