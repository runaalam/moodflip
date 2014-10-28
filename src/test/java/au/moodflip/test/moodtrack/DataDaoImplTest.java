package au.moodflip.test.moodtrack;

import java.util.List;

import au.moodflip.personalisation.model.User;
import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.moodtrack.model.Data;
import au.moodflip.moodtrack.dao.DataDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-persistence-context.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional

public class DataDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DataDao dataDao;


    @Test
    public void testSaveData(){
        User user = new User();
        user.setName("Test user");

        sessionFactory.getCurrentSession().save(user);

        Data data = new Data(user);
        data.setMoodRating(1);
        data.setCopedWithTask(3);
        data.setHoursOfSleeping(10);
        data.setExerciseHours(20);

        dataDao.save(data);
    }

@Test
public void testUpdateData(){
    User user = new User();
    user.setName("Test user");

    sessionFactory.getCurrentSession().save(user);

    Data data = new Data(user);
    data.setMoodRating(1);
    data.setCopedWithTask(3);
    data.setHoursOfSleeping(10);
    data.setExerciseHours(20);

    dataDao.save(data);
    
    data.setMoodRating(5);
    dataDao.update(data);
}

}

