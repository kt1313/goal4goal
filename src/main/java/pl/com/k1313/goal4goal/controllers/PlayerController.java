package pl.com.k1313.goal4goal.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.com.k1313.goal4goal.controllers.dto.First11DTO;
import pl.com.k1313.goal4goal.controllers.dto.PlayerContractingDTO;
import pl.com.k1313.goal4goal.controllers.dto.PlayerUpdateDTO;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
import pl.com.k1313.goal4goal.domain.player.Position;
//import pl.com.k1313.goal4goal.domain.team.First11;
//import pl.com.k1313.goal4goal.domain.team.First11;
import pl.com.k1313.goal4goal.domain.team.TeamService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/players")
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService service) {
        this.playerService = service;
    }

    @Autowired
    public TeamService teamService;

    //localhost8080://players
    @GetMapping
    public String guests(Model model) {
        model.addAttribute("players",
                this.playerService.findAllPlayers());
        return "players";
    }

    @GetMapping("/hire")
    public String createNewPlayer() {
        return "playerform";
    }

    //obsluga powolan do 11
    //pobiera wszystkie checkboxy o nazwie firstsquadplayer i sprawdza czy tickniete
    //wtedy tworzy pierwsza 11
    //a potem tworzy tabele first11FinalTable z wybranymi nazwiskami na odpowiednich pozycjach
    @PostMapping("/firstsquadplayers")
    public String handleFirstSquad(@RequestParam("firstSquadPlayer") List<String> ids, Model model) {

        List<Player> firstsquadplayers = new ArrayList<>();
        if (ids != null) {
            for (String idplayer : ids) {
                long l = Long.parseLong(idplayer);
                Player first11Player = this.playerService.getPlayerById(l);
                System.out.println("Player position: "+first11Player.getPosition());
                String first11PlayerPos = String.valueOf(first11Player.getPosition());
                first11Player.setFirstSquadPlayer(true);
                String first11PlayerFirstName = first11Player.getFirstName();
                String first11PlayerLastName = first11Player.getLastName();
                String first11PlayerFullName = first11PlayerFirstName + " " + first11PlayerLastName;
                System.out.println(")o)o)o)o)o)");
                System.out.println(first11PlayerFullName + " " + "ID: " + first11Player.getId() + " " + first11PlayerPos);
                System.out.println(")o)o)o)o)o)");
                firstsquadplayers.add(first11Player);

                }
            }

            model.addAttribute("firstsquadplayers", firstsquadplayers);
            String[][] first11FinalTable = this.teamService.setUpFirst11(firstsquadplayers);
            model.addAttribute("first11FinalTable", first11FinalTable);
            System.out.println("-o-o-o-o-o-o-");
            System.out.println(firstsquadplayers);
            System.out.println("-o-o-o-o-o-o-");

        return "firstsquadplayers";
    }


    @PostMapping
    public String handleCreateNewPlayer
            (@Valid PlayerContractingDTO playerDTO,
             BindingResult result, Model model) {
        System.out.println(playerDTO);
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "playerform";
        } else {
            this.playerService.createNewPlayer(playerDTO);
            return "redirect:/players";
        }
    }

    @GetMapping("/delete/{id}")
    public String removePlayer(@PathVariable("id") Long id) {
        this.playerService.removeById(id);

        return "redirect:/players";
    }

    @GetMapping("/managePlayer/{id}")
    public String editPlayer(@PathVariable Long id, Model model) {
        Player player = this.playerService.getPlayerById(id);
        model.addAttribute("player", player);
        return "managePlayer";
    }

    @PostMapping("/managePlayer")
    public String editPlayer(PlayerUpdateDTO updatedPlayer) {
        this.playerService.update(updatedPlayer);
        return "redirect:/players";
    }


}
