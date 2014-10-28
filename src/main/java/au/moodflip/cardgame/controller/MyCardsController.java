package au.moodflip.cardgame.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.Card.Symptom;
import au.moodflip.cardgame.model.CardEvent;
import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.model.MissionEvent;
import au.moodflip.cardgame.model.PlaylistItem;
import au.moodflip.cardgame.model.TaskEvent;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.cardgame.service.CardEventManager;
import au.moodflip.cardgame.service.PlaylistManager;
import au.moodflip.cardgame.service.UsersCardManager;
import au.moodflip.moodtrack.model.Data;
import au.moodflip.moodtrack.model.ReportCmd;
import au.moodflip.moodtrack.service.DataService;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
import au.moodflip.userpage.model.Activity;
import au.moodflip.userpage.model.Assessment;
import au.moodflip.userpage.model.ResultDetails;
import au.moodflip.userpage.service.ActivityService;
import au.moodflip.userpage.service.AssessmentService;

@Controller
@RequestMapping(value="/card-game/myCards")
@SessionAttributes("globalPoints")
public class MyCardsController {
	private static final Logger logger = LoggerFactory.getLogger(MyCardsController.class);
	private final String FOLDER = "card-game";
	@Autowired
	private CardManager cardManager;
	@Autowired
	private UsersCardManager usersCardManager;
    @Autowired
    private UserManager userManager;
	@Autowired
	private CardEventManager cardPlayedManager;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private AssessmentService assessmentService;
	@Autowired
	private DataService dataService;
	@Autowired
	private PlaylistManager playlistManager;
	
	@RequestMapping
	public String myCards(Model model, Principal principal){
		User user = userManager.getUserByUsername(principal.getName());
		List<Card> cards = cardManager.getCards(playlistManager.get(user.getId()));
		model.addAttribute("cards", cards);
		return FOLDER + "/myCards";
	}
	
