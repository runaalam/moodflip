package au.moodflip.cardgame.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.CgUser;
import au.moodflip.cardgame.model.UsersCard;

@Service(value="userCardsManager")
@Transactional
public class CgUserManagerDbImpl implements CgUserManager{
//	private static final Logger logger = LoggerFactory.getLogger(CgUserManagerDbImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void add(CgUser user){
		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	public List<CgUser> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from CgUser").list();
	}

	public CgUser getById(long id) {
		return (CgUser)sessionFactory.getCurrentSession().get(CgUser.class, id);
	}

	public void update(CgUser user) {
		sessionFactory.getCurrentSession().merge(user);
	}

	public void delete(long id) {
		CgUser user = getById(id);
		sessionFactory.getCurrentSession().delete(user);
	}
	
	public void addCard(CgUser cgUser, Card card){
		Set<UsersCard> cards;
		if (!cgUser.getCards().isEmpty()){ // user has cards
			cards = new TreeSet<UsersCard>(cgUser.getCards());
			cards.add(new UsersCard(card.getCardId()));
		}else{ // user has no cards
			cards = new TreeSet<UsersCard>();
			cards.add(new UsersCard(card.getCardId()));
		}
		cgUser.setCards(cards);
		update(cgUser);
		
	}
}
