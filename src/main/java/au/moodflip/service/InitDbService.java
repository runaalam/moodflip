package au.moodflip.service;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.CardSurvey;
import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.model.Playlist;
import au.moodflip.cardgame.model.PlaylistItem;
import au.moodflip.cardgame.model.Task;
import au.moodflip.cardgame.model.Card.Symptom;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.cardgame.service.PlaylistManager;
import au.moodflip.personalisation.model.Friend;
import au.moodflip.personalisation.model.Role;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.model.User.Privacy;
import au.moodflip.personalisation.service.RoleManager;
import au.moodflip.personalisation.service.UserManager;
import au.moodflip.personalisation.service.FriendManager;
import au.moodflip.userpage.model.Answer;
import au.moodflip.userpage.model.Assessment;
import au.moodflip.userpage.model.Question;
import au.moodflip.userpage.model.Response;
import au.moodflip.userpage.service.AssessmentService;

@Service
@Transactional
public class InitDbService {
	private static final Logger logger = LoggerFactory.getLogger(InitDbService.class);
	@Autowired
	private RoleManager roleService;

	@Autowired
	private UserManager userService;

	@Autowired
	@Qualifier("transactionManager")
	protected PlatformTransactionManager txManager;
	
	@Autowired
	private CardManager cardManager;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private AssessmentService assessmentService;
	
	@Autowired
	private PlaylistManager playlistManager;
	@Autowired
	private FriendManager friendManager;

