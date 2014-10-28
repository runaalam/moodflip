package au.moodflip.personalisation.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.security.Principal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.cardgame.model.Card;
import au.moodflip.comm.model.Forum;
import au.moodflip.comm.model.TopicComment;
import au.moodflip.personalisation.model.Role;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.model.User.Privacy;
import au.moodflip.personalisation.service.FriendManager;
import au.moodflip.personalisation.service.RoleManager;
import au.moodflip.personalisation.service.UserManager;


@Controller
@SessionAttributes(value = {"user"})
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private RoleManager roleService;
	@Autowired
	private FriendManager friendManager;

	
	@RequestMapping(value="/register",method = RequestMethod.GET)
	public ModelAndView register() {
		User user = new User();
		return new ModelAndView("personalisation/register","user",user);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest httpServletRequest) {
		
		ModelAndView mav = new ModelAndView();
		
		if (userManager.getUserByUsername(httpServletRequest.getParameter("username")) != null) {
			String error = "Username '" + httpServletRequest.getParameter("username") + "' has already been taken.";
			mav.addObject("error", error);
			mav.setViewName("personalisation/register");
			return mav;
		}
		
		if(!httpServletRequest.getParameter("password").equals(httpServletRequest.getParameter("password2"))) {
			String error = "Password confirmation does not match Password.";
			mav.addObject("error", error);
			mav.setViewName("personalisation/register");
			return mav;
		}

		User user = new User();
		user.setName(httpServletRequest.getParameter("name"));
		user.setBanned(false);
		user.setUsername(httpServletRequest.getParameter("username"));
		user.setPassword(httpServletRequest.getParameter("password"));
		user.setPrivacy(Privacy.OPEN);

//		Set<Role> roles = new HashSet<Role>();
//		roles.add(roleService.findByName("ROLE_USER"));
//		user.setRoles(roles);
		userManager.addUser(user);
		
		mav.setViewName("redirect:/login?registerSuccess");
		return mav;
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public ModelAndView profile(Principal principal) {
		
		ModelAndView mav = new ModelAndView("personalisation/UserSetting");
		User user = userManager.getUserByUsername(principal.getName());
		List<User> friends = new ArrayList<User>();
		friends = friendManager.getFriends(user);
		//mav.addObject("friends",friends);
		mav.addObject("privacy", User.Privacy.values());
		mav.addObject("user", user);
	    return mav;
	}
	
	
	
	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession()
				.getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username or password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username or password!";
		}

		return error;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editUser(Model model,@PathVariable("id") Long id) {
		User user = userManager.getUserById(id);
		model.addAttribute(user);

		ModelAndView mav = new ModelAndView("personalisation/editUser","model",model);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public String editUser(@ModelAttribute("user") User user,BindingResult result) {
		userManager.updateUser(user);
		
		return "redirect:/personalisation.htm";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable("id") Long id) {
		
		userManager.deleteUser(id);

		return new ModelAndView("redirect:/logout");
	}
}