	@RequestMapping(method = RequestMethod.GET, params="recommend")
	public String recommendCards(Principal principal){
		logger.info("Enter recommendCards()");
		User user = userManager.getUserByUsername(principal.getName());
		//get latest assessment
		List<Assessment> assessments = assessmentService.listAssessmentByUserId(user.getId());
		Map<Symptom, Double> symptomRes = new TreeMap<Symptom, Double>();
		Assessment lastAssessment;
		List<Card> recCards = null;
		if (assessments.size() != 0){
			lastAssessment = assessments.get(assessments.size()-1);
			ResultDetails res = lastAssessment.getResultDetails();
			
			String assessment_stats = "app:%s guilt:%s loss_interest:%s move:%s sad:%s sleep:%s suicide:%s thinking:%s fatigue:%s\n";
			System.out.printf(assessment_stats, res.getAppetite(), res.getGuilt(), res.getAnhedonia(), res.getAgitation(), res.getDysphoria(), res.getSleep(), res.getSuicidalIdeation(), res.getConcentration(), res.getFatigue());
			
			symptomRes.put(Symptom.APPETITE, res.getAppetite());
			symptomRes.put(Symptom.GUILT, res.getGuilt());
			symptomRes.put(Symptom.LOSS_OF_INTEREST, res.getAnhedonia());
			symptomRes.put(Symptom.MOVEMENT, res.getAgitation());
			symptomRes.put(Symptom.SADNESS, res.getDysphoria());
			symptomRes.put(Symptom.SLEEP, res.getSleep());
			symptomRes.put(Symptom.SUICIDAL_IDEATION, res.getSuicidalIdeation());
			symptomRes.put(Symptom.THINKING, res.getConcentration());
			symptomRes.put(Symptom.TIRED, res.getFatigue());
		
			recCards = cardManager.recommendCards(symptomRes, user.getId());
			System.out.printf("recommending cards based on assessment... recCardSize:%d", recCards.size());
			for (Card c : recCards){
				System.out.printf("%s %s %d\n", c.getTitle(), c.getSymptom().getText(), c.getCardId());
				playlistManager.appendItem(new PlaylistItem(c.getCardId()), user.getId());
			}
		}
		if (recCards == null){ // didn't get any recommendation from assessment 
			ReportCmd reportCmd = new ReportCmd(user);
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			try {
				int x = -60;
				Calendar cal = GregorianCalendar.getInstance();
				cal.add( Calendar.DAY_OF_YEAR, x); // set calendar to x days ago
				Date start = cal.getTime();
				date = ft.parse("2014-09-19");
				reportCmd.setStartDate(start);
				reportCmd.setEndDate(new Date());
				List<Data> data = dataService.listData(reportCmd);
				if (data.size() > 0){
					Map<Symptom, Double> moodRes = new TreeMap<Symptom, Double>();
					Data lastData = data.get(data.size() - 1);
					
					String mood_stats = "mood stats: alert:%s ashamed:%s distressed:%s excited:%s hrsSleep:%s upset:%s guilty:%s irritable:%s\n";
					System.out.printf(mood_stats, lastData.getAlert(), lastData.getAshamed(), lastData.getDistressed(), lastData.getExcited(), lastData.getHoursOfSleeping(), lastData.getUpset(), lastData.getGuilty(), lastData.getIrritable());			
					/** generate scores for all symptoms
					 * some scores are adjusted inversely to the user response stat.
					 * eg. tired symptom score should increase as user sleeps less hrs, 
					 * so tired symptom = 10 - hrs slept.  This keeps all symptoms in 
					 * line with stats that increase like guilt 
					 */
					if (lastData.getHoursOfSleeping() == 10){
						moodRes.put(Symptom.SLEEP, 10.0);
					}
					if (lastData.getHoursOfSleeping() < 5){
						moodRes.put(Symptom.TIRED, 10.0 - lastData.getHoursOfSleeping()); 
					}
					if (lastData.getAlert() < 5){
						moodRes.put(Symptom.APPETITE, 10.0 - lastData.getAlert());  
					}
					if (lastData.getAshamed() > 5){
						moodRes.put(Symptom.THINKING, (double)(lastData.getAshamed()));  
					}
					if (lastData.getDistressed() > 5){
						moodRes.put(Symptom.SUICIDAL_IDEATION, (double)(lastData.getDistressed()));  
					}
					if (lastData.getExcited() < 5){
						moodRes.put(Symptom.LOSS_OF_INTEREST, 10.0 - lastData.getExcited());  
					}
					if (lastData.getUpset() > 5){
						moodRes.put(Symptom.SADNESS, (double)(lastData.getUpset()));  
					}
					if (lastData.getGuilty() > 5){
						moodRes.put(Symptom.GUILT, (double)(lastData.getGuilty()));  
					}
					if (lastData.getIrritable() > 5){
						moodRes.put(Symptom.MOVEMENT, (double)(lastData.getIrritable()));  
					}
					
					recCards = cardManager.recommendCards(moodRes, user.getId());
					System.out.printf("recommending cards based on moodtracking... recCardSize:%d", recCards.size());
					for (Card c : recCards){
						System.out.printf("%s %s %d\n", c.getTitle(), c.getSymptom().getText(), c.getCardId());
						playlistManager.appendItem(new PlaylistItem(c.getCardId()), user.getId());
					}
				}else{
					System.out.println("No mood data");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (recCards == null){
				System.out.println("no recommendations available");
			}
		}
		logger.info("Exit recommendCards()");
		return "redirect:/" + FOLDER + "/myCards";
	}
	
	@RequestMapping(method = RequestMethod.GET, params="random")
	public String randomCards(Principal principal){
		User user = userManager.getUserByUsername(principal.getName());
		List<Card> randomCards = cardManager.randomCards(user.getId());
		if (randomCards.size() > 0){
			for (Card c : randomCards){
				playlistManager.appendItem(new PlaylistItem(c.getCardId()), user.getId());
			}
		}
		return "redirect:/" + FOLDER + "/myCards";
	}
	
	@RequestMapping(method = RequestMethod.GET, params="play")
	public String playCard(Model model, @RequestParam(value="play", required=false) long cardId, Principal principal){
		User user = userManager.getUserByUsername(principal.getName());
		Card card = cardManager.getById(cardId);
		Mission m = null;
		if (user.getCurrentTaskEvent() == null){ // user not doing task
			if (card.getTasks().get(0) instanceof Mission){
				m = (Mission)card.getTasks().get(0);
				// add new cardPlayed
				List<TaskEvent> teList = new ArrayList<TaskEvent>();
				MissionEvent me = new MissionEvent(m, 0, new Date());
				CardEvent ce = new CardEvent(user, card, new Date(), 0, false);
				me.setCardEvent(ce);
				teList.add(me);
				ce.setTaskEvents(teList);
				cardPlayedManager.add(ce);
				// add current mission to user
				user.setCurrentTaskEvent(me);
				userManager.updateUser(user);
				model.addAttribute(m);
				// add playing card activity to user homepage
				activityService.addActivity(new Activity(user, "started playing card " + card.getTitle(), new Date())) ;
			}
		}else{
			logger.info("Already doing a mission");
		}
		return "redirect:/" + FOLDER;
	}
}
