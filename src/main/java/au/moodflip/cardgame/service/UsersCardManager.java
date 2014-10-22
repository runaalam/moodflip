package au.moodflip.cardgame.service;

import java.util.Set;

import au.moodflip.cardgame.model.UsersCard;
import au.moodflip.cardgame.model.UsersCard.UsersCardPK;
//
public interface UsersCardManager{
	public void add(UsersCard usersCard);
	public void update(UsersCard usersCard);
	public UsersCard getById(UsersCardPK id);
	public Set<UsersCard> getAll(long userId);
	public void delete(UsersCardPK id);
}
