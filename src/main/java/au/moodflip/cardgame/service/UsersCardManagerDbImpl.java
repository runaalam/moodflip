package au.moodflip.cardgame.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.cardgame.model.UsersCard;
import au.moodflip.cardgame.model.UsersCard.UsersCardPK;

@Service(value="usersCardManager")
@Transactional
public class UsersCardManagerDbImpl implements UsersCardManager{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void add(UsersCard usersCard) {
		sessionFactory.getCurrentSession().save(usersCard);
	}

	@Override
	public void update(UsersCard usersCard) {
		sessionFactory.getCurrentSession().merge(usersCard);
	}

	@Override
	public UsersCard getById(UsersCardPK id) {
		return (UsersCard) sessionFactory.getCurrentSession().get(UsersCard.class, id);
	}

	@Override
	public void delete(UsersCardPK id) {
		UsersCard usersCard = getById(id);
		sessionFactory.getCurrentSession().delete(usersCard);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<UsersCard> getAll(long userId) {
		String hql = "FROM UsersCard AS c WHERE c.id.userId = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", userId);
		List<UsersCard> res = query.list();
		if (res.isEmpty()){
			return new TreeSet<UsersCard>();
		}
		return new TreeSet<UsersCard>(query.list());
	}
}
