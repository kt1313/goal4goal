package pl.com.k1313.goal4goal.domain.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.controllers.dto.PlayerContractingDTO;
import pl.com.k1313.goal4goal.controllers.dto.PlayerUpdateDTO;

import java.util.List;

//@Service
//public class PlayerService {
//
//    @Autowired
//    PlayerRepository playerRepository;
//
//    public List<Player> getAllPlayers() throws ExecutionControl.NotImplementedException {
//        return new ArrayList<>(playerRepository.getAllPlayers());
//    }
//
//    public void hirePlayer(Player player) throws ExecutionControl.NotImplementedException {
//        playerRepository.hirePlayer(player);
//    }
//
//    public Player getPlayer(Integer id) throws ExecutionControl.NotImplementedException {
//        return playerRepository.getPlayerById(id);
//    }
//
//    public void firePlayer(Integer playerId) throws ExecutionControl.NotImplementedException {
//        playerRepository.firePlayer(playerId);
//    }
//
//    public List<Player> getFirstSquad(boolean firstSquadPlayerBoolean) throws ExecutionControl.NotImplementedException {
//        return new ArrayList<>(playerRepository.getFirstSquad( firstSquadPlayerBoolean));
//    }
//
//    public void setPlayerFor11(Integer playerId) throws ExecutionControl.NotImplementedException{
//        playerRepository.callPlayerTo11(playerId);
//    }
//
//    public Player getPlayerById(long id) throws ExecutionControl.NotImplementedException {
//        return this.playerRepository.getPlayerById(id);
//    }
//}



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

    public void createNewPlayer( PlayerContractingDTO playerDTO) {

        Player newOne=new Player(playerDTO.getFirstName(), playerDTO.getLastName(), playerDTO.getDateOfBirth(), playerDTO.getPosition());
        this.repository.save(newOne);
    }

    public void removeById(long id) {
        this.repository.deleteById(id);
    }


    public Player getPlayerById(long id) {
        return this.repository.getById(id);}

    public void update(PlayerUpdateDTO updatedPlayer) {
        Player byId = this.repository.getById(updatedPlayer.getId());
        byId.update(
                updatedPlayer.getFirstName(),
                updatedPlayer.getLastName(),
                updatedPlayer.getDateOfBirth());
                //                updatedPlayer.getPosition())

        this.repository.save(byId);
    }
}



