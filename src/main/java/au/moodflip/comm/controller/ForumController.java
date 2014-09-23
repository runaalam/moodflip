package au.moodflip.comm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.comm.model.Forum;
import au.moodflip.comm.service.ForumService;

@Controller
@RequestMapping(value = "/forum")
public class ForumController {

	private final String FOLDER = "communication";

	@Autowired
	private ForumService forumService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAllForums() {
		ModelAndView mav = new ModelAndView(FOLDER + "/listForums");
		List<Forum> forums = forumService.listForum();
		mav.addObject("forums", forums);
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newForumForm() {
		ModelAndView mav = new ModelAndView(FOLDER + "/newForum");
		Forum forum = new Forum();
		mav.getModelMap().put("forum", forum);
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute("forum") @Validated Forum forum,
			BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			//logger
			
            return FOLDER + "/newForum";
        }
		forumService.addForum(forum);
		status.setComplete();
		return "redirect:/forum";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView(FOLDER + "/editForum");
		Forum forum = forumService.getForumById(id);
		mav.addObject("forum", forum);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("forum") @Validated Forum forum,
			BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			//logger

            return FOLDER + "/editForum";
        }
		forumService.editForum(forum);
		status.setComplete();
		return "redirect:/forum";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("redirect:/forum");
		forumService.removeForum(id);
		return mav;
	}
}
