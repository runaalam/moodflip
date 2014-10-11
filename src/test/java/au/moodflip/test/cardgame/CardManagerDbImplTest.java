package au.moodflip.test.cardgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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
import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.model.Card.Symptom;
import au.moodflip.cardgame.service.CardManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-persistence-context.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional

public class CardManagerDbImplTest {
	@Autowired
	private CardManager cardManager;
	
	@Test
	public void testGetCardsBySymptom(){
		System.out.println(org.hibernate.Version.getVersionString());
		// add some cards
		List<Mission> missions = new ArrayList<Mission>(); 
		Card card = new Card("APPETITE card #1", 1, Symptom.APPETITE, "intro", missions, "outro", 1, 1, 1);
		missions.add(new Mission("Appetite card #1 Mission 1", card));
		cardManager.add(card);
		
		missions = new ArrayList<Mission>(); 
		card = new Card("APPETITE card #2", 2, Symptom.APPETITE, "intro2", missions, "outro2", 2, 2, 2);
		missions.add(new Mission("Appetite card #2 Mission 1", card));
		missions.add(new Mission("Appetite card #2 Mission 2", card));
		// link missions together
		for (int i=0; i < missions.size(); i++){
			if (i-1 >= 0){
				missions.get(i).setPrev(missions.get(i-1));
			}
			if (i+1 < missions.size()){
				missions.get(i).setNext(missions.get(i+1));
			}
		}
		cardManager.add(card);

		missions = new ArrayList<Mission>(); 
		card = new Card("SLEEP card #1", 2, Symptom.SLEEP, "intro1", missions, "outro1", 1, 1, 1);
		missions.add(new Mission("SLEEP card #1 Mission 1", card));
		cardManager.add(card);
		
		// get cards by symptom
		Set<Card> appetiteCards = cardManager.getCards(Symptom.APPETITE);
		Set<Card> sleepCards = cardManager.getCards(Symptom.SLEEP);
		Set<Card> guiltCards = cardManager.getCards(Symptom.GUILT);
		
		System.out.println("Appetite cards");
		for (Card c : appetiteCards){
			System.out.println(c);
			List<Mission> m = c.getMissions();
			System.out.println("getting cardid from mission: " + m.get(0).getCard().getCardId());
			if (c.getTitle().equals("APPETITE card #2")){
				System.out.println("Using Mission next & prev props: ");
				for (Mission a : m){
					System.out.println("Mission " + a.getMissionId() + ":");
					System.out.println("\tprev mission: " + a.getPrev());
					System.out.println("\tcur mission: " + a);
					System.out.println("\tnext mission: " + a.getNext());
				}
			}
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

}
