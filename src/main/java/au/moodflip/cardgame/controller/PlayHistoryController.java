package au.moodflip.cardgame.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import au.moodflip.cardgame.service.CardEventManager;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;

@Controller
@RequestMapping(value = "/card-game/playHistory")
@SessionAttributes("cgUser")
public class PlayHistoryController {
	private final String FOLDER = "card-game";
	@Autowired
	private CardEventManager cardEventManager;
	@Autowired
	private UserManager userManager;
	
	@RequestMapping
	public String playHistory(Model model, Principal principal){
		// get user
		User user = userManager.getUserByUsername(principal.getName());
		model.addAttribute(user);
		model.addAttribute("mainPlayHistoryItems", cardEventManager.getMainPlayHistory(user));
		model.addAttribute("details", cardEventManager.getCardPlayHistory(user));
		return FOLDER + "/playHistory"; 
	}
	
	@RequestMapping(method = RequestMethod.GET, params="cardId")
	public String getCardHistory(Model model, @RequestParam(value="cardId", required=false) long cardId, Principal principal){
		User user = userManager.getUserByUsername(principal.getName());
		model.addAttribute("mainPlayHistoryItems", cardEventManager.getMainPlayHistory(user));
		model.addAttribute("cardPlayHistoryItems", cardEventManager.getCardPlayHistory(user, cardId));
		return FOLDER + "/playHistory";
	}
}
