package au.moodflip.moodtrack.service;



import java.util.List;







import au.moodflip.moodtrack.model.Charts;
import au.moodflip.moodtrack.model.Data;
import au.moodflip.moodtrack.model.ReportCmd;


public interface DataService {

    Data findData(Data data);

    public void save(Data data);

    public Data update(Data data) ;
    
    public List<Data> listData(ReportCmd reportCmd);
    
    public List<Data> listData(Charts charts);
    }




