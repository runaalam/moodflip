package au.moodflip.userpage.dao;

import java.util.List;

import au.moodflip.userpage.model.Status;
//
public interface StatusDao {
	
	public void addStatus(Status status);

	public List<Status> listStatus();
	
	public Status getStatusById(Long id);

}
