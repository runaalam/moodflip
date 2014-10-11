package au.moodflip.cardgame.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.validation.constraints.AssertFalse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.cardgame.model.CgUser;
import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.service.CgUserManager;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;

@Controller
@RequestMapping(value = "/card-game")
public class CardGameController {
	private static final Logger logger = LoggerFactory.getLogger(CardGameController.class);
	private final String FOLDER = "card-game";
	@Autowired
	private CgUserManager cgUserManager;
    @Autowired
    private UserManager userManager;
    
	@RequestMapping
	public ModelAndView home(Locale locale, Principal principal, Model model) {
		logger.info("Welcome to the card game!");
		ModelAndView mav = new ModelAndView(FOLDER + "/cardGame");
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		
		if (cgUser == null){ // store user in cgUser so we don't have to alter user code 
			cgUser = cgUserManager.add(new CgUser(user.getId()));
			System.out.println("created new cguser");
		}
		System.out.println("cgUser is: " + cgUser);
		
		Mission current = cgUser.getCurrentMission();
		if (current != null){
			model.addAttribute("title", cgUser.getCurrentMission().getCard().getTitle());
			model.addAttribute("level", String.valueOf(cgUser.getCurrentMission().getCard().getLevel()));
			model.addAttribute("symptom", cgUser.getCurrentMission().getCard().getSymptom().getText());
			model.addAttribute("text", current.getText());
			mav = new ModelAndView(FOLDER + "/cardGame", "mission", model);
		}
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, params="nextMission")
	public ModelAndView getNextMission(Principal principal, Model model){
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		
		Mission next = cgUser.getCurrentMission().getNext();
		model.addAttribute("title", cgUser.getCurrentMission().getCard().getTitle());
		model.addAttribute("level", String.valueOf(cgUser.getCurrentMission().getCard().getLevel()));
		model.addAttribute("symptom", cgUser.getCurrentMission().getCard().getSymptom().getText());
		if (next != null){
			model.addAttribute("text", next.getText());  
		}
		cgUser.setCurrentMission(next);
		cgUserManager.update(cgUser);
		return new ModelAndView(FOLDER + "/cardGame", "mission", model);
	}
	
	@RequestMapping(method = RequestMethod.GET, params="finishCard")
	public ModelAndView getEndCard(Principal principal, Model model){
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		cgUser.setCurrentMission(null); // set to no mission for user
		cgUserManager.update(cgUser);
		return new ModelAndView(FOLDER + "/cardGame", "model", model);
	}
	
	@RequestMapping(method = RequestMethod.GET, params="newCard")
	public ModelAndView getNewCard(Principal principal, Model model){
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		cgUser.setCurrentMission(null); // set to no mission for user
		cgUserManager.update(cgUser);
		return new ModelAndView(FOLDER + "/cardGame", "model", model);
	}
}
