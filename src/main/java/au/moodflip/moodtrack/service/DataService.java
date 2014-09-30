package au.moodflip.moodtrack.service;



import java.util.List;



import au.moodflip.moodtrack.model.Data;






public interface DataService {

   

    public void save(Data data);

    public Data update(Data data) ;
    
    public List<Data> listData();
    }




