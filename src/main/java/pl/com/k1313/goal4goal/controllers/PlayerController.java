package pl.com.k1313.goal4goal.controllers;


import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.com.k1313.goal4goal.components.TimeComponent;
import pl.com.k1313.goal4goal.controllers.dto.PlayerContractingDTO;
import pl.com.k1313.goal4goal.controllers.dto.PlayerUpdateDTO;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.UserInformation;
import pl.com.k1313.goal4goal.domain.player.PlayerService;


@Controller
@RequestMapping("/players")
public class PlayerController {


    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService service) {

        this.playerService = service;
    }

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

    @PostMapping
    public String handleCreateNewPlayer
            (PlayerContractingDTO playerDTO) {
        System.out.println(playerDTO);
        this.playerService.createNewPlayer(playerDTO);
        System.out.println("dupadupadupadupadupadupa");

        return "redirect:/players";
    }

    @GetMapping("/delete/{id}")
    public String removePlayer(@PathVariable("id") long id) {
        this.playerService.removeById(id);

        return "redirect:/players";
    }

    @GetMapping("/managePlayer/{id}")
    public String editPlayer(@PathVariable long id, Model model) {
        Player player = this.playerService.getPlayerById(id);
        model.addAttribute("player", player);

        return "managePlayer";
    }

    @PostMapping("/managePlayer")
    public String editPlayer(PlayerUpdateDTO updatedPlayer) {
        this.playerService.update(updatedPlayer);

        return "redirect:/players";
    }

 //po Submicie CallTo11 dodaje Playera do firstsquad
//    @PostMapping("/firstsquadcall/{id}")
//    @PostMapping("/players")
//
//    public String firstSquad(@PathVariable long id, Model model) {
//        Player player = this.playerService.getPlayerById(id);
//        model.addAttribute("player", player);
//        this.playerService.addToFirstSquad(id);
//        return "redirect:/players";
//    }

//    // próba wyciagniecia tabeli ze strony firstsquad i znalezienia
//    //komorki o tym samym id co position playera
//    //wtedy ma zmienic puste value z " " na  "Player,name"
//    @GetMapping("/firstsquad")
//    public String firstSquad(@RequestParam("firstsquadtable") String firstsquadtable, Model model, String tableId) {
////        this.playerService.update(updatedPlayer);
//        return "redirect:/firstsquad";
//    }

}
