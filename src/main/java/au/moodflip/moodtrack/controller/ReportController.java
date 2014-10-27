package au.moodflip.moodtrack.controller;

import au.moodflip.moodtrack.model.Data;

import au.moodflip.moodtrack.model.ReportCmd;
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
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    private final String FOLDER = "mood-tracking";

    @Autowired
    private UserManager userManager;

    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/mood-tracking/reports", method = RequestMethod.GET)
    public String getReport(Principal principal,
                            ModelMap model) {
        User user = userManager.getUserByUsername(principal.getName());

        model.addAttribute("command", new ReportCmd(user));

        return FOLDER + "/reports";
    }

    @RequestMapping(value = "/mood-tracking/reports", method = RequestMethod.POST)
    public ModelAndView getReportData(@ModelAttribute("command") ReportCmd reportCmd,
                                      BindingResult result,
                                      ModelMap modelMap) {

        new ReportValidator().validate(reportCmd, result);

        if (result.hasErrors()) {
            return new ModelAndView(FOLDER + "/reports");
        }

        List<Data> data = dataService.listData(reportCmd);

        modelMap.put("dataItems", data);

        return new ModelAndView(FOLDER + "/reports");
    }
}
