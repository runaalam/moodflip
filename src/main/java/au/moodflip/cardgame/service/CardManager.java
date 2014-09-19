package au.moodflip.cardgame.service;

import java.util.List;

import au.moodflip.cardgame.model.Card;

public interface CardManager {
	public void addCard(Card card);
	public List<Card> getCards();
}
