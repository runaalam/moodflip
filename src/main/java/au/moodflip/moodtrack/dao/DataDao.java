package au.moodflip.moodtrack.dao;

import java.util.Date;
import java.util.List;


import au.moodflip.moodtrack.model.Charts;
import au.moodflip.moodtrack.model.Data;
import au.moodflip.moodtrack.model.ReportCmd;
import au.moodflip.personalisation.model.User;

public interface DataDao {

    public void save(Data data);

    public Data update(Data data) ;
    
    public List<Data> listData(ReportCmd reportCmd);

    public List<Data> listData(Charts charts);

    public List<Data> listData(User user, Date startDate, Date endDate);
    
    public Data findData(Data data);
    
}
