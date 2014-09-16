package au.moodflip.cardgame.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/card-game")
public class CardGameController {
	private static final Logger logger = LoggerFactory
			.getLogger(CardGameController.class);

	private final String FOLDER = "card-game";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		logger.info("Welcome to the card game!");
		ModelAndView mav = new ModelAndView(FOLDER + "/cardGame");
		return mav;
	}

}
