package au.moodflip.cardgame.service;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.cardgame.model.CardEvent;
import au.moodflip.cardgame.model.MainPlayHistoryItem;
import au.moodflip.cardgame.model.CardPlayHistoryItem;
import au.moodflip.personalisation.model.User;

@Service(value="cardEventManager")
@Transactional
public class CardEventManagerDbImpl implements CardEventManager {
//	private static final Logger logger = LoggerFactory.getLogger(CardEventManagerDbImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void add(CardEvent cardEvent) {
		sessionFactory.getCurrentSession().save(cardEvent);
	}

	@Override
	public CardEvent update(CardEvent cardEvent) {
		sessionFactory.getCurrentSession().merge(cardEvent);
		return cardEvent;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CardEvent> getAll(User user) {
		String hql = "FROM CardEvent AS c WHERE c.user = :user";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("user", user);
		return new ArrayList<CardEvent>(query.list());
	}

	@Override
	public CardEvent get(long id) {
		return (CardEvent)sessionFactory.getCurrentSession().get(CardEvent.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MainPlayHistoryItem> getMainPlayHistory(User user) {
		String hql = "SELECT a.card.cardId, (select title from Card where cardId = a.card.cardId), sum(a.points), "
			+ "(SELECT count(*) FROM CardEvent AS b WHERE user = :user AND b.complete = false AND b.card.cardId = a.card.cardId "
			+ "GROUP BY b.card.cardId) AS attempts, (SELECT count(*) FROM CardEvent AS c WHERE user = :user AND c.complete = true "
			+ "AND c.card.cardId = a.card.cardId GROUP BY c.card.cardId) AS completes FROM CardEvent AS a " 
			+ "WHERE user = :user GROUP BY a.card.cardId ORDER BY a.card.cardId ASC"; 
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("user", user);
		List<MainPlayHistoryItem> res = new ArrayList<MainPlayHistoryItem>();
		List<Object[]> list = query.list();
		for (Object[] l : list){
			String pts = String.valueOf(l[2]).equals("null") ? "0" : String.valueOf(l[2]);
			String attempts = String.valueOf(l[3]).equals("null") ? "0" : String.valueOf(l[3]);
			String completions = String.valueOf(l[4]).equals("null") ? "0" : String.valueOf(l[4]);
			res.add(new MainPlayHistoryItem(String.valueOf(l[0]), String.valueOf(l[1]), pts, attempts, completions));
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CardPlayHistoryItem getCardPlayHistory(User user, long cardId) {
		String hql = "SELECT date, points, complete FROM CardEvent WHERE user = :user AND card.cardId = :card ORDER BY date DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("user", user);
		query.setParameter("card", cardId);
		List<Object[]> list = query.list();
		List<CardPlayHistoryItem> res = new ArrayList<CardPlayHistoryItem>();
		DateFormat df = DateFormat.getDateTimeInstance();
		for (Object[] l : list){
			String pts = String.valueOf(l[1]).equals("null") ? "0" : String.valueOf(l[1]); 
			return new CardPlayHistoryItem(df.format((Date)l[0]), pts, String.valueOf(l[2]));
		}
		return new CardPlayHistoryItem("", "", "");
	}

	@Override
	public List<CardPlayHistoryItem> getCardPlayHistory(User user) {
		List<CardPlayHistoryItem> res = new ArrayList<CardPlayHistoryItem>();
		String hql = "SELECT card.cardId FROM CardEvent WHERE user = :user ORDER BY card.cardId ASC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("user", user);
		List<Long> list = query.list();
		for (Long cardId : list){
			res.add(getCardPlayHistory(user, cardId));
		}
		
		return res;
	} 
}
