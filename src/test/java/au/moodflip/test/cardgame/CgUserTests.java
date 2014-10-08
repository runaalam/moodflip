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
		
		List<Mission> missions = new ArrayList<Mission>(); 
		missions.add(new Mission("c1 Mission 1"));
		Card card = new Card("Title for new card", 1, Symptom.TIRED, "intro", missions, "outro", 1, 1, 1);
		cards.add(card);
		
		missions = new ArrayList<Mission>();
		missions.add(new Mission("c2 Mission card 1"));
		missions.add(new Mission("c2 Mission card 2"));
		card = new Card("Second card title", 2, Symptom.THINKING, "c2 intro", missions, "c2 outro", 2, 2, 2);
		cards.add(card);
		
		// store a user + their cards
		CgUser user = new CgUser();
		user.setCards(cards);
		userCardsManager.add(user);

		// get user+cards from db
		CgUser user1 = userCardsManager.getById(1);
		System.out.println("Retrieving user id: " + user1.getCgUserId());
		Set<Card> uc = user1.getCards();
		for (Iterator<Card> iterator = uc.iterator(); iterator.hasNext();) {
			System.out.println((Card) iterator.next());
		}
	}
}