	@PostConstruct
	private void init() {
		// switches to toggle prepopulating data
		boolean card = true;
		boolean assessment = true;	
		boolean playlist = false;
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				if (roleService.findByName("ROLE_USER") == null) {
					Role roleUser = new Role();
					roleUser.setName("ROLE_USER");
					roleService.createRole(roleUser);
				}

				if (roleService.findByName("ROLE_ADMIN") == null) {
					Role roleAdmin = new Role();
					roleAdmin.setName("ROLE_ADMIN");
					roleService.createRole(roleAdmin);
				}
				User userAdmin = new User();
				if (userService.getUserByUsername("admin") == null) {
					
					userAdmin.setBanned(false);
					userAdmin.setUsername("admin");
					userAdmin.setName("Administrator");
					userAdmin.setPassword("admin");
					userAdmin.setPrivacy(Privacy.OPEN);
					Set<Role> roles = new HashSet<Role>();
					roles.add(roleService.findByName("ROLE_ADMIN"));
					roles.add(roleService.findByName("ROLE_USER"));
					userAdmin.setRoles(roles);
					userService.addUserWithRoles(userAdmin);
				}
				
				User userNormal = new User();
				String iStr = "";
				for (int i=0; i < 1; i++){
					if (i==0) 
						iStr = ""; 
					else
						iStr = String.valueOf(i);
					if (userService.getUserByUsername("user" + iStr) == null) {
			
						userNormal.setBanned(false);
						userNormal.setUsername("user" + iStr);
						userNormal.setPassword("user" + iStr);
						userNormal.setName("Test User" + iStr);
						userNormal.setPrivacy(Privacy.OPEN);
						Set<Role> roles = new HashSet<Role>();
						roles.add(roleService.findByName("ROLE_USER"));
						userNormal.setRoles(roles);
						userService.addUserWithRoles(userNormal);
						
						if (userService.getUserByUsername("admin") == null) {
							Friend friend = new Friend();
							friend.setReceiver(userNormal);
							friend.setSender(userAdmin);
							friend.setFriends(true);
							friendManager.addFriendRequest(friend);
						}
						
						
					}
				}
				
				
			}
		});
		if (card == true){
			tmpl.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					logger.info("BEGIN IMPORTING CARD DATA");
					Scanner sc2 = null;
			    	InputStream is = getClass().getResourceAsStream("/db/card_game_data.txt"); // reads from src/main/resources
			    	if (is == null){
			    		System.out.println("inputstream null");
			    		return; 
			    	}
			    	sc2 = new Scanner(is);
				    StringBuilder buf = new StringBuilder();
				    List<Task> missions = new ArrayList<Task>();
				    String intro = null;
				    String title = null;
				    Integer level = null;
				    Symptom symptom = null;
				    Card card = null;
				    int i = 0;
				    boolean bad = false;
				    while (sc2.hasNextLine()) {
			    		i++;
			    		String s = sc2.nextLine();
			    		if (s.startsWith("123intro123")){
			    			intro = buf.toString();
			    			buf.setLength(0);
			    			continue;
			    		}else if (s.startsWith("123outro123")){
			    			String outro = buf.toString();
			    			if (title.length() == 0){
			    				System.out.println("found empty title around line " + i);
			    				bad = true;
			    			}
			    			if (level == 0){
			    				System.out.println("found empty level around line " + i);
			    				bad = true;
			    			}
			    			if (symptom == null){
			    				System.out.println("found empty symptom around line " + i);
			    				bad = true;
			    			}
			    			if (intro.length() == 0){
			    				System.out.println("found empty intro around line " + i);
			    				bad = true;
			    			}
			    			if (outro.length() == 0){
			    				System.out.println("found empty outro around line " + i);
			    				bad = true;
			    			}
			    			if (missions.size() == 0){
			    				System.out.println("found empty mission around line " + i);
			    				bad = true;
			    			}
			    			if (bad){
			    				sc2.close();
			    				return;
			    			}
	
	//						logger.info("intro len: " + intro.length());
	//						logger.info("outro len: " + outro.length());
	//						logger.info("title len: " + title.length());
	//						logger.info("level : " + level);
	//						logger.info("symptom : " + symptom.getText());
	//						for (Task t : missions) {
	//							if (t instanceof Mission) {
	//								Mission m = (Mission) t;
	//								logger.info("mission : " + m.getText().length());
	//							}
	//						}
							
			    			card = new Card(title, level, symptom, intro, missions, outro, 0, 0, 0);
			    			CardSurvey cardSurvey = new CardSurvey("This task was helpful");
			    			card.getTasks().add(cardSurvey);
			    			cardManager.add(card);
			    			// reset vars for next card
			    			missions = new ArrayList<Task>();
			    			bad = false;
			    			buf.setLength(0);
			    			continue;
			    		}else if (s.startsWith("123mission123")){
			    			String mission = buf.toString();
			    			missions.add(new Mission(mission));
			    			buf.setLength(0);
			    			continue;
			    		}else if (s.startsWith("123title123")){
			    			title = buf.toString().trim();
			    			buf.setLength(0);
			    			continue;
			    		}else if (s.startsWith("123level123")){
			    			level = Integer.parseInt(buf.toString().trim());
			    			buf.setLength(0);
			    			continue;
			    		}else if (s.startsWith("123symptom123")){
			    			symptom = Symptom.values()[Integer.parseInt(buf.toString().trim())];
			    			buf.setLength(0);
			    			continue;
			    		}
			            buf.append(s);
			            buf.append(System.getProperty("line.separator"));
				    }
				    sc2.close();
				    logger.info("FINISHED IMPORTING CARD DATA");
				}
			});
		}
		tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                initializeData();
            }
        });
		if (assessment == true){
			tmpl.execute(new TransactionCallbackWithoutResult() {
				// populate random assessment data
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					logger.info("IMPORTING ASSESSMENT DATA");
					User user = userService.getUserByUsername("user");
					List<Question> quesList = assessmentService.getQuestions();
					Random rand = new Random();
					List<Response> rList;
					Response r = null;
					Assessment assessment;
					SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
					
					// create 12 survey assessments with random data
					for (int i=0; i < 12; i++){
						quesList = assessmentService.getQuestions();
						assessment = new Assessment();
						rList = new ArrayList<Response>();
						for (Question q : quesList){
							r = new Response();
							Answer a = new Answer();
							a.setValue(rand.nextInt(5));	
							r.setAnswer(a);
							r.setQuestion(q);
							r.setUser(user);
							r.setAssessment(assessment);
							rList.add(r);
						}
						assessment.setResponseList(rList);
						assessment.setUser(user);
						try {
							Date t = ft.parse("2014-10-" + String.format("%02d", i+1)); // start at day 1
							assessment.setDate(t);
							assessmentService.save(assessment);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					logger.info("FINISHED IMPORTING ASSESSMENT DATA");
				}
			});
		}
		if (playlist == true){
			tmpl.execute(new TransactionCallbackWithoutResult() {
				// populate playlist for user
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {		
					User user = userService.getUserByUsername("user");
					List<PlaylistItem> playlistItems = new ArrayList<PlaylistItem>();
					Playlist playlist = new Playlist(user.getId(), playlistItems);
					playlistItems.add(new PlaylistItem(1L));
					playlistItems.add(new PlaylistItem(2L));
					playlistItems.add(new PlaylistItem(3L));
					playlistItems.add(new PlaylistItem(4L));
					playlistItems.add(new PlaylistItem(5L));
					for (PlaylistItem item : playlistItems){
						item.setPlaylist(playlist);
					}
					
					playlistManager.add(playlist);
				}
			});
		}
    }
	


		
		private void initializeData() {
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(9999990,1,2,3,4,5,7,'2014-09-01',4,5,6,7,8,8,9,10,3,4,5,6,1,7,0,1,6,7,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(9999991,10,9,10,8,0,7,'2014-09-02',2,3,4,5,6,7,8,10,10,0,1,2,5,3,4,5,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(9999992,0,0,0,0,0,7,'2014-09-03',0,0,0,0,0,0,0,0,0,0,0,0,10,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(9999993,2,3,4,5,6,5,'2014-09-04',10,0,3,4,5,6,7,1,2,3,4,5,3,7,1,2,3,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(9999994,0,0,0,0,0,6,'2014-09-05',1,2,3,4,5,6,7,8,9,10,2,3,6,4,5,1,1,1,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(9999995,4,5,6,7,8,1,'2014-09-06',0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(9999996,0,0,0,0,0,7,'2014-09-07',0,0,0,0,0,0,0,0,0,0,0,0,10,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(9999997,0,0,0,0,0,7,'2014-09-08',0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(9999998,0,0,3,4,6,7,'2014-09-09',3,4,5,0,0,1,10,2,3,4,6,7,5,3,4,7,8,1,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999910,0,0,0,0,0,7,'2014-09-11',0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999911,0,0,0,0,0,7,'2014-09-12',0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999921,0,0,0,0,0,7,'2014-09-13',0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999931,0,0,0,0,0,7,'2014-09-14',0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999941,1,2,4,10,6,7,'2014-09-15',8,9,1,0,0,0,0,0,0,0,0,1,5,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999951,0,0,0,0,0,7,'2014-09-16',0,0,0,0,0,0,0,0,0,0,0,2,5,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999961,0,0,0,0,0,7,'2014-09-17',0,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999971,0,0,0,0,0,7,'2014-09-18',0,0,0,0,0,0,0,0,0,0,0,9,5,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999981,1,0,6,2,0,7,'2014-09-19',1,8,9,1,2,4,5,6,7,8,1,8,5,1,2,3,5,9,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999982,1,0,6,2,0,7,'2014-09-20',1,8,9,1,2,4,5,6,7,8,1,2,5,1,2,3,5,9,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999983,1,0,6,2,0,7,'2014-09-21',1,8,9,1,2,4,5,6,7,8,1,4,5,1,2,3,5,9,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999984,1,0,6,2,0,7,'2014-09-22',1,8,9,1,2,4,5,6,7,8,1,1,5,1,2,3,5,9,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999985,1,0,6,2,0,7,'2014-09-23',1,8,9,1,2,4,5,6,7,8,1,8,5,1,2,3,5,9,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999986,1,0,6,2,0,7,'2014-09-24',1,8,9,1,2,4,5,6,7,8,1,2,5,1,2,3,5,9,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999987,1,0,6,2,0,7,'2014-09-25',1,8,9,1,2,4,5,6,7,8,1,8,5,1,2,3,5,9,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999988,1,0,6,2,0,7,'2014-09-26',1,8,9,1,2,4,5,6,7,8,1,7,5,1,2,3,5,9,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999989,1,0,6,2,0,7,'2014-09-27',1,8,9,1,2,4,5,6,7,8,1,8,5,1,2,3,5,9,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999990,1,0,6,2,0,7,'2014-09-28',1,8,9,1,2,4,5,6,7,8,1,8,5,1,2,3,5,9,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999991,1,0,6,2,0,7,'2014-09-29',1,8,9,1,2,4,5,6,7,8,1,5,5,1,2,3,5,9,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999992,1,0,6,2,0,7,'2014-09-30',1,8,9,0,2,4,5,6,7,8,1,8,5,1,2,3,5,4,2)").executeUpdate();
	}
}
