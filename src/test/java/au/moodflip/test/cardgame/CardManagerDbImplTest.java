package au.moodflip.test.cardgame;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import java.util.TreeSet;

import org.junit.Before;
//
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.CardSurvey;
import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.model.Card.Symptom;
import au.moodflip.cardgame.model.Task;
import au.moodflip.cardgame.model.UsersCard;
import au.moodflip.cardgame.service.CardManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-persistence-context.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional

public class CardManagerDbImplTest {
	@Autowired
	private CardManager cardManager;
	private Card card;
	private Set<Card> cardSet;
	
	@Before
	public void setUp(){
		List<Task> tasks = new ArrayList<Task>();
		card = new Card("card title", 1, Symptom.SADNESS, "intro", tasks, "outro", 1, 2, 3.0);
		Mission m = new Mission("mission");
		m.setCard(card);
		tasks.add(m);
		
		tasks.clear();
		cardSet = new TreeSet<Card>();
		for (int i=0; i < 3; i++){
			card = new Card("card title" + i, 1, Symptom.SADNESS, "intro", tasks, "outro", 1, 2, 3.0);
			m = new Mission("mission" + i);
			m.setCard(card);
			tasks.add(m);
			cardSet.add(card);
		}
	}
	
	@Test
	public void testAddAndGetCard(){
		
		long cardId = cardManager.add(card);
		Card card2 = cardManager.getById(cardId);
		assertEquals(card, card2);
	}
	
	@Test
	public void testCardUpdate(){
		card.setTitle("New card title");
		Card cardUpdated = cardManager.update(card);
		assertEquals("New card title", cardUpdated.getTitle());		
	}
	
	@Test
	public void testDelete(){
		List<Task> tasks = new ArrayList<Task>();
		Card card = new Card("card title", 1, Symptom.SADNESS, "intro", tasks, "outro", 1, 2, 3.0);
		Mission m = new Mission("mission");
		m.setCard(card);
		tasks.add(m);
		long cardAdded = cardManager.add(card);
		cardManager.delete(cardAdded);
		Card cardDeleted = cardManager.getById(cardAdded);
		assertNull(cardDeleted);
	}
	
	@Test
	public void testGetCards(){
		for (Card c : cardSet){
			cardManager.add(c);
		}
		assertEquals(cardSet, cardManager.getCards());
	}
	
//	@Test
	public void testGetCardsBySymptom(){
		// add some cards
		List<Task> missions = new ArrayList<Task>(); 
		Card card = new Card("APPETITE card #1", 1, Symptom.APPETITE, "intro", missions, "outro", 1, 1, 1);
		missions.add(new Mission("Appetite card #1 Mission 1"));
		cardManager.add(card);
		
		missions = new ArrayList<Task>(); 
		card = new Card("APPETITE card #2", 2, Symptom.APPETITE, "intro2", missions, "outro2", 2, 2, 2);
		missions.add(new Mission("Appetite card #2 Mission 1"));
		missions.add(new Mission("Appetite card #2 Mission 2"));
		cardManager.add(card);

		missions = new ArrayList<Task>(); 
		card = new Card("SLEEP card #1", 2, Symptom.SLEEP, "intro1", missions, "outro1", 1, 1, 1);
		missions.add(new Mission("SLEEP card #1 Mission 1"));
		cardManager.add(card);
		
		// get cards by symptom
		Set<Card> appetiteCards = cardManager.getCards(Symptom.APPETITE);
		Set<Card> sleepCards = cardManager.getCards(Symptom.SLEEP);
		Set<Card> guiltCards = cardManager.getCards(Symptom.GUILT);
		
		System.out.println("Appetite cards");
		for (Card c : appetiteCards){
			System.out.println(c);
			List<Task> m = c.getTasks();
			System.out.println("getting cardid from mission 0: " + m.get(0).getCard().getCardId());
		}
		System.out.println("Sleep cards");
		for (Card c : sleepCards){
			System.out.println(c);
		}
		System.out.println("Guilt cards");
		for (Card c : guiltCards){
			System.out.println(c);
		}
	}
	
//	@Test
	public void testTaskIteration(){
		// create a card
		List<Task> missions = new ArrayList<Task>(); 
		Card card = new Card("card 1", 1, Symptom.APPETITE, "intro", missions, "outro", 1, 1, 1);
		missions.add(new Mission("card Mission 1"));
		missions.add(new Mission("Card Mission 2"));
		missions.add(new CardSurvey("The weather is great"));
		long cardId = cardManager.add(card);
		
		Card c = cardManager.getById(cardId);
		for (Task t : c.getTasks()){
			if (t instanceof Mission){
				System.out.println("Mission: ");
			}else if (t instanceof CardSurvey){
				System.out.println("CardSurvey: ");
			}
			System.out.println("\tcur:" + t + "\n\tnext:" + t.getNext());
		}
	}
	
//	@Test
	public void testRandomCards(){
		List<Card> list = cardManager.randomCards(1L);
		if (list.size() > 0){
			for (Card c : list){
				System.out.printf("%s %s\n", c.getTitle(), c.getCardId());
			}
		}
	}
}
