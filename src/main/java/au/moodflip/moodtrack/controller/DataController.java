package au.moodflip.moodtrack.controller;

import au.moodflip.moodtrack.service.DataService;


import au.moodflip.moodtrack.model.Data;

import au.moodflip.moodtrack.validator.DataValidator;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
import au.moodflip.userpage.model.Activity;
import au.moodflip.userpage.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@SessionAttributes("command")
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    private final String FOLDER = "mood-tracking";

    @Autowired
    private UserManager userManager;

    @Autowired
    private DataService dataService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String data(Principal principal,
                       Model model) {
        User user = userManager.getUserByUsername(principal.getName());

        model.addAttribute("command", new Data(user));

        return FOLDER + "/data";
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public ModelAndView saveData(@ModelAttribute("command") Data data,
                                 Principal principal,
                                 BindingResult result,
                                 Model model) {

        new DataValidator().validate(data, result);
        if (result.hasErrors()) {
            return new ModelAndView(FOLDER + "/data");
        }

        Data oldData = dataService.findData(data);
        String activityDesc = null;

        if (oldData == null) {
            dataService.save(data);
            activityDesc = "User has entered mood data for";
        } else {
            if (data.getId() != oldData.getId()) {
                model.addAttribute("command", oldData);
                model.addAttribute("msg", "You have old data modify it");
                return new ModelAndView(FOLDER + "/data");
            } else {
                dataService.update(data);
                activityDesc = "User has updated mood data for";
            }
        }

        User user = userManager.getUserByUsername(principal.getName());

        Activity activity = new Activity();
        activity.setUser(user);
        activity.setDescription(activityDesc);
        activity.setActivityDate(data.getDate());
        activityService.addActivity(activity);

        return new ModelAndView(new RedirectView("/mood-tracking", true));
    }

}