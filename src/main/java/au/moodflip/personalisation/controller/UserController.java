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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;


@Controller
@RequestMapping(value="/user/**")
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
	public String editUser(@PathVariable("id") Long id, Model uiModel) {
		
		User user = this.userManager.getUserById(id);
		uiModel.addAttribute("user", user);
		
		return "edit";
	}
	
	@RequestMapping(value="/edit/**", method=RequestMethod.POST)
	public String editUser(@Valid User user) {
		
		this.userManager.updateUser(user);
		System.out.println(user.getId());
		
		return "redirect:/.htm";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable("id") Long id) {
		
		this.userManager.deleteUser(id);
		
		return "redirect:/home.htm";
	}
}
