package pl.com.k1313.goal4goal.controllers;


import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.com.k1313.goal4goal.components.TimeComponent;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.UserInformation;
import pl.com.k1313.goal4goal.services.PlayerService;

import java.util.List;

@Controller
public class PlayerController {

    @Autowired
    TimeComponent timeComponent;

    @Autowired
    UserInformation userInformation;

    @Autowired
    PlayerService playerService;

    @RequestMapping("/players")
    public String getPlayers(Model model) throws ExecutionControl.NotImplementedException {
        List<Player> allPlayers = playerService.getAllPlayers();
        model.addAttribute("players", allPlayers);
        model.addAttribute("timeComponent", timeComponent);
        model.addAttribute("userInformation", userInformation);
        return "players";
    }

    @RequestMapping("/player")
    //przedstawiony 1 sposob , by dostac "id", a ponizej drugi sposob
    public String getPlayer(@RequestParam("id") Integer id, Model model) throws ExecutionControl.NotImplementedException {
        Player player = playerService.getPlayer(id);
        model.addAttribute("player", player);
        model.addAttribute("timeComponent", timeComponent);
        model.addAttribute("userInformation", userInformation);
        return "player";
    }


    @RequestMapping("/newplayer")
    public String hirePlayer(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("timeComponent", timeComponent);
        model.addAttribute("userInformation", userInformation);
        return "playerform";
    }

    @RequestMapping(value = "/players", method = RequestMethod.POST)
    public String savePlayer(@Validated Player player, BindingResult bindingResult) throws ExecutionControl.NotImplementedException {
        if(bindingResult.hasErrors()){
            System.out.println("there were errors");
            bindingResult.getAllErrors().forEach(error->{
                System.out.println(error.getObjectName()+error.getDefaultMessage());
            });
            return "redirect:/newplayer";
        }else{
            playerService.hirePlayer(player);
            return "redirect:/players";
        }}

    //    //drugi sposob , by dostac "id"
    @RequestMapping(value="/player/delete/{id}")
    public String firePlayer(@PathVariable("id") Integer id) throws ExecutionControl.NotImplementedException {
        playerService.firePlayer(id);
        return "redirect:/players";
    }

    @RequestMapping(value = "/firstsquadchoice", method = RequestMethod.POST)
//    public String getFirstSquad(Model model) throws ExecutionControl.NotImplementedException {
      public String getFirstSquad(@RequestParam String firstSquadPlayer) throws ExecutionControl.NotImplementedException {
        boolean firstSquadPlayerBoolean=Boolean.parseBoolean(firstSquadPlayer);
        List<Player> firstSquadPlayers = playerService.getFirstSquad( firstSquadPlayerBoolean);
//        List<Player> players = playerService.getAllPlayers();
//        model.addAttribute("players", players);
//        model.addAttribute("firstsquadplayers", firstSquadPlayers);
//        model.addAttribute("timeComponent", timeComponent);
//        model.addAttribute("userInformation", userInformation);
        return "redirect:/players";
    }

    //obsluga Submita do zmiany statusu firstSquad
//@RequestMapping("/players/callfor11")
//public String setPlayerFor11(@RequestParam("id") Integer id, Model model) throws ExecutionControl.NotImplementedException {
//    Player player = playerService.getPlayer(id);
//    playerService.setPlayerFor11(id);
//    model.addAttribute("player", player);
//    return "players";
//}
    //    //drugi sposob , by dostac "id"

//    @RequestMapping(value="/players/callfor11/{id}")
//    public String setPlayerFor11(@PathVariable("id") Integer id) throws ExecutionControl.NotImplementedException {
//        playerService.setPlayerFor11(id);
//        return "redirect:/players";
//    }

}