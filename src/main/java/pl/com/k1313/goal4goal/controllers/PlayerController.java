package pl.com.k1313.goal4goal.controllers;


import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.repository.PlayerRepository;
import pl.com.k1313.goal4goal.services.PlayerService;

import java.util.List;

@Controller
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @RequestMapping("/players")
    public String getPlayers(Model model) throws ExecutionControl.NotImplementedException {
        List<Player> allPlayers = playerService.getAllPlayers();
        model.addAttribute("players", allPlayers);
        return "players";
    }

    @RequestMapping("/player")
    public String getPlayer(@RequestParam("id") Integer id, Model model) throws ExecutionControl.NotImplementedException {
        Player player = playerService.getPlayer(id);
        model.addAttribute("player", player);
        return "player";
    }


    @RequestMapping("/newplayer")
    public String hirePlayer(Model model) {
        model.addAttribute("player", new Player());
        return "playerform";
    }

    @RequestMapping(value = "/players", method = RequestMethod.POST)
    public String savePlayer(Player player) throws ExecutionControl.NotImplementedException {
        playerService.savePlayer(player);
        return "redirect:/players";
    }
}
