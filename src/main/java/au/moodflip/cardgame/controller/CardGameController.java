package au.moodflip.cardgame.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.service.CardManager;

@Controller
@RequestMapping(value = "/card-game")
public class CardGameController {
	private static final Logger logger = LoggerFactory.getLogger(CardGameController.class);
	private final String FOLDER = "card-game";
	@Autowired
	private CardManager cardManager;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		logger.info("Welcome to the card game!");
		return new ModelAndView(FOLDER + "/cardGame");
	}
	
	@RequestMapping(value = "/customCards")
	public ModelAndView customCards(Model model){
		logger.info("Custom cards");
		model.addAttribute("customCards", cardManager.getCards());
		return new ModelAndView(FOLDER + "/customCards", "model", model);
	}
	
	@RequestMapping(value = "/customCards", method = RequestMethod.GET, params="new")
	public ModelAndView newCard(Model model){
		logger.info("New card");
		model.addAttribute(new Card());
		model.addAttribute("symptoms", Card.Symptom.values());
		return new ModelAndView(FOLDER + "/edit", "model", model);
	}
	
	@RequestMapping(value = "/customCards", method = RequestMethod.POST, params="new")
	public ModelAndView addCard(Card card, Model model){
		logger.info("add card {}", card);
		logger.info("cardManager {}", cardManager);
		cardManager.addCard(card);
		model.addAttribute("customCards", cardManager.getCards());
		return new ModelAndView(FOLDER + "/customCards", "model", model);
	}

}
