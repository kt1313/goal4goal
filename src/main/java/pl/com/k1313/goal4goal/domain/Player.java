package pl.com.k1313.goal4goal.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

//@Component
//@Scope"prototype"
public class Player {

    private String firstName;

    private int age;

//    private PlayerTask playerTask;
//    private int stamina;
//    private int goalkeeping;
//    private int passing;
//    private int dribbling;
//    private int tackling;
//    private int pace;
//    private int shooting;
//    private int tactics;
//    private int personality;

    private PlayerTask playerTask;



    public Player(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
        this.playerTask=playerTask;
    }

    public Player() {
        this.firstName = "Zenon";
        this.age = 22;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //    @Autowired
//    Team team;

    public void setPlayerTask(PlayerTask playerTask) {
        System.out.println("Ustawiam zadanie dla zawodnika. ");
        this.playerTask = playerTask;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Zawodnik o imieniu " + firstName
                + "("+ age+")"   +". Zadanie do wykonania: " + playerTask ;
    }
}
