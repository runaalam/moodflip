package au.moodflip.moodtrack.controller;



import au.moodflip.moodtrack.service.DataService;
import au.moodflip.moodtrack.model.Data;

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
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private DataService dataService;
    private final String FOLDER = "mood-tracking";

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String data(Model model) {

        model.addAttribute("command", new Data());

        return FOLDER + "/data";
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public String saveData(@ModelAttribute("command") Data data) {
        dataService.save(data);

        return FOLDER + "/data";
    }

    @ModelAttribute("dropDownValues")
    public List<Integer> getDropDownValues() {
        List<Integer> values = new ArrayList<Integer>();

        for (int i = 0; i < 6; i++) {
            values.add(i);
        }

        return values;
    }
}
