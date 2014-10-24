package au.moodflip.moodtrack.controller;

import au.moodflip.moodtrack.model.Charts;
import au.moodflip.moodtrack.model.Data;
import au.moodflip.moodtrack.service.DataService;
import au.moodflip.moodtrack.utils.ChartsUtils;
import au.moodflip.moodtrack.validator.ChartsValidator;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
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
    public String getCharts(Principal principal,
                            HttpSession session,
                            ModelMap model) {
        User user = userManager.getUserByUsername(principal.getName());

        Object object = session.getAttribute("command");
        if (object == null || !(object instanceof Charts)) {
            model.addAttribute("command", new Charts(user));
        }

        return FOLDER + "/charts";


    }

    @ResponseBody
    @RequestMapping(value = "/drawLineChart", method = RequestMethod.GET)
    public Charts drawLineChart(@ModelAttribute("command") Charts charts) {

        List<Data> list = dataService.listData(charts);

        Charts prepareChartData = ChartsUtils.prepareChartData(list);

        return prepareChartData;

    }
    
    

    @RequestMapping(value = "/charts", method = RequestMethod.POST)
    public ModelAndView getChartData(@ModelAttribute("command") Charts charts,
                                     BindingResult result,
                                     ModelMap modelMap) {

        new ChartsValidator().validate(charts, result);

        if (result.hasErrors()) {
            return new ModelAndView(FOLDER + "/charts");
        }

        List<Data> data = dataService.listData(charts);

        modelMap.put("moodRating", data);

        return new ModelAndView(new RedirectView("/charts", true));
    }


}