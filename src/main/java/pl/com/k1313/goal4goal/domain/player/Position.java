package pl.com.k1313.goal4goal.domain.player;

import org.springframework.stereotype.Component;


public enum Position {

    GK ("Goalkeeper"),
    RWB("Right Wingback"),
    RCB("Right Centre-back"),
    CB("Centre back"),
    LCB("Left Centre-back"),
    LWB("Left Wingback"),
    RW("Right Winger"),
    CMD("Centre Midfielder Defending"),
    CM("Centre Midfielder"),
    CMA("Centre Midfielder Attacking"),
    LW("Left Winger"),
    RF("Right Forward"),
    CF("Centre Forward"),
    LF("Left Forward");

    private final String position;

    Position(String position){
        this.position=position;
    }

    public String toString(){
        return this.position;
    }
}
