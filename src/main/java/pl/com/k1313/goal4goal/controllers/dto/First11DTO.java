package pl.com.k1313.goal4goal.controllers.dto;

import lombok.Data;
import pl.com.k1313.goal4goal.domain.player.Player;
@Data
public class First11DTO {

    private  final Player goalkeeper;
    private  final Player rightWingback;
    private  final Player rightCentreback;
    private  final Player centreback;


    public First11DTO(Player goalkeeper, Player rightWingback, Player rightCentreback, Player centreback) {
        this.goalkeeper = goalkeeper;
        this.rightWingback = rightWingback;
        this.rightCentreback = rightCentreback;
        this.centreback = centreback;
    }

    public Player getGoalkeeper() {
        return this.goalkeeper;
    }

    public Player getRightWingback() {
        return this.rightWingback;
    }

    public Player getRightCentreback() {
        return this.rightCentreback;
    }

    public Player getCentreback() {
        return this.rightCentreback;
    }
}
