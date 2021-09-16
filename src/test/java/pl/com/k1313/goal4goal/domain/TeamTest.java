package pl.com.k1313.goal4goal.domain;

import org.junit.jupiter.api.Test;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamTest {

    @Test
    public void teamToStringMessage(){

        PlayerTask playerTask=new PlayerTask();
        Player player=new Player();
        player.setPlayerTask(playerTask);
        Team team=new Team(player, "Odra Malczyce");
        String except =
                "Zespol nazywa sie Odra Malczyce. " +
                        "Gra w nim zawodnik: " +
                        "Zawodnik o imieniu Zenon(22). " +
                        "Zadanie do wykonania: Biegaj jak kon.";
        assertEquals(except, team.toString());
    }
}
