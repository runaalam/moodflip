package au.moodflip.moodtrack.controller;

import au.moodflip.moodtrack.model.Charts;
import au.moodflip.moodtrack.model.Data;



import au.moodflip.moodtrack.service.DataService;
import au.moodflip.moodtrack.validator.DataValidator;
import au.moodflip.moodtrack.validator.ReportValidator;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@SessionAttributes("command")
public class ChartsController {

    private static final Logger logger = LoggerFactory.getLogger(ChartsController.class);

    private final String FOLDER = "mood-tracking";

    @Autowired
    private UserManager userManager;

    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/charts", method = RequestMethod.GET)
    public String getReport(Principal principal,
                            ModelMap model) {
        User user = userManager.getUserByUsername(principal.getName());

        model.addAttribute("command", new Charts(user));

        return FOLDER + "/charts";
    

   
    }
}