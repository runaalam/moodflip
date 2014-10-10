package au.moodflip.cardgame.controller;

import java.security.Principal;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.CgUser;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.cardgame.service.CgUserManager;
import au.moodflip.cardgame.service.UsersCardManager;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;

@Controller
@RequestMapping(value="/card-game/myCards")
public class MyCardsController {
	private static final Logger logger = LoggerFactory.getLogger(MyCardsController.class);
	private final String FOLDER = "card-game";
	@Autowired
	private CardManager cardManager;
	@Autowired
	private UsersCardManager usersCardManager;
    @Autowired
    private UserManager userManager;
	@Autowired
	private CgUserManager cgUserManager;
	
	@RequestMapping
	public ModelAndView myCards(Model model, Principal principal){
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		Set<Card> cards = new TreeSet<Card>(cardManager.getCards(usersCardManager.getAll(cgUser.getCgUserId())));
		model.addAttribute("cards", cards);
		return new ModelAndView(FOLDER + "/myCards", "model", model);
	}
	
	@RequestMapping(method = RequestMethod.GET, params="play")
	public ModelAndView playCard(Model model, @RequestParam(value="play", required=false) long cardId){
		Card card = cardManager.getById(cardId);
		model.addAttribute("card", card);
		return new ModelAndView(FOLDER + "/cardGame", "model", model);
	}
}
