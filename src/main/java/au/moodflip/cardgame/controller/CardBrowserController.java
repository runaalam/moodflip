package au.moodflip.cardgame.controller;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import au.moodflip.cardgame.model.PlaylistItem;
import au.moodflip.cardgame.model.UsersCard;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.cardgame.service.PlaylistManager;
import au.moodflip.cardgame.service.UsersCardManager;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;

@Controller
@RequestMapping(value = "/card-game/cardBrowser")
public class CardBrowserController {
	private static final Logger logger = LoggerFactory.getLogger(CardBrowserController.class);
	private final String FOLDER = "card-game";
	@Autowired
	private CardManager cardManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private UsersCardManager usersCardManager; 
	@Autowired
	private PlaylistManager playlistManager;
	
	@RequestMapping
	public String getCards(Model model){
		logger.info("Enter getCards()");
		model.addAttribute("cards", cardManager.getCards());
		return FOLDER + "/cardBrowser";
	}
	
	@RequestMapping(method=RequestMethod.GET, params="view")
	public String viewCard(Model model, @RequestParam(value="view", required=false) long cardId){
		logger.info("Enter viewCard()");
		model.addAttribute(cardManager.getById(cardId));
		return FOLDER + "/cardBrowser";
	}
	
	@RequestMapping(method=RequestMethod.GET, params="addToMyCards")
	public String addToMyCards(Model model, @RequestParam(value="addToMyCards", required=false) long cardId, Principal principal){
		logger.info("Enter addToMyCards()");
		User user = userManager.getUserByUsername(principal.getName());
		usersCardManager.add(new UsersCard(user.getId(), cardId));
		model.addAttribute(cardManager.getById(cardId));
		return "redirect:/" + FOLDER + "/cardBrowser";
	}
	
	@RequestMapping(method=RequestMethod.GET, params="addToPlaylist")
	public String addToPlaylist(Model model, @RequestParam(value="addToPlaylist", required=false) long cardId, Principal principal){
		logger.info("Enter addToPlaylist()");
		User user = userManager.getUserByUsername(principal.getName());
		playlistManager.appendItem(new PlaylistItem(cardId), user.getId());
		return "redirect:/" + FOLDER + "/cardBrowser";
	}
}
