package au.moodflip.moodtrack.dao;

import java.util.List;

import au.moodflip.moodtrack.model.Data;
import au.moodflip.moodtrack.model.ReportCmd;

public interface DataDao {

    public void save(Data data);

    public Data update(Data data) ;
    
    public List<Data> listData(ReportCmd reportCmd);


    Data findData(Data data);
}
