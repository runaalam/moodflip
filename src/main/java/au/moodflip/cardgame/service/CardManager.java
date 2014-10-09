package au.moodflip.cardgame.service;

import java.util.Set;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.Card.Symptom;
import au.moodflip.cardgame.model.UsersCard;

public interface CardManager extends GenericManager<Card>{
//	public void add(T card);
	public Set<Card> getCards();
	public Set<Card> getCards(Set<UsersCard> ids);
	public Set<Card> getCards(Symptom symptom);
//	public Card getCardById(long id);
//	public void update(T card);
//	public void deleteCard(long id);
}
