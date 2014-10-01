package au.moodflip.test.cardgame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.Card.Symptom;
import au.moodflip.cardgame.model.CgUser;
import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.cardgame.service.UserCardsManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-persistence-context.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional

public class CgUserTests {
    @Autowired
    private UserCardsManager userCardsManager;
	
	@Test
	public void testGetCgUserId() {
		//create 2 cards
		Set<Card> cards = new HashSet<Card>();
		
		Card card = new Card();
		card.setTitle("Title for new card");
		card.setAttempts(0);
		card.setAvgRating(0);
		card.setCompletions(0);
		card.setIntro("intro");
		card.setLevel(1);
		card.setOutro("outro");
		card.setSymptom(Symptom.APPETITE);
		List<Mission> missions = new ArrayList<Mission>(); 
		missions.add(new Mission("Mission 1"));
		card.setMissions(missions);
		cards.add(card);
		
		card = new Card();
		card.setTitle("Second card title");
		card.setAttempts(1);
		card.setAvgRating(1);
		card.setCompletions(1);
		card.setIntro("intro2");
		card.setLevel(2);
		card.setOutro("outro2");
		card.setSymptom(Symptom.APPETITE);
		missions = new ArrayList<Mission>(); 
		missions.add(new Mission("Mission card 1"));
		missions.add(new Mission("Mission card 2"));
		card.setMissions(missions);
		cards.add(card);
		
		// store a user+cards
		CgUser user = new CgUser();
		user.setCards(cards);

		// get user+cards from db
		userCardsManager.add(user);
		CgUser user1 = userCardsManager.getById(1);
		System.out.println("Retrieving user id: " + user1.getCgUserId());
		Set<Card> uc = user1.getCards();
		for (Iterator iterator = uc.iterator(); iterator.hasNext();) {
			Card card2 = (Card) iterator.next();
			System.out.println("card title: " + card2.getTitle());
			
		}
	}
}
