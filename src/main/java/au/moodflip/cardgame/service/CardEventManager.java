package au.moodflip.cardgame.service;

import java.util.List;

import au.moodflip.cardgame.model.CardEvent;
import au.moodflip.cardgame.model.CardPlayHistoryItem;
import au.moodflip.cardgame.model.CgUser;
import au.moodflip.cardgame.model.MainPlayHistoryItem;

public interface CardEventManager {
	public void add(CardEvent cardEvent);
	public CardEvent update(CardEvent cardEvent);
	public List<CardEvent> getAll(CgUser user);
	public CardEvent get(long id);
	public List<MainPlayHistoryItem> getMainPlayHistory(CgUser user);
	public CardPlayHistoryItem getCardPlayHistory(CgUser user, long cardId);
	public List<CardPlayHistoryItem> getCardPlayHistory(CgUser user);
}
