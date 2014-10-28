package au.moodflip.cardgame.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.CardSurvey;
import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.model.Task;
import au.moodflip.cardgame.model.UsersCard;
import au.moodflip.cardgame.model.UsersCard.UsersCardPK;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.cardgame.service.UsersCardManager;
import au.moodflip.comm.service.NotificationService;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;

@Controller
@RequestMapping(value = "/card-game/customCards")
@SessionAttributes("globalPoints")
public class CustomCardController {
	private static final Logger logger = LoggerFactory.getLogger(CustomCardController.class);
	private final String FOLDER = "card-game";
	@Autowired
	private CardManager cardManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private UsersCardManager usersCardManager;
    @Autowired
    private NotificationService notificationService;
	
	@RequestMapping
	public ModelAndView customCards(Model model, Principal principal){
		logger.info("Enter customCards()");
		User user = userManager.getUserByUsername(principal.getName());
		Set<Card> cards = cardManager.getCards(usersCardManager.getAll(user.getId()));
		model.addAttribute("customCards", cards);
		logger.info("Exit customCards()");
		return new ModelAndView(FOLDER + "/customCards", "model", model);
	}
	
	@RequestMapping(method = RequestMethod.GET, params="new")
	public ModelAndView newCard(Model model){
		logger.info("Enter newCard()");
		Card newCard = new Card();
		List<Task> missions = new AutoPopulatingList<Task>(Mission.class);
		missions.add(new Mission(""));	// 1 empty mission for new cards
		newCard.setTasks(missions);
		model.addAttribute(newCard);
		model.addAttribute("symptoms", Card.Symptom.values());
		model.addAttribute("lastMissionIndex", "0");
		logger.info("Exit newCard()");
		return new ModelAndView(FOLDER + "/edit");
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		logger.info("Enter initBinder()");
	    binder.registerCustomEditor(Task.class, "tasks", new TaskPropertyEditor());
	}
	
	@RequestMapping(method = RequestMethod.POST, params="new")
	public String saveNewCard(@Valid Card card, BindingResult bindingResult, RedirectAttributes ra, Principal principal){
		logger.info("Enter saveNewCard()");
		if (bindingResult.hasErrors()){
			logger.info("errors {}: {}", bindingResult.getFieldErrorCount(), bindingResult.getFieldErrors());
			return FOLDER + "/edit";
		}
		logger.info("about to add card {}", card);
		User user = userManager.getUserByUsername(principal.getName());
		CardSurvey cardSurvey = new CardSurvey("This task was helpful");
		card.getTasks().add(cardSurvey);
		cardManager.add(card);
		logger.info("added card " + card);
		usersCardManager.add(new UsersCard(user.getId(), card.getCardId()));
		logger.info("Exit saveNewCard()");
		return "redirect:/" + FOLDER + "/customCards";
	}

	@RequestMapping(method = RequestMethod.GET, params="edit")
	public ModelAndView editCard(Model model, @RequestParam(value="edit", required=false) long cardId){
		logger.info("Enter editCard()");
		Card card = cardManager.getById(cardId);
		model.addAttribute(card);
		model.addAttribute("lastMissionIndex", card.getTasks().size() - 2); // last element is cardSurvey
		logger.info("Exit editCard() tasks: " + card.getTasks().size() );
		return new ModelAndView(FOLDER + "/edit", "model", model);
	}
	
	@RequestMapping(method = RequestMethod.POST, params="edit")
	public ModelAndView saveEditCard(@Valid Card card, BindingResult result, Model model, @RequestParam(value="edit", required=false) long cardId){
		logger.info("Enter saveEditCard()");
		if (result.hasErrors()){
			model.addAttribute("status","Update failed");
			return new ModelAndView(FOLDER + "/edit");
		}
		logger.info("Merging with card: " + cardManager.getById(card.getCardId())); 
		model.addAttribute("status","Card updated!");
		logger.info("update card: " + card);
		CardSurvey cardSurvey = new CardSurvey("This task was helpful");
		card.getTasks().add(cardSurvey);
		cardManager.update(card);
		model.addAttribute("lastMissionIndex", card.getTasks().size() - 2); // last element is cardSurvey
		logger.info("Exit saveEditCard()");
		return new ModelAndView(FOLDER + "/edit");
	}
	
	@RequestMapping(method = RequestMethod.GET, params="delete")
	public String deleteCard(@RequestParam(value="delete", required=false) long cardId, RedirectAttributes ra, Principal principal){
		User user = userManager.getUserByUsername(principal.getName());
		logger.info("deleting userid {} cardid {}", user.getId(), cardId);
		usersCardManager.delete(new UsersCardPK(user.getId(), cardId));
		return "redirect:/" + FOLDER + "/customCards";
	}
	
	@RequestMapping(method = RequestMethod.GET, params={"user", "cardId"})
	@ResponseBody
	public String shareCard(Model model, @RequestParam(value="user") String user, @RequestParam(value="cardId") long cardId, RedirectAttributes ra, Principal principal){
		logger.info("Enter shareCard()");
		logger.info("got user:" + user + " cardId:" + cardId);
		Card card = cardManager.getById(cardId);
		User u1 = userManager.getUserByUsername(principal.getName());
		User u2 = userManager.getUserByUsername(user);
		if (u2 == null){
			return "invalid user";
		}
		notificationService.createNotification("User " + u1.getUsername() + " shared card \"" + card.getTitle() + "\" with you", "card-game/cardBrowser?view=" + cardId, u2.getId());
		return "share sent";
	}
}
