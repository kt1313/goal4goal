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

import java.util.ArrayList;
import java.util.List;

//@Controller
//public class PlayerController {
//
//    @Autowired
//    TimeComponent timeComponent;
//
//    @Autowired
//    UserInformation userInformation;
//
//    @Autowired
//    PlayerService playerService;
//
//    @RequestMapping("/players")
//    public String getPlayers(Model model) throws ExecutionControl.NotImplementedException {
//        List<Player> allPlayers = playerService.getAllPlayers();
//        model.addAttribute("players", allPlayers);
//        model.addAttribute("timeComponent", timeComponent);
//        model.addAttribute("userInformation", userInformation);
//        return "players";
//    }
//
//    @RequestMapping("/player")
//    //przedstawiony 1 sposob , by dostac "id", a ponizej drugi sposob
//    public String getPlayer(@RequestParam("id") Integer id, Model model) throws ExecutionControl.NotImplementedException {
//        Player player = playerService.getPlayer(id);
//        model.addAttribute("player", player);
//        model.addAttribute("timeComponent", timeComponent);
//        model.addAttribute("userInformation", userInformation);
//        return "player";
//    }
//
//
//    @RequestMapping("/newplayer")
//    public String hirePlayer(Model model) {
//        model.addAttribute("player", new Player());
//        model.addAttribute("timeComponent", timeComponent);
//        model.addAttribute("userInformation", userInformation);
//        return "playerform";
//    }
//
//    @RequestMapping(value = "/players", method = RequestMethod.POST)
//    public String savePlayer(@Validated Player player, BindingResult bindingResult) throws ExecutionControl.NotImplementedException {
//        if(bindingResult.hasErrors()){
//            System.out.println("there were errors");
//            bindingResult.getAllErrors().forEach(error->{
//                System.out.println(error.getObjectName()+error.getDefaultMessage());
//            });
//            return "redirect:/newplayer";
//        }else{
//            playerService.hirePlayer(player);
//            return "redirect:/players";
//        }}
//
//    //    //drugi sposob , by dostac "id"
//    @RequestMapping(value="/player/delete/{id}")
//    public String firePlayer(@PathVariable("id") Integer id) throws ExecutionControl.NotImplementedException {
//        playerService.firePlayer(id);
//        return "redirect:/players";
//    }
//
//    @PostMapping("/firstsquadchoice")
//   public String getFirstSquad(@PathVariable long id,Model model) throws ExecutionControl.NotImplementedException {
//
//        Player player = this.playerService.getPlayerById(id);
//        List<Player> firstSquad = new ArrayList<>();
//        if (player.isFirstSquadPlayer()) {
//            model.addAttribute("players", player);
//            firstSquad.add(player);
//        }
//        System.out.println("--------------first squad-----------------");
//        System.out.println(firstSquad);
//        return "redirect:/firstsquad";
//    }
//
//
//    @RequestMapping(value="/callfor11")
//    public String setPlayerFor11(@PathVariable("id") Integer id) throws ExecutionControl.NotImplementedException {
//        playerService.setPlayerFor11(id);
//        return "redirect:/players";
//    }
//
//}


        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
//        import pl.clockworkjava.gnomix.controllers.dto.GuestCreationDTO;
//        import pl.clockworkjava.gnomix.controllers.dto.GuestUpdateDTO;
//        import pl.clockworkjava.gnomix.domain.guest.Guest;
//        import pl.clockworkjava.gnomix.domain.guest.GuestService;


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
    public String createNewPlayer(){
        return "playerform";
    }

    @PostMapping
    public String handleCreateNewPlayer
            (PlayerContractingDTO playerDTO){
        System.out.println(playerDTO);
        this.playerService.createNewPlayer(playerDTO);

        return "redirect:/player";
    }

    @GetMapping("/delete/{id}")
    public String removePlayer(@PathVariable("id") long id){
        this.playerService.removeById(id);

        return "redirect:/player";
    }

    @GetMapping("/edit/{id}")
    public String editPlayer(@PathVariable long id, Model model){
        Player player=this.playerService.getPlayerById(id);
        model.addAttribute("player", player);

        return "editPlayer";
    }

    @PostMapping("/edit")
    public String editPlayer(PlayerUpdateDTO updatedPlayer){
        this.playerService.update(updatedPlayer);

        return "redirect:/player";
    }

}
