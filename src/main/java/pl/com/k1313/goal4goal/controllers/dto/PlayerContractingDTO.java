package pl.com.k1313.goal4goal.controllers.dto;

import pl.com.k1313.goal4goal.domain.player.Position;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class PlayerContractingDTO {

    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @Past(message ="Data urodzenia musi być w przeszłości")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate birthDate;
    private final Position position;
    //tutaj musimy sparsowac boolean do stringa??
    private final boolean firstSquadPlayer;
//    private final String firstSquadPlayer;

}