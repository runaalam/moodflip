package au.moodflip.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import au.moodflip.cardgame.model.Task;
import au.moodflip.cardgame.model.Card.Symptom;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.personalisation.model.Role;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.RoleManager;
import au.moodflip.personalisation.service.UserManager;

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

	@PostConstruct
	private void init() {
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

				if (userService.getUserByUsername("admin") == null) {
					User userAdmin = new User();
					userAdmin.setBanned(false);
					userAdmin.setUsername("admin");
					userAdmin.setPassword("admin");
					Set<Role> roles = new HashSet<Role>();
					roles.add(roleService.findByName("ROLE_ADMIN"));
					roles.add(roleService.findByName("ROLE_USER"));
					userAdmin.setRoles(roles);
					userService.addUserWithRoles(userAdmin);
				}

				if (userService.getUserByUsername("user") == null) {
					User userNormal = new User();
					userNormal.setBanned(false);
					userNormal.setUsername("user");
					userNormal.setPassword("user");
					Set<Role> roles = new HashSet<Role>();
					roles.add(roleService.findByName("ROLE_USER"));
					userNormal.setRoles(roles);
					userService.addUserWithRoles(userNormal);
				}
			}
		});
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
//			    			System.out.println("====================Found intro========================");
		    			intro = buf.toString();
//			    			System.out.println(intro);
		    			buf.setLength(0);
		    			continue;
		    		}else if (s.startsWith("123outro123")){
//			    			System.out.println("===================Found outro=====================");
		    			String outro = buf.toString();
//			    			System.out.println(outro);
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

						logger.info("intro len: " + intro.length());
						logger.info("outro len: " + outro.length());
						logger.info("title len: " + title.length());
						logger.info("level : " + level);
						logger.info("symptom : " + symptom.getText());
						for (Task t : missions) {
							if (t instanceof Mission) {
								Mission m = (Mission) t;
								logger.info("mission : " + m.getText().length());
							}
						}
						
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
//			    			System.out.println("===================Found mission=====================");
		    			String mission = buf.toString();
//			    			System.out.println(mission);
		    			missions.add(new Mission(mission));
		    			buf.setLength(0);
		    			continue;
		    		}else if (s.startsWith("123title123")){
//			    			System.out.println("===================Found title=====================");
		    			title = buf.toString().trim();
//			    			System.out.println(title);
		    			buf.setLength(0);
		    			continue;
		    		}else if (s.startsWith("123level123")){
//			    			System.out.println("===================Found lvl=====================");
		    			level = Integer.parseInt(buf.toString().trim());
//			    			System.out.println("[" + level + "]");
		    			buf.setLength(0);
		    			continue;
		    		}else if (s.startsWith("123symptom123")){
//			    			System.out.println("===================Found symptom=====================");
		    			symptom = Symptom.values()[Integer.parseInt(buf.toString().trim())];
//			    			System.out.println(symptom);
		    			buf.setLength(0);
		    			continue;
		    		}
//			            System.out.println(s);
		            buf.append(s);
		            buf.append(System.getProperty("line.separator"));
			    }
			    sc2.close();
			    logger.info("FINISHED IMPORTING CARD DATA");
			}
		});
		tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                initializeData();
            }
        });
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
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999941,1,2,4,10,6,7,'2014-09-15',8,9,1,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999951,0,0,0,0,0,7,'2014-09-16',0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999961,0,0,0,0,0,7,'2014-09-17',0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999971,0,0,0,0,0,7,'2014-09-18',0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,2)").executeUpdate();
	        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO DATA VALUES(99999981,1,0,6,2,0,7,'2014-09-19',1,8,9,1,2,4,5,6,7,8,1,8,5,1,2,3,5,9,2)").executeUpdate();

	}
}
