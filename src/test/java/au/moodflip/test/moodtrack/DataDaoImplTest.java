package au.moodflip.test.moodtrack;


import au.moodflip.moodtrack.dao.DataDao;
import au.moodflip.moodtrack.model.Data;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-persistence-context.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class DataDaoImplTest {

    @Autowired
    private UserManager userManager;

    @Autowired
    private DataDao dataDao;

    @Test
    public void testSaveData() throws ParseException {
        Data savedData = saveData();

        User user = userManager.getUserByUsername("user");

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(format.format(new Date()));

        Data data = new Data(user);
        data.setDate(date);

        data = dataDao.findData(data);

        assert data.getDate().equals(savedData.getDate());

    }

    @Test
    public void testUpdateData() throws ParseException {
        testSaveData();

        User user = userManager.getUserByUsername("user");

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(format.format(new Date()));

        Data data = new Data(user);
        data.setDate(date);

        data = dataDao.findData(data);

        data.setMoodRating(5);

        dataDao.update(data);

        data = dataDao.findData(data);

        assert data.getMoodRating() == 5;

    }

    @Test
    public void testListData() throws ParseException {
        testSaveData();

        User user = userManager.getUserByUsername("user");

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(format.format(new Date()));

        List<Data> dataList = dataDao.listData(user, date, date);

        assert dataList.size() == 1;
    }

    private Data saveData() throws ParseException {
        User user = userManager.getUserByUsername("user");

        Data data = new Data(user);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(format.format(new Date()));

        data.setDate(date);
        data.setMoodRating(1);
        data.setCopedWithTask(3);
        data.setHoursOfSleeping(10);
        data.setExerciseHours(20);

        dataDao.save(data);

        return data;
    }

}

