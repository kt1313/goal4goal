package pl.com.k1313.goal4goal.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import pl.com.k1313.goal4goal.controllers.dto.PlayerContractingDTO;
import pl.com.k1313.goal4goal.controllers.dto.PlayerUpdateDTO;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
import pl.com.k1313.goal4goal.domain.player.Position;
import pl.com.k1313.goal4goal.domain.team.TeamService;


@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;
    @MockBean
    private PlayerRepository playerRepository;
    @MockBean
    private TeamService teamService;


    //unit test done- working
    @Test
    public void basic() throws Exception {
//given
        Player player = new Player("Tom", "Klmx", LocalDate.parse("1976-05-08")
                , Position.GK, false);
//when
        Mockito.when(playerService.findAllPlayers()).thenReturn(Arrays.asList(player));

//then
        mockMvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("players"))
                .andExpect(view().name("players"))
                .andExpect(content().string(containsString("1976-05-08")));
    }

    //unit test done- not working
    @Test
    public void handlePostTest() throws Exception {

        String postContent = "firstName=Tom&lastName=Klimkiewicz" +
                "&dateOfBirth=2021-11-01&position=goalkeeper&attacking=67";

        MockHttpServletRequestBuilder request = post("/players")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .content(postContent);

        PlayerContractingDTO dto = new PlayerContractingDTO("Tomek", "Klimkiewicz"
                , LocalDate.parse("2021-11-01"), Position.GK, 67);

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/players"));

        Mockito
                .verify(playerService, Mockito.times(1))
                .createNewPlayer(dto);
    }

    //unit test done- working
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

    //unit test done- not working
    @Test
    public void createFirst11Test() {

        //given
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Zyg", "Pol", LocalDate.parse("2021-11-01"), Position.GK, true, 67);
        players.add(player1);
        Player player2 = new Player("Pyg", "Zol", LocalDate.parse("2001-11-01"), Position.RF, true, 76);
        players.add(player2);
        Player player3 = new Player("Gyg", "Gol", LocalDate.parse("2001-11-06"), Position.CB, false, 88);
        players.add(player3);

        String f11player1 = new String(String.valueOf(players.indexOf(player1)));
        String f11player2 = new String(String.valueOf(players.indexOf(player3)));
        System.out.println("PlContrTest, createFirst11, f11p1 i f11p2: "+ f11player1+" i "+f11player2);
        List<Player> first11PlayersExpected = new ArrayList<>(Arrays.asList(player1,player3));

        PlayerRepository playerRepository = Mockito.mock(PlayerRepository.class);
        PlayerService playerService = new PlayerService(playerRepository);

        List<String> stringList = new ArrayList<>(Arrays.asList("0", "2"));
//        playerService.createFirst11(stringList);

        //when
        long l1 = Long.parseLong(stringList.get(0));
        long l2 = Long.parseLong(stringList.get(1));

        Mockito.when(playerRepository.getById(l1)).thenReturn(players.get((int) l1));
        Mockito.when(playerRepository.getById(l2)).thenReturn(players.get((int) l2));

        List<Player> result = playerService.createFirst11(stringList);

        //then
        assertEquals(player1, result.get(0));
        assertEquals(player3, result.get(1));
        assertEquals(first11PlayersExpected,result);
    }

    //unit test done-  working
    @Test
    public void updatePlayerTest() throws Exception {

        //given
        PlayerRepository playerRepository = Mockito.mock(PlayerRepository.class);
        PlayerService playerService = new PlayerService(playerRepository);

        List<Player> players = new ArrayList<>();

        Player playerBefore = new Player(21, "Tom", "Klmx",
                LocalDate.parse("1976-05-08"), Position.GK, false, 67);
        players.add(playerBefore);

        Player playerAfter = new Player(
                "Tom", "After"
                , LocalDate.parse("1976-05-08")
                , Position.GK
                , false);
        players.add(playerAfter);

        PlayerUpdateDTO playerUpdateDTO = new PlayerUpdateDTO((long) 21
                , "Tom", "After"
                , LocalDate.parse("1976-05-08")
                , Position.GK
                , 67
        );

        //when
        Mockito.when(playerService.getPlayerById((long) 21)).thenReturn(playerAfter);
        playerService.update(playerUpdateDTO);
        Player playerUpdated = playerRepository.getById((long) 21);

        //then
        assertEquals(playerAfter, playerUpdated);
    }

    //unit test done-  working
    @Test
    public void createNewPlayerTest() {

        //given
        PlayerRepository playerRepository = Mockito.mock(PlayerRepository.class);
        PlayerService playerService = new PlayerService(playerRepository);

        List<Player> players = new ArrayList<>();
        Player playerExpected = new Player("Tom", "Klmx"
                , LocalDate.parse("1976-05-08"), Position.GK, 67);

        PlayerContractingDTO playerContractingDTO = new PlayerContractingDTO(
                "Tom", "Klmx"
                , LocalDate.parse("1976-05-08")
                , Position.GK
                , 67);
        Player addPlayer = new Player(playerContractingDTO.getFirstName()
                , playerContractingDTO.getLastName()
                , playerContractingDTO.getBirthDate()
                , playerContractingDTO.getPosition()
                , playerContractingDTO.getAttacking()
        );
        players.add(addPlayer);

        //when
        Player p = playerService.createNewPlayer(playerContractingDTO);

        //then
        assertEquals(playerExpected, p);
    }

}




