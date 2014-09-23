package au.moodflip.personalisation.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.comm.model.Forum;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;


@Controller
@SessionAttributes(value = {"user"})
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserManager userManager;

	@RequestMapping(value="/register",method = RequestMethod.GET)
	public ModelAndView register() {
		User user = new User();
		return new ModelAndView("personalisation/register","user",user);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(HttpServletRequest httpServletRequest) {

		User user = new User();
		user.setName(httpServletRequest.getParameter("name"));
		user.setBanned(false);
		user.setUsername(httpServletRequest.getParameter("username"));
		user.setPassword(httpServletRequest.getParameter("password"));
		user.setPrivacySetting(httpServletRequest.getParameter("privacySetting"));
		user.setCreateAt(new Date());
		
		userManager.addUser(user);
		
		return "redirect:/personalisation.htm";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editUser(@PathVariable("id") Long id) {

		ModelAndView mav = new ModelAndView("personalisation/editUser");
		User user = userManager.getUserById(id);
		mav.addObject("user", user);
		
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public String editUser(@ModelAttribute("user") User user,BindingResult result) {
		
		userManager.updateUser(user);
		System.out.println(user.getId());
		
		return "redirect:/personalisation.htm";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable("id") Long id) {
		
		userManager.deleteUser(id);

		return new ModelAndView("redirect:/personalisation.htm");
	}
}
