package au.moodflip.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
//
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Redirect to user homepage.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		logger.info("Welcome home!, " + "Locale: " + locale);
		ModelAndView mav = new ModelAndView("redirect:/user-homepage");
		return mav;
	}
	
	@RequestMapping(value = "/dev", method = RequestMethod.GET)
	public ModelAndView homeDev(Locale locale) {
		logger.info("Welcome home!, " + "Locale: " + locale);
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}
	
}
