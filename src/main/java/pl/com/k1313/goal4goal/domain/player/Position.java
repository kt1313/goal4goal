package pl.com.k1313.goal4goal.domain.player;

import org.springframework.stereotype.Component;


public enum Position {

    GK ("Goalkeeper", "3"),
    RWB("Right Wingback", "10"),
    RCB("Right Centre-back", "9"),
    CB("Centre back", "8"),
    LCB("Left Centre-back", "7"),
    LWB("Left Wingback", "6"),
    RW("Right Winger", "15"),
    CMD("Centre Midfielder Defending", "14"),
    CM("Centre Midfielder", "13"),
    CMA("Centre Midfielder Attacking", "12"),
    LW("Left Winger", "11"),
    RF("Right Forward", "19"),
    CF("Centre Forward", "18"),
    LF("Left Forward", "17");

    private final String position;
    private final String number;


    Position(String position, String number){
        this.position=position; this.number=number;
    }

    public static String toString(Position playerPosition) {
        return Position.toString(playerPosition);
    }

    public String toString(){
        return this.position;
    }
}
