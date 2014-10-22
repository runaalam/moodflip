package au.moodflip.cardgame.service;

import java.util.Set;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.Card.Symptom;
import au.moodflip.cardgame.model.UsersCard;
//
public interface CardManager{
	public long add(Card card);
	public void update(Card card);
	public Card getById(long id);
	public void delete(long id);
	public Set<Card> getCards();
	public Set<Card> getCards(Set<UsersCard> ids);
	public Set<Card> getCards(Symptom symptom);
//	public Card getCardById(long id);
//	public void update(T card);
//	public void deleteCard(long id);
}
