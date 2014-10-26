package au.moodflip.comm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.comm.model.CardSuggest;
import au.moodflip.comm.service.CardSuggestService;
import au.moodflip.personalisation.service.UserManager;

@Controller
@RequestMapping(value = "/forums/card_suggest")
public class CardSuggestController {
	
	private static final String FOLDER = "communication";
	
	@Autowired
	private CardSuggestService cardSuggestService;
	
	@Autowired
	private CardManager cardManager;
	
	@Autowired
	private UserManager userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<CardSuggest> listSuggestedCards(@RequestParam("topicId") Long topicId) {
		List<CardSuggest> cards = cardSuggestService.listSuggestedCardsByTopicId(topicId);
		return cards;
	}
	
	@RequestMapping(value = "/listCards", method = RequestMethod.GET)
	public @ResponseBody Set<Card> listCards(@RequestParam(value = "symptom", required = false) String symptom) {
		Map<String, Card.Symptom> lookup = new HashMap<String, Card.Symptom>();
		for (Card.Symptom s : Card.Symptom.values()) {
            lookup.put(s.getText(), s);
        }
		Set<Card> cards = symptom == null ? cardManager.getCards() : cardManager.getCards(lookup.get(symptom));
		return cards;
	}
	
	@RequestMapping(value = "/listSymptoms", method = RequestMethod.GET)
	public @ResponseBody Card.Symptom[] listSymptoms() {
		Card.Symptom[] symptoms = Card.Symptom.values();
		return symptoms;
	}
	
	@RequestMapping(value = "/up_vote/{suggestId}", method = RequestMethod.GET)
	public @ResponseBody Boolean upVoteComment(@PathVariable("suggestId") Long suggestId) {
		cardSuggestService.upVoteComment(suggestId);
		return true;
	}

	@RequestMapping(value = "/down_vote/{suggestId}", method = RequestMethod.GET)
	public @ResponseBody Boolean downVoteComment(@PathVariable("suggestId") Long suggestId) {
		cardSuggestService.downVoteComment(suggestId);
		return true;
	}

}
