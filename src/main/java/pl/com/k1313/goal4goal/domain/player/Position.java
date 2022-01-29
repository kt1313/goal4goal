package pl.com.k1313.goal4goal.domain.player;

import org.springframework.stereotype.Component;


public enum Position {

    GK ("goalkeeper", "3"),
    RWB("rightWingback", "10"),
    RCB("rightCentreback", "9"),
    CB("centreback", "8"),
    LCB("leftCentreback", "7"),
    LWB("leftWingback", "6"),
    RW("rightWinger", "15"),
    CMD("centreMidfielder Defending", "14"),
    CM("centre Midfielder", "13"),
    CMA("centreMidfielder Attacking", "12"),
    LW("leftWinger", "11"),
    RF("rightForward", "19"),
    CF("centreForward", "18"),
    LF("leftForward", "17");

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
