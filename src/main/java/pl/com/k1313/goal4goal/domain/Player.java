package pl.com.k1313.goal4goal.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;

import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

public class Player {

    private int id;
    @NonNull
    @Size(min=2, max=20, message="imie musi zawierac pomiedzy 2 a 20 znakow")
    private String firstName;
    @NonNull
    @Min(17)
    @Max(35)
    private int age;
    private int goalkeeping;
//    private int stamina;

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
        this.goalkeeping=1;
        this.playerTask=playerTask;
    }

    public Player() {

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoalkeeping() {
        return goalkeeping;
    }

    public void setGoalkeeping(int goalkeeping) {
        this.goalkeeping = goalkeeping;
    }

    @Override
    public String toString() {
        return "Zawodnik o imieniu " + firstName
                + "("+ age+")"   +". Zadanie do wykonania: " + playerTask ;
    }
}
