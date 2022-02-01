package pl.com.k1313.goal4goal.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.com.k1313.goal4goal.controllers.PlayerController;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.security.Policy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.mockito.NotExtensible;
import pl.com.k1313.goal4goal.controllers.dto.PlayerContractingDTO;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
import pl.com.k1313.goal4goal.domain.player.Position;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;
import pl.com.k1313.goal4goal.domain.team.TeamService;


@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    public void basic() throws Exception {
//given
        Player player = new Player("Tom", "Klmx", LocalDate.parse("1976-05-08"), Position.GK, false);
//when
        Mockito.when(playerService.findAllPlayers()).thenReturn(Arrays.asList(player));

//then
        mockMvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("players"))
                .andExpect(view().name("players"))
                .andExpect(content().string(containsString("1976-05-08")));
    }

    @Test
    public void handlePostTest() throws Exception {

        String postContent = "firstName=Tom&lastName=Klimkiewicz" +
                "&dateOfBirth=2021-11-01&position=goalkeeper&attacking=67";

        MockHttpServletRequestBuilder request = post("/players")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .content(postContent);


        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/players"));

        PlayerContractingDTO dto = new PlayerContractingDTO("Tomek", "Klimkiewicz"
                , LocalDate.parse("2021-11-01"), Position.GK, 67);


        Mockito
                .verify(playerService, Mockito.times(1))
                .createNewPlayer(dto);
    }

    @Test
    public void handleRemovePlayerTest() throws Exception {
        //given for address /players/delete/21

        MockHttpServletRequestBuilder request = get("/players/delete/21");

        //when
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/players"));
        //then
        Mockito
                .verify(playerService, Mockito.times(1))
                .removeById((long) 21);

    }


    @Test
    public void handleFirstSquadTest(){

        //given
        List<Player> firstsquadplayers=new ArrayList<>();
        Player player1=new Player("Zyg", "Pol", LocalDate.parse("2021-11-01"), Position.GK, true, 67);
        firstsquadplayers.add(player1);
        Player player2=new Player("Pyg", "Zol", LocalDate.parse("2001-11-01"), Position.RF, true, 76);
        firstsquadplayers.add(player2);
        Player player3=new Player("Gyg", "Gol", LocalDate.parse("2001-11-06"), Position.CB, false, 88);
        firstsquadplayers.add(player3);

        PlayerRepository playerRepository = Mockito.mock(PlayerRepository.class);
        Mockito.when(playerRepository.findAll().stream()
                .filter(Player::isFirstSquadPlayer)
                .collect(Collectors.toList()))
                .thenReturn(firstsquadplayers);
        PlayerService playerService=new PlayerService(playerRepository);
        List<String> firstsquadplayersStrings=new ArrayList<>();
        for (Player p:firstsquadplayers
             ) {
            firstsquadplayersStrings.add(String.valueOf(p.getId()));
        }

        //when
        List<Player> result=playerService.createFirst11(firstsquadplayersStrings);

        //then
        assertEquals(2,result.size());
    }

}
