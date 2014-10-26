package au.moodflip.moodtrack.service;

import java.util.List;





import au.moodflip.moodtrack.model.Charts;
import au.moodflip.moodtrack.model.ReportCmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.moodtrack.dao.DataDao;
import au.moodflip.moodtrack.model.Data;


@Service
@Transactional
public class DataServiceImpl implements DataService {

    @Autowired
    private DataDao dataDao;

    public Data findData(Data data) {
       return dataDao.findData(data);
    }

    public void save(Data data) {
        dataDao.save(data);
    }

    public Data update(Data data) {
        return dataDao.update(data);
    }

    public List<Data> listData(ReportCmd reportCmd) {
        return dataDao.listData(reportCmd);
    }
    
    public List<Data> listData(Charts charts) {
        return dataDao.listData(charts);
    }

}
