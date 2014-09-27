package au.moodflip.cardgame.service;

import java.util.List;

import au.moodflip.cardgame.model.Card;

public interface CardManager {
	public void addCard(Card card);
	public List<Card> getCards();
	public Card getCardById(long id);
	public void updateCard(Card card);
	public void deleteCard(long id);
	public void test();
}
