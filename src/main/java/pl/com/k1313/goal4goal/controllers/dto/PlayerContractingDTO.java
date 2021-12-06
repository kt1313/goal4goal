package pl.com.k1313.goal4goal.controllers.dto;

import pl.com.k1313.goal4goal.domain.player.Position;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
public class PlayerContractingDTO {

    private final String firstName;
    private final String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate birthDate;
    private final Position position;

}
