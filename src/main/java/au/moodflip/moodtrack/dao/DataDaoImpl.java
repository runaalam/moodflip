package au.moodflip.moodtrack.dao;

import java.util.List;

import au.moodflip.moodtrack.model.Charts;
import au.moodflip.moodtrack.model.ReportCmd;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.moodflip.moodtrack.model.Data;

@SuppressWarnings("unchecked")
@Repository
public class DataDaoImpl implements DataDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Data data) {
        sessionFactory.getCurrentSession().save(data);
    }

    @Override
    public Data update(Data data) {
        return (Data) sessionFactory.getCurrentSession().merge(data);
    }

    @Override
    public List<Data> listData(ReportCmd reportCmd) {
        return sessionFactory.getCurrentSession().createQuery("SELECT d FROM Data d" +
                " WHERE d.user = :user" +
                " AND d.date BETWEEN :startDate AND :endDate")
                .setParameter("user", reportCmd.getUser())
                .setParameter("startDate", reportCmd.getStartDate())
                .setParameter("endDate", reportCmd.getEndDate())
                .list();
    }
    
    @Override
    public List<Data> listData(Charts charts) {
        return sessionFactory.getCurrentSession().createQuery("SELECT d FROM Data d" +
                " WHERE d.user = :user"+
                " AND d.date BETWEEN :startDate AND :endDate")
                .setParameter("user", charts.getUser())
                .setParameter("startDate", charts.getStartDate())
                .setParameter("endDate", charts.getEndDate())
                .list();
    }

    
    @Override
    public Data findData(Data data) {
        List<Data> list = sessionFactory.getCurrentSession().createQuery("SELECT d FROM Data d" +
                " WHERE d.user = :user" +
                " AND d.date = :date")
                .setParameter("user", data.getUser())
                .setParameter("date", data.getDate())
                .list();

        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

}
