package au.moodflip.moodtrack.service;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import au.moodflip.moodtrack.dao.DataDao;
import au.moodflip.moodtrack.model.Data;



@Service
public class DataServiceImp implements DataService {
	
	@Autowired
    private DataDao dataDao;
	@Transactional
	public void save(Data data) {
        dataDao.save(data);
    }

    public Data update(Data data) {
        return dataDao.update(data);
    }

    @Transactional
	public List<Data> listData() {
		return dataDao.listData();
	}
    

}
