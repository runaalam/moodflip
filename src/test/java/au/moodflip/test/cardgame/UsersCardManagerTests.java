package au.moodflip.test.cardgame;

import java.util.ArrayList;
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
import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.model.Task;
import au.moodflip.cardgame.model.UsersCard;
import au.moodflip.cardgame.service.CardManager;
import au.moodflip.cardgame.service.UsersCardManager;
//
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-persistence-context.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional

public class UsersCardManagerTests {
    @Autowired
    private CardManager cardManager;
    @Autowired
    private UsersCardManager usersCardManager;
	
	@Test
	public void testGetCgUserId() {
		//create 2 cards
		List<Task> missions = new ArrayList<Task>(); 
		Card card = new Card("Title for new card", 1, Symptom.TIRED, "intro", missions, "outro", 1, 1, 1);
		missions.add(new Mission("c1 Mission 1"));
		long c1id = cardManager.add(card);
		
		missions = new ArrayList<Task>();
		card = new Card("Second card title", 2, Symptom.THINKING, "c2 intro", missions, "c2 outro", 2, 2, 2);
		missions.add(new Mission("c2 Mission card 1"));
		missions.add(new Mission("c2 Mission card 2"));
		long c2id = cardManager.add(card);
		
		// assign cards to userId 1
		usersCardManager.add(new UsersCard(1L, c1id));
		usersCardManager.add(new UsersCard(1L, c2id));

		// get userId 1's cards
		System.out.println("Retrieving user id 1");
		Set<Card> uc = cardManager.getCards(usersCardManager.getAll(1));
		for (Iterator<Card> iterator = uc.iterator(); iterator.hasNext();) {
			System.out.println((Card) iterator.next());
		}
	}
}
