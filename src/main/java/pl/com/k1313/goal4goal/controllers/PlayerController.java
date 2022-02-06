package pl.com.k1313.goal4goal.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.com.k1313.goal4goal.controllers.dto.PlayerContractingDTO;
import pl.com.k1313.goal4goal.controllers.dto.PlayerUpdateDTO;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerService;

import pl.com.k1313.goal4goal.domain.team.TeamService;

import javax.validation.Valid;
import java.util.List;


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
    //unit test done- not working
    @GetMapping
    public String players(Model model) {
        model.addAttribute("players",
                this.playerService.findAllPlayers());
        return "players";
    }

    @GetMapping("/hire")
    public String createNewPlayer() {
        return "playerform";
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

    //unit test done-  working
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
    //unit test done - not working - w update "byId" jest null..
    @PostMapping("/managePlayer")
    public String editPlayer(PlayerUpdateDTO updatedPlayer) {
        this.playerService.update(updatedPlayer);
        return "redirect:/players";
    }


    //unit test done- not working
    //obsluga powolan do 11
    //pobiera wszystkie checkboxy o nazwie firstsquadplayer i sprawdza czy tickniete
    //wtedy tworzy pierwsza 11
    //a potem tworzy tabele first11FinalTable z wybranymi nazwiskami na odpowiednich pozycjach
    @PostMapping("/firstsquadplayers")
    public String handleFirstSquad(@RequestParam(value = "firstSquadPlayer", required = false) List<String> ids, Model model) {
        if (ids != null) {

            List<Player> firstsquadplayers;
            firstsquadplayers = this.playerService.createFirst11(ids);

            model.addAttribute("firstsquadplayers", firstsquadplayers);
            String[][] first11FinalTable = this.teamService.setUpFirst11(firstsquadplayers);
            model.addAttribute("first11FinalTable", first11FinalTable);
            System.out.println("Siła ataku: "+teamService.calculateFirst11Attack());
            return "firstsquadplayers";
        } else {
            return "redirect:/players";
        }
    }





}
