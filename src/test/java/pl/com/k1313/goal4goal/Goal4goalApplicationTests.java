package pl.com.k1313.goal4goal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Goal4goalApplicationTests {

	@Autowired
	Team team;

	@Autowired
	Player player;

	@Test
	void contextLoads() {
	}

	@Test
	public void testTeam(){
		String except = "Zespol nazywa sie Odra Malczyce. Gra w nim zawodnik: Zawodnik o imieniu Zenon(22) z klubu Odra Malczyce. Zadanie do wykonania:";
		assertEquals(except, team.toString());
	}

}
