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
//    private int ballControl=33;
//    private int passing=44;


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
//                  , int ballControl
//                  , int passing

    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.position = position;
        this.firstSquadPlayer = firstSquadPlayer;
        this.attacking = attacking;
//        this.ballControl = ballControl;
//        this.passing=passing;
    }

    public Player(String firstName,
                  String lastName, LocalDate birthDate,
                  Position position,
                  int attacking
//                 , int ballControl
//                 , int passing

    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.position = position;
        this.attacking = attacking;
//        this.ballControl = ballControl;
//        this.passing=passing;

    }

    public Player(long id,
                  String firstName,
                  String lastName, LocalDate birthDate,
                  Position position
            , boolean firstSquadPlayer, int attacking
//            , int ballControl
//            , int passing
    ) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.position = position;
        this.firstSquadPlayer = firstSquadPlayer;
        this.attacking = attacking;
//        this.ballControl = ballControl;
//        this.passing = passing;


    }

    public void update(String firstName,
                       String lastName, LocalDate birthDate,
                       Position position
            , boolean firstSquadPlayer, int attacking
//            , int ballControl
//            , int passing
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.position = position;
        this.firstSquadPlayer = firstSquadPlayer;
        this.attacking = attacking;
//        this.ballControl = ballControl;
//        this.passing=passing;

    }

    public void setFirstSquadPlayer(boolean firstSquadPlayer) {
        this.firstSquadPlayer = firstSquadPlayer;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", position=" + position +
                ", firstSquadPlayer=" + firstSquadPlayer +
                ", attacking=" + attacking +
//                ", ballControl=" + ballControl +
//                ", passing=" + passing +
                '}';
    }

}
