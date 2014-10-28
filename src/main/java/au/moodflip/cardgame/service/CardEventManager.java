package au.moodflip.cardgame.service;

import java.util.List;

import au.moodflip.cardgame.model.CardEvent;
import au.moodflip.cardgame.model.CardPlayHistoryItem;
import au.moodflip.cardgame.model.MainPlayHistoryItem;
import au.moodflip.personalisation.model.User;

public interface CardEventManager {
	public void add(CardEvent cardEvent);
	public CardEvent update(CardEvent cardEvent);
	public List<CardEvent> getAll(User user);
	public CardEvent get(long id);
	public List<MainPlayHistoryItem> getMainPlayHistory(User user);
	public CardPlayHistoryItem getCardPlayHistory(User user, long cardId);
	public List<CardPlayHistoryItem> getCardPlayHistory(User user);
}
