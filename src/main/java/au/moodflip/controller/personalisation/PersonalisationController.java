package au.moodflip.controller.personalisation;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/personalisation")
public class PersonalisationController {
	private static final Logger logger = LoggerFactory
			.getLogger(PersonalisationController.class);

	private final String FOLDER = "personalisation";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		logger.info("Welcome to the personalisation system!");
		ModelAndView mav = new ModelAndView(FOLDER + "/personalisation");
		return mav;
	}

}