package au.moodflip.cardgame.service;
//
import java.util.List;

import au.moodflip.cardgame.model.CgUser;

public interface CgUserManager {
	public CgUser add(CgUser user);
	public void update(CgUser user);
	public CgUser getById(long id);
	public List<CgUser> getAll();
	public void delete(long id);
//	public void addCard(CgUser cgUser, Card card);
//	public void deleteCard(CgUser cgUser, long cardId);
}
