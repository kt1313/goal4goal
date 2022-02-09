package pl.com.k1313.goal4goal.domain.player;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Setter(value = AccessLevel.NONE)
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Position position;

    private boolean firstSquadPlayer;

    private int attacking;


    public Player() {

    }

    public Player(String firstName,
                  String lastName, LocalDate birthDate,
                  Position position,
                  boolean firstSquadPlayer
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.position = position;
        this.firstSquadPlayer = firstSquadPlayer;
    }

    public Player(String firstName,
                  String lastName, LocalDate birthDate,
                  Position position,
                  boolean firstSquadPlayer,
                  int attacking
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.position = position;
        this.firstSquadPlayer = firstSquadPlayer;
        this.attacking = attacking;
    }
    public Player(String firstName,
                  String lastName, LocalDate birthDate,
                  Position position,
                  int attacking
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.position = position;
        this.attacking = attacking;
    }
    public Player(long id,
                  String firstName,
                  String lastName, LocalDate birthDate,
                  Position position
            , boolean firstSquadPlayer, int attacking){

        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.position = position;
        this.firstSquadPlayer = firstSquadPlayer;
        this.attacking=attacking;

    }

    public void update(String firstName,
                       String lastName, LocalDate birthDate,
                       Position position
            , boolean firstSquadPlayer, int attacking
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.position = position;
        this.firstSquadPlayer = firstSquadPlayer;
        this.attacking=attacking;
    }

    public void setFirstSquadPlayer(boolean firstSquadPlayer) {
        this.firstSquadPlayer = firstSquadPlayer;
    }

    @Override
    public String toString() {
        return "Imię: " + firstName +
                ", Nazwisko: " + lastName +
                ", Urodzony: " + birthDate + ", " +
                ", Position: " + position +
                ", 11: " +firstSquadPlayer;
    }

}
