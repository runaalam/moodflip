package au.moodflip.cardgame.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import au.moodflip.cardgame.model.CardSurvey;
import au.moodflip.cardgame.model.CgUser;
import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.model.UsersCard;
import au.moodflip.cardgame.model.UsersCard.UsersCardPK;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.cardgame.service.CgUserManager;
import au.moodflip.cardgame.service.UsersCardManager;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;

@Controller
@RequestMapping(value = "/card-game/customCards")
public class CustomCardController {
	private static final Logger logger = LoggerFactory.getLogger(CustomCardController.class);
	private final String FOLDER = "card-game";
	@Autowired
	private CardManager cardManager;
	@Autowired
	private CgUserManager cgUserManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private UsersCardManager usersCardManager;
	
	@RequestMapping
	public ModelAndView customCards(Model model, Principal principal){
		logger.info("Custom cards all");
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		logger.info("user logged in {}", user.getId());
		Set<Card> cards = cardManager.getCards(usersCardManager.getAll(cgUser.getCgUserId()));
		model.addAttribute("customCards", cards);
//			Iterator<Card> it = cards.iterator();
//			while(it.hasNext()){
//				Card c = it.next();
//				System.out.println(c);
//				for (Mission m : c.getMissions()){
//					System.out.println("\tprev:" + m.getPrev() + "\tCur:" + m + "\tNext:" + m.getNext());
//				}
//			}
		return new ModelAndView(FOLDER + "/customCards", "model", model);
	}
	
	@RequestMapping(method = RequestMethod.GET, params="new")
	public ModelAndView newCard(Model model){
		logger.info("Create new card");
		Card newCard = new Card();
		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());	// 1 empty mission for new cards
		newCard.setMissions(missions);
		model.addAttribute(newCard);
		model.addAttribute("symptoms", Card.Symptom.values());
		logger.info("returning modelandview from newCard()");
		return new ModelAndView(FOLDER + "/edit", "model", model);
	}
	
	@RequestMapping(method = RequestMethod.POST, params="new")
	public String addCard(@Valid Card card, BindingResult bindingResult, RedirectAttributes ra, Principal principal){
		logger.info("Save new card");
		if (bindingResult.hasErrors()){
			logger.info("errors {}: {}", bindingResult.getFieldErrorCount(), bindingResult.getFieldErrors());
			return FOLDER + "/edit";
		}
		logger.info("add card {}", card);
		logger.info("cardManager {}", cardManager);
		User user = userManager.getUserByUsername(principal.getName());
		linkMissionsAndCard(card);
		cardManager.add(card);
		logger.info("add cardId {} to user {}", card.getCardId(), user.getId());
		CgUser cgUser = cgUserManager.getById(user.getId());
		usersCardManager.add(new UsersCard(cgUser.getCgUserId(), card.getCardId()));
		logger.info("Saved card " + card);
		return "redirect:/" + FOLDER + "/customCards";
	}

	@RequestMapping(method = RequestMethod.GET, params="edit")
	public ModelAndView editCard(Model model, @RequestParam(value="edit", required=false) long cardId){
		logger.info("Edit card");
		model.addAttribute(cardManager.getById(cardId));
		Card card = cardManager.getById(cardId);
		logger.info("Got card: " + card);
		logger.info("returning from Edit card");
		return new ModelAndView(FOLDER + "/edit", "model", model);
	}
	
	// updates the card for everyone who has it
	@RequestMapping(method = RequestMethod.POST, params="edit")
	public ModelAndView editCard(@Valid Card card, BindingResult result, Model model, @RequestParam(value="edit", required=false) long cardId){
		logger.info("Save card edit");
		if (result.hasErrors()){
			model.addAttribute("status","Update failed");
			return new ModelAndView(FOLDER + "/edit");
		}
		model.addAttribute("status","Card updated!");
		linkMissionsAndCard(card);
		cardManager.update(card);
		return new ModelAndView(FOLDER + "/edit");
	}
	
	@RequestMapping(method = RequestMethod.GET, params="delete")
	public String deleteCard(@RequestParam(value="delete", required=false) long cardId, RedirectAttributes ra, Principal principal){
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		logger.info("deleting userid {} cardid {}", cgUser.getCgUserId(), cardId);
		usersCardManager.delete(new UsersCardPK(cgUser.getCgUserId(), cardId));
		return "redirect:/" + FOLDER + "/customCards";
	}
	
	// link missions to each other.  link all missions to same card
	private void linkMissionsAndCard(Card card){
		List<Mission> missions = card.getMissions();
		for (int i=0; i < missions.size(); i++){
			if (i-1 >= 0){
				missions.get(i).setPrev(missions.get(i-1));
			}
			if (i+1 < missions.size()){
				missions.get(i).setNext(missions.get(i+1));
			}
			missions.get(i).setCard(card); 
		}
	}
}
