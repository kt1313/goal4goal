package pl.com.k1313.goal4goal.domain;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

@Component
public class Player {

    private String firstName="Zenon";

    private int age=22;

    private String club="Odra Malczyce";
//    private PlayerTask playerTask;
//    private int stamina;
//    private int goalkeeping;
//    private int passing;
//    private int dribbling;
//    private int tackling;
//    private int pace;
//    private int shooting;
//    private int tactics;


//    public Player(String firstName, int age, PlayerTask playerTask) {
//        this.firstName = firstName;
//        this.age = age;
//        this.playerTask=playerTask;
//    }

    public Player() {
    }


//    public Player(String firstName, int age) {
//        this.firstName = firstName;
//        this.age = age;
//    }

//    public void setPlayerTask(PlayerTask playerTask) {
//        this.playerTask = playerTask;
//    }

    @Override
    public String toString() {
        return "Zawodnik o imieniu " + firstName
                + "("+ age+")" + " z klubu " +club +". Zadanie do wykonania: "  ;
    }
}
