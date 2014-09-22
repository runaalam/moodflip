package au.moodflip.comm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.comm.model.Forum;
import au.moodflip.comm.model.Topic;
import au.moodflip.comm.model.TopicComment;
import au.moodflip.comm.service.ForumService;
import au.moodflip.comm.service.TopicCommentService;
import au.moodflip.comm.service.TopicService;

@Controller
@RequestMapping(value = "/forum/{forumId}")
public class TopicController {

	private final String FOLDER = "communication";

	@Autowired
	private TopicService topicService;
	
	@Autowired
	private TopicCommentService topicCommentService;

	@Autowired
	private ForumService forumService;

	private Long forumId;
	private Forum forum;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAllTopics(@PathVariable("forumId") Long forumId) {
		ModelAndView mav = new ModelAndView(FOLDER + "/listTopics");
		this.forumId = forumId;
		forum = forumService.getForumById(forumId);
		List<Topic> topics = topicService.listTopicByForumId(this.forumId);
		mav.addObject("topics", topics);
		mav.addObject("forumId", this.forumId);
		mav.addObject("forumName", forum.getForumName());
		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView(FOLDER + "/showTopic");
		Topic topic = topicService.getTopicById(id);
		mav.addObject("topic", topic);
		mav.addObject("comments", topicCommentService.listCommentByTopicId(id));
		mav.addObject("forumId", this.forumId);
		mav.addObject("forumName", forum.getForumName());
		
		TopicComment comment = new TopicComment();
		mav.getModelMap().put("comment", comment);
		return mav;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String create(@PathVariable("id") Long id,
			@ModelAttribute("comment") TopicComment comment, BindingResult result,
			SessionStatus status) {
		// validator.validate(contact, result);
		// if (result.hasErrors()) {
		// return "newContact";
		// }

		comment.setTopic(topicService.getTopicById(id));
		comment.setCreatedAt(new Date());

		topicCommentService.createComment(comment);
		status.setComplete();
		return "redirect:/forum/{forumId}/{id}";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newForumForm() {
		ModelAndView mav = new ModelAndView(FOLDER + "/newTopic");
		Topic topic = new Topic();
		mav.getModelMap().put("topic", topic);
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@PathVariable("forumId") Long forumId,
			@ModelAttribute("topic") Topic topic, BindingResult result,
			SessionStatus status) {
		// validator.validate(contact, result);
		// if (result.hasErrors()) {
		// return "newContact";
		// }

		// prevent direct HTTP POST with different "forum" object
		topic.setForum(forum);
		topic.setCreatedAt(new Date());

		topicService.createTopic(topic);
		
		status.setComplete();
		return "redirect:/forum/{forumId}";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView(FOLDER + "/editTopic");
		Topic topic = topicService.getTopicById(id);
		mav.addObject("topic", topic);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("forumId") Long forumId,
			@ModelAttribute("topic") Topic topic, BindingResult result,
			SessionStatus status) {
		// validator.validate(contact, result);
		// if (result.hasErrors()) {
		// return "editContact";
		// }

		// prevent direct HTTP POST with different "forum" object
		topic.setForum(forum);
		topic.setEditedAt(new Date());

		topicService.editTopic(topic);
		status.setComplete();
		return "redirect:/forum/{forumId}";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("redirect:/forum/{forumId}");
		topicService.removeTopic(id);
		return mav;
	}

	@RequestMapping(value = "/up_vote/{id}", method = RequestMethod.GET)
	public ModelAndView upVote(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("redirect:/forum/{forumId}/{id}");
		topicService.upVoteTopic(id);
		return mav;
	}

	@RequestMapping(value = "/down_vote/{id}", method = RequestMethod.GET)
	public ModelAndView downVote(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("redirect:/forum/{forumId}/{id}");
		topicService.downVoteTopic(id);
		return mav;
	}

}
