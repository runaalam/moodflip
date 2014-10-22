package au.moodflip.cardgame.controller;

import java.security.Principal;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.CardSurvey;
import au.moodflip.cardgame.model.CardSurvey.Answer;
import au.moodflip.cardgame.model.CgUser;
import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.model.Task;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.cardgame.service.CgUserManager;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
//
@Controller
@RequestMapping(value = "/card-game")
public class CardGameController {
	private static final Logger logger = LoggerFactory.getLogger(CardGameController.class);
	private final String FOLDER = "card-game";
	@Autowired
	private CgUserManager cgUserManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private CardManager cardManager;
    
	@RequestMapping
	public String home(Locale locale, Principal principal, Model model) {
		logger.info("Welcome to the card game!");
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		
		if (cgUser == null){ // store user in cgUser so we don't have to alter user code 
			cgUser = cgUserManager.add(new CgUser(user.getId()));
			System.out.println("created new cguser");
		}
		System.out.println("cgUser is: " + cgUser);
		
		Task task = cgUser.getCurrentTask();
		if (task != null){
			if (task instanceof Mission){
				model.addAttribute(((Mission)task));
				logger.info("Getting mission " + (Mission)task);
			}else if (task instanceof CardSurvey){
				model.addAttribute(((CardSurvey)task));
				model.addAttribute("answers", Answer.values());
				logger.info("Getting cardSurvey " + (CardSurvey)task);
			}
		}
		return FOLDER + "/cardGame";
	}
	
	@RequestMapping(method = RequestMethod.POST, params="nextMission")
	public String getNextMission(Principal principal, Model model){
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		
		Task task = cgUser.getCurrentTask().getNext();
		if (task != null){
			if (task instanceof Mission){
				model.addAttribute(((Mission)task));
			}else if (task instanceof CardSurvey){
				model.addAttribute(((CardSurvey)task));
				model.addAttribute("answers", Answer.values());
			}
		}
		cgUser.setCurrentTask(task);
		cgUserManager.update(cgUser);
		return FOLDER + "/cardGame";
	}
	
	@RequestMapping(method = RequestMethod.POST, params="finish")
	public String getEndCard(CardSurvey cardSurvey, Principal principal, Model model){
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		logger.info("answered: " + cardSurvey.getAnswer());
		rateCard(cgUser.getCurrentTask().getCard().getCardId(), cardSurvey.getAnswer());
		cgUser.setCurrentTask(null); // set to no mission for user
		cgUserManager.update(cgUser);
		return "redirect:/" + FOLDER;
	}
	
	@RequestMapping(method = RequestMethod.POST, params="newCard")
	public ModelAndView getNewCard(Principal principal, Model model){
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		cgUser.setCurrentTask(null); // set to no mission for user
		cgUserManager.update(cgUser);
		return new ModelAndView(FOLDER + "/cardGame", "model", model);
	}
	
	// based on exponential moving avg
	// http://www.bennadel.com/blog/1627-create-a-running-average-without-storing-individual-values.htm
	private void rateCard(long cardId, Answer answer){
		Card card = cardManager.getById(cardId);
		double baseAvg = card.getAvgRating();
		long ratingCount = card.getCompletions();
		int newRating = answer.ordinal() + 1; // ordinal is zero based so add 1 to get rating between 1-5
		double rating = (baseAvg * ratingCount + newRating) / (ratingCount + 1); 
		card.setCompletions(card.getCompletions() + 1);
		card.setAvgRating(rating);
		cardManager.update(card);
	}
}
