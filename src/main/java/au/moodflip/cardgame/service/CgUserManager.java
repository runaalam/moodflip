package au.moodflip.cardgame.service;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.CgUser;

public interface CgUserManager extends GenericManager<CgUser> {
	public void addCard(CgUser cgUser, Card card);
}
