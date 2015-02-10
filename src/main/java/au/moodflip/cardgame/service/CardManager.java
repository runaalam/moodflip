package au.moodflip.cardgame.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.Card.Symptom;
import au.moodflip.cardgame.model.Playlist;
import au.moodflip.cardgame.model.UsersCard;

public interface CardManager{
	public long add(Card card);
	public Card update(Card card);
	public Card getById(long id);
	public void delete(long id);
	public Set<Card> getCards();
	public Set<Card> getCards(Set<UsersCard> ids);
	public Set<Card> getCards(Symptom symptom);
	public List<Card> getCards(Playlist playlist);
	public List<Card> recommendCards(Map<Symptom, Double> symptoms, long userId);
	public List<Card> randomCards(long userId);
}
