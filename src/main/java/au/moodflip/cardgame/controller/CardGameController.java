package au.moodflip.cardgame.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.CgUser;
import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.cardgame.service.CgUserManager;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;

@Controller
@RequestMapping(value = "/card-game")
public class CardGameController {
	private static final Logger logger = LoggerFactory.getLogger(CardGameController.class);
	private final String FOLDER = "card-game";
	@Autowired
	private CardManager cardManager;
	@Autowired
	private CgUserManager cgUserManager;
    @Autowired
    private UserManager userManager;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		logger.info("Welcome to the card game!");
		return new ModelAndView(FOLDER + "/cardGame");
	}
	
	@RequestMapping(value = "/customCards")
	public ModelAndView customCards(Model model, Principal principal){
		logger.info("Custom cards all");
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		logger.info("user logged in {}", user.getId());
		if (cgUser != null){ // user has cards
			model.addAttribute("customCards", cardManager.getCards(cgUser.getCards()));
		}else{ //user doesn't exist
			cgUserManager.add(new CgUser(user.getId()));
		}
		return new ModelAndView(FOLDER + "/customCards", "model", model);
	}
	
	@RequestMapping(value = "/customCards", method = RequestMethod.GET, params="new")
	public ModelAndView newCard(Model model){
		logger.info("Create new card");
		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());	// 1 empty mission for new cards
		Card newCard = new Card();
		newCard.setMissions(missions);
		model.addAttribute(newCard);
		model.addAttribute("symptoms", Card.Symptom.values());
		logger.info("returning modelandview from newCard()");
		return new ModelAndView(FOLDER + "/edit", "model", model);
	}
	
	@RequestMapping(value = "/customCards", method = RequestMethod.POST, params="new")
	public String addCard(@Valid Card card, BindingResult bindingResult, RedirectAttributes ra, Principal principal){
		logger.info("Save new card");
		if (bindingResult.hasErrors()){
			logger.info("errors {}: {}", bindingResult.getFieldErrorCount(), bindingResult.getFieldErrors());
			return FOLDER + "/edit";
		}
		logger.info("add card {}", card);
		logger.info("cardManager {}", cardManager);
		User user = userManager.getUserByUsername(principal.getName());
		cardManager.add(card);
		logger.info("add cardId {} to user {}", card.getCardId(), user.getId());
		CgUser cgUser = cgUserManager.getById(user.getId());
		cgUserManager.addCard(cgUser, card);
		logger.info("Saved card " + card);
		return "redirect:/" + FOLDER + "/customCards";
	}

	@RequestMapping(value = "/customCards", method = RequestMethod.GET, params="edit")
	public ModelAndView editCard(Model model, @RequestParam(value="edit", required=false) long cardId){
		logger.info("Edit card");
		model.addAttribute(cardManager.getById(cardId));
		Card card = cardManager.getById(cardId);
		logger.info("Got card: " + card);
		logger.info("returning from Edit card");
		return new ModelAndView(FOLDER + "/edit", "model", model);
	}
	
	@RequestMapping(value = "/customCards", method = RequestMethod.POST, params="edit")
	public ModelAndView editCard(@Valid Card card, BindingResult result, Model model, @RequestParam(value="edit", required=false) long cardId){
		logger.info("Save card edit");
		if (result.hasErrors()){
			model.addAttribute("status","Update failed");
			return new ModelAndView(FOLDER + "/edit");
		}
		model.addAttribute("status","Card updated!");
		cardManager.update(card);
		return new ModelAndView(FOLDER + "/edit");
	}
	
	@RequestMapping(value = "/customCards", method = RequestMethod.GET, params="delete")
	public String deleteCard(@RequestParam(value="delete", required=false) long cardId, RedirectAttributes ra){
		cardManager.delete(cardId);
		return "redirect:/" + FOLDER + "/customCards";
	}
	
}
