package au.moodflip.moodtrack.dao;

import java.util.List;



import au.moodflip.moodtrack.model.Data;

public interface DataDao {
	
	
	
    public void save(Data data);

    public Data update(Data data) ;
    
    public List<Data> listData();



}
