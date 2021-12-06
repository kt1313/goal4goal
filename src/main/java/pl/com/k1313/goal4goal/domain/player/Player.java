package pl.com.k1313.goal4goal.domain.player;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public Player(String firstName,
                  String lastName, LocalDate birthDate, Position position
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.position = position;
    }

    public Player() {

    }

    public void update(String firstName,
                       String lastName, LocalDate birthDate,
                       Position position
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.position = position;

    }

    @Override
    public String toString() {
        return "Imię: " + firstName +
                ", Nazwisko: " + lastName +
                ", Urodzony: " + birthDate + ", " +
                ", Position: " + position;
    }

}
