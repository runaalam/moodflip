package au.moodflip.moodtrack.controller;





import au.moodflip.moodtrack.service.ReportsService;
import au.moodflip.moodtrack.model.Reports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes("command")
public class ReportsController{

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private ReportsService dataService;
    private final String FOLDER = "mood-tracking";

    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public String data(Model model) {

        model.addAttribute("command", new Reports());

        return FOLDER + "/reports";
    }
}