package pl.com.k1313.goal4goal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.repository.PlayerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Goal4goalApplicationTests {

	@Autowired
    PlayerRepository playerRepository;

	@Autowired
	Player player;

	@Test
	void contextLoads() {
	}

	@Test
	public void testTeam(){
		String except =
				"Zespol nazywa sie Odra Malczyce. " +
						"Gra w nim zawodnik: " +
						"Zawodnik o imieniu Zenon(22). " +
						"Zadanie do wykonania: Biegaj jak kon.";
		assertEquals(except, playerRepository.toString());
	}

}
