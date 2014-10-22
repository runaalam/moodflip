package au.moodflip.cardgame.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import au.moodflip.cardgame.model.CgUser;
import au.moodflip.cardgame.model.MainPlayHistoryItem;
import au.moodflip.cardgame.service.CardEventManager;
import au.moodflip.cardgame.service.CgUserManager;
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
	private CgUserManager cgUserManager;
	@Autowired
	private UserManager userManager;
	
	@RequestMapping
	public String playHistory(Model model, Principal principal){
		// get user
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		if (cgUser == null){ // store user in cgUser so we don't have to alter user code 
			cgUser = cgUserManager.add(new CgUser(user.getId()));
			System.out.println("created new cguser");
		}
		model.addAttribute(cgUser);
		model.addAttribute("mainPlayHistoryItems", cardEventManager.getMainPlayHistory(cgUser));
		return FOLDER + "/playHistory"; 
	}
	
	@RequestMapping(method = RequestMethod.GET, params="cardId")
	public String getCardHistory(Model model, @RequestParam(value="cardId", required=false) long cardId){
		CgUser cgUser = (CgUser)model.asMap().get("cgUser");
		model.addAttribute("mainPlayHistoryItems", cardEventManager.getMainPlayHistory(cgUser));
		model.addAttribute("cardPlayHistoryItems", cardEventManager.getCardPlayHistory(cgUser, cardId));
		return FOLDER + "/playHistory";
	}
}
