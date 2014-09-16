package au.moodflip.moodtrack.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/mood-tracking")
public class MoodTrackingController {
	private static final Logger logger = LoggerFactory
			.getLogger(MoodTrackingController.class);

	private final String FOLDER = "mood-tracking";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		logger.info("Welcome to the mood tracking system!");
		ModelAndView mav = new ModelAndView(FOLDER + "/moodTracking");
		return mav;
	}

}