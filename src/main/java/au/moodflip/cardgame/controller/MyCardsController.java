package au.moodflip.cardgame.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.CardEvent;
import au.moodflip.cardgame.model.CgUser;
import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.model.MissionEvent;
import au.moodflip.cardgame.model.TaskEvent;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.cardgame.service.CardEventManager;
import au.moodflip.cardgame.service.CgUserManager;
import au.moodflip.cardgame.service.UsersCardManager;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
import au.moodflip.userpage.model.Activity;
import au.moodflip.userpage.service.ActivityService;

@Controller
@RequestMapping(value="/card-game/myCards")
@SessionAttributes("globalPoints")
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
	@Autowired
	private CardEventManager cardPlayedManager;
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping
	public String myCards(Model model, Principal principal){
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		Set<Card> cards = new TreeSet<Card>(cardManager.getCards(usersCardManager.getAll(cgUser.getCgUserId())));
//		Set<Card> cards = new TreeSet<Card>(cardManager.getCards()); // get all cards
		model.addAttribute("cards", cards);
		return FOLDER + "/myCards";
	}
	
	@RequestMapping(method = RequestMethod.GET, params="play")
	public String playCard(Model model, @RequestParam(value="play", required=false) long cardId, Principal principal){
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		Card card = cardManager.getById(cardId);
		Mission m = null;
		if (cgUser.getCurrentTaskEvent() == null){ // user not doing task
			if (card.getTasks().get(0) instanceof Mission){
				m = (Mission)card.getTasks().get(0);
				// add new cardPlayed
				List<TaskEvent> teList = new ArrayList<TaskEvent>();
				MissionEvent me = new MissionEvent(m, 0, new Date());
				CardEvent ce = new CardEvent(cgUser, card, new Date(), 0, false);
				me.setCardEvent(ce);
				teList.add(me);
				ce.setTaskEvents(teList);
				cardPlayedManager.add(ce);
				// add current mission to user
				cgUser.setCurrentTaskEvent(me);
				cgUserManager.update(cgUser);
				model.addAttribute(m);
				// add playing card activity to user homepage
				activityService.addActivity(new Activity(user, "started playing card " + card.getTitle(), new Date())) ;
			}
		}else{
			logger.info("Already doing a mission");
		}
		return "redirect:/" + FOLDER;
	}
}
