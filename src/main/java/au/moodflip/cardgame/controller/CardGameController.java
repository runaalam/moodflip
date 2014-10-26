package au.moodflip.cardgame.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.CardEvent;
import au.moodflip.cardgame.model.CardSurvey;
import au.moodflip.cardgame.model.CardSurvey.Answer;
import au.moodflip.cardgame.model.CgUser;
import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.model.MissionEvent;
import au.moodflip.cardgame.model.Task;
import au.moodflip.cardgame.model.TaskEvent;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.cardgame.service.CardEventManager;
import au.moodflip.cardgame.service.CgUserManager;
import au.moodflip.cardgame.service.PlaylistManager;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;

@Controller
@RequestMapping(value = "/card-game")
@SessionAttributes({"globalPoints"}) // need to fix.  doesn't stay when going to diff controller
public class CardGameController{
	private static final Logger logger = LoggerFactory.getLogger(CardGameController.class);
	private final String FOLDER = "card-game";
	@Autowired
	private CgUserManager cgUserManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private CardManager cardManager;
    @Autowired
    private CardEventManager cardEventManager;
    @Autowired
    private PlaylistManager	playlistManager;
    
    @RequestMapping
	public String home(Locale locale, Principal principal, Model model, HttpSession session) {
		logger.info("Get card game home page - home() ");
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		if (cgUser == null){ // store user in cgUser so we don't have to alter user code 
			cgUser = cgUserManager.add(new CgUser(user.getId()));
			System.out.println("created new cguser");
		}
		model.addAttribute("globalPoints", cgUser.getPoints()); // get global points 
		System.out.println("cgUser is: " + cgUser);
		
		TaskEvent curTaskEvent = cgUser.getCurrentTaskEvent();
		if (curTaskEvent != null){
			Task curTask = curTaskEvent.getTask();
			if (curTask != null){
				if (curTask instanceof Mission){
					model.addAttribute(((Mission)curTask));
					logger.info("Getting mission " + (Mission)curTask);
				}else if (curTask instanceof CardSurvey){
					model.addAttribute(((CardSurvey)curTask));
					model.addAttribute("answers", Answer.values());
					logger.info("Getting cardSurvey " + (CardSurvey)curTask);
				}
			}
		}
		return FOLDER + "/cardGame";
	}
	
    @RequestMapping(method = RequestMethod.POST, params="nextMission")
	public String getNextTask(Principal principal, Model model){
		logger.info("Get next task - getNextTask()");
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		
		cgUser.setAttempts(cgUser.getAttempts() + 1); // set global attempts
		
		TaskEvent curTaskEvent = cgUser.getCurrentTaskEvent();
		CardEvent curCardEvent = curTaskEvent.getCardEvent();
		List<TaskEvent> teList = curCardEvent.getTaskEvents();
		Task nextTask = curTaskEvent.getTask().getNext();
		TaskEvent newTaskEvent = null;
		
		//update current mission activity
		if (curTaskEvent instanceof MissionEvent){
			MissionEvent curMissionEvent = (MissionEvent)curTaskEvent;
			curMissionEvent.setDate(new Date());
			curMissionEvent.setPoints(1);				// update points for mission event
			cgUser.setPoints(cgUser.getPoints() + 1);	// update global points
			curCardEvent.setPoints(curCardEvent.getPoints() + 1);	// update points for card event
			model.addAttribute("globalPoints", cgUser.getPoints());
			logger.info("current mission event after update: " + curMissionEvent);
		}
		if (nextTask != null){
			if (nextTask instanceof Mission){
				model.addAttribute(((Mission)nextTask));

				//create new mission played to act as marker for which mission user is currently playing
				newTaskEvent = new MissionEvent(nextTask, 0, new Date());
			}else if (nextTask instanceof CardSurvey){
				model.addAttribute(((CardSurvey)nextTask));
				model.addAttribute("answers", Answer.values());
				
				newTaskEvent = new TaskEvent(nextTask, new Date());
			}
			newTaskEvent.setCardEvent(curCardEvent);
			teList.add(newTaskEvent);
			curCardEvent.setTaskEvents(teList);
			
			logger.info("Updating cardPlayed: ");
			CardEvent ce = cardEventManager.update(curCardEvent); 
			List<TaskEvent> list = ce.getTaskEvents();
			/** when cardPlayedManager inserts the newMissionPlayed, HSQL adds an index due to @indexcolumn in 
			 *  CardEvent.  This changes the instance, so we need to make sure to add this particular instance 
			 *  to cgUser. If newMissionPlayed is added, it inserts a new row because it doesn't have the extra index info. 
			 */
			cgUser.setCurrentTaskEvent(list.get(list.size()-1)); 
		}
		// debug
		System.out.printf("card:%d title:%s user:%d pts:%d complete:%s tasksPlayed:%d\n", curCardEvent.getCard().getCardId(), curCardEvent.getCard().getTitle(), curCardEvent.getUser().getCgUserId(), curCardEvent.getPoints(), curCardEvent.isComplete(), curCardEvent.getTaskEvents().size());
		for (TaskEvent t : curCardEvent.getTaskEvents()){
			if (t instanceof MissionEvent){
				MissionEvent m = (MissionEvent)t;
				System.out.printf("\tmission:%d pts:%d date:%s\n", m.getTask().getTaskId(), m.getPoints(), m.getDate());
			}else{
				System.out.printf("\ttask:%d date:%s\n", t.getTask().getTaskId(), t.getDate());
			}
		}
		
		logger.info("Updating cgUser: ");
		cgUserManager.update(cgUser);
		return FOLDER + "/cardGame";
	}
	
    @RequestMapping(method = RequestMethod.POST, params="finish")
	public String getCardSurvey(CardSurvey cardSurvey, Principal principal, Model model){
    	logger.info("Enter getCardSurvey()");
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		logger.info("answered: " + cardSurvey.getAnswer());
		rateCard(cgUser.getCurrentTaskEvent().getCardEvent().getCard(), cardSurvey.getAnswer());
		CardEvent curCardEvent = cgUser.getCurrentTaskEvent().getCardEvent();
		curCardEvent.setComplete(true); 
		cgUser.setCompletions(cgUser.getCompletions() + 1); // set global completions
		cgUser.setCurrentTaskEvent(null); // set to no mission for user
		boolean res = playlistManager.deleteItem(curCardEvent.getCard().getCardId(), cgUser.getCgUserId());// remove from playlist
		logger.info("removed card from playlist: " + res);
		cgUserManager.update(cgUser);
		logger.info("Exit getCardSurvey()");
		return "redirect:/" + FOLDER;
	}
	
    @RequestMapping(method = RequestMethod.POST, params="newCard")
	public ModelAndView getNewCard(Principal principal, Model model){
		User user = userManager.getUserByUsername(principal.getName());
		CgUser cgUser = cgUserManager.getById(user.getId());
		cgUser.setCurrentTaskEvent(null); // set to no mission for user
		cgUserManager.update(cgUser);
		return new ModelAndView(FOLDER + "/cardGame", "model", model);
	}
	
	// based on exponential moving avg
	// http://www.bennadel.com/blog/1627-create-a-running-average-without-storing-individual-values.htm
	public void rateCard(Card card, Answer answer){
		double baseAvg = card.getAvgRating();
		long ratingCount = card.getCompletions();
		int newRating = answer.ordinal() + 1; // ordinal is zero based so add 1 to get rating between 1-5
		double rating = (baseAvg * ratingCount + newRating) / (ratingCount + 1); 
		card.setCompletions(card.getCompletions() + 1);
		card.setAvgRating(rating);
		cardManager.update(card);
	}
}
