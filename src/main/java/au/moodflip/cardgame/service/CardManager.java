package au.moodflip.cardgame.service;

import java.util.List;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.Card.Symptom;

public interface CardManager extends GenericManager<Card>{
//	public void add(T card);
	public List<Card> getCards();
	public List<Card> getCards(Symptom symptom);
//	public Card getCardById(long id);
//	public void update(T card);
//	public void deleteCard(long id);
}
