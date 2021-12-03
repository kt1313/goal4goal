package pl.com.k1313.goal4goal.domain.player;


import org.springframework.lang.NonNull;

public class Player {

    private long id;
    @NonNull
//    @Size(min=2, max=20, message="imie musi zawierac pomiedzy 2 a 20 znakow")
    private String firstName;
    private Position position;
    private FirstSquad firstSquadPlayer;
    @NonNull
//    @Min(17)
//    @Max(35)
    private int age;
    private int goalkeeping;
    private int defending = 444;

//    private int stamina;
//    private int passing;
//    private int dribbling;
//    private int pace;
//    private int shooting;
//    private int tactics;
//    private int personality;


    private PlayerTask playerTask;

    public Player(@NonNull String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
    }

    public Player() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isFirstSquadPlayer() {
        if (firstSquadPlayer.equals("YES")) {
            return true;
        } else return false;
    }

//    public void setFirstSquadPlayer(boolean firstSquadPlayer) {
//        this.firstSquadPlayer = firstSquadPlayer;
//    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGoalkeeping() {
        return goalkeeping;
    }

    public void setGoalkeeping(int goalkeeping) {
        this.goalkeeping = goalkeeping;
    }

    public int getDefending() {
        return defending;
    }

    public void setDefending(int defending) {
        this.defending = defending;
    }

    @Override
    public String toString() {
        return "Zawodnik o imieniu " + firstName
                + "(" + age + ")" + ". Pierwsza 11: " + isFirstSquadPlayer();
    }
}