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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.util.List;

@Controller
@SessionAttributes("command")
@RequestMapping(value="/mood-tracking")
public class ChartsController {

    private static final Logger logger = LoggerFactory.getLogger(ChartsController.class);

    private final String FOLDER = "mood-tracking";

    @Autowired
    private UserManager userManager;

    @Autowired
    private DataService dataService;


    @RequestMapping(value = "/charts", method = RequestMethod.GET)
    public String getCharts(Principal principal,
                            HttpSession session,HttpServletRequest request,
                            ModelMap model) {
        User user = userManager.getUserByUsername(principal.getName());
        boolean drawGraph = ServletRequestUtils.getBooleanParameter(request, "drawGraph", false);
        model.put("drawGraph", drawGraph);

        Object object = session.getAttribute("command");
        if (object == null || !(object instanceof Charts)) {
            model.addAttribute("command", new Charts(user));
        }

        return FOLDER + "/charts";


    }

    @ResponseBody
    @RequestMapping(value = "/charts/drawLineChart", method = RequestMethod.GET)
    public Charts drawLineChart(@ModelAttribute("command") Charts charts) {
    	logger.info("Enter drawLineChart()");
        List<Data> list = dataService.listData(charts);

        Charts prepareChartData = ChartsUtils.prepareChartData(list);
        return prepareChartData;

    }
    
    @ResponseBody
    @RequestMapping(value = "/charts/drawColumnChart", method = RequestMethod.GET)
    public Charts drawColumnChart(@ModelAttribute("command") Charts charts) {
    	logger.info("Enter drawColumnChart()");
        List<Data> list = dataService.listData(charts);

        Charts prepareColumnChartData = ChartsUtils.prepareColumnChartData(list);

        return prepareColumnChartData;

    }
    


    @RequestMapping(value = "/charts", method = RequestMethod.POST)
    public ModelAndView getChartData(@ModelAttribute("command") Charts charts,
                                     BindingResult result,
                                     ModelMap modelMap) {
    	logger.info("Enter getChartData()");
        new ChartsValidator().validate(charts, result);

        if (result.hasErrors()) {
            return new ModelAndView(FOLDER + "/charts");
        }

        //List<Data> data = dataService.listData(charts);

       // modelMap.put("moodRating" , data);
        
        modelMap.put("drawGraph", true);
        logger.info("Exit getChartData()");

        return new ModelAndView(new RedirectView("/mood-tracking/charts", true));
    
    
    }  
}