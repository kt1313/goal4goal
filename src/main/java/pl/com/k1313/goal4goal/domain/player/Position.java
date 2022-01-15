package pl.com.k1313.goal4goal.domain.player;

import org.springframework.stereotype.Component;


public enum Position {

    GK ("Goalkeeper", "3"),
    RWB("RightWingback", "10"),
    RCB("RightCentre-back", "9"),
    CB("CentreBack", "8"),
    LCB("LeftCentre-back", "7"),
    LWB("LeftWingback", "6"),
    RW("RightWinger", "15"),
    CMD("CentreMidfielder Defending", "14"),
    CM("Centre Midfielder", "13"),
    CMA("CentreMidfielder Attacking", "12"),
    LW("LeftWinger", "11"),
    RF("RightForward", "19"),
    CF("CentreForward", "18"),
    LF("LeftForward", "17");

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
