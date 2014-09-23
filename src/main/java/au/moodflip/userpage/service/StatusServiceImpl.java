package au.moodflip.userpage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.userpage.dao.StatusDao;
import au.moodflip.userpage.model.Status;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDao statusDao;
	
	@Transactional
	public void addStatus(Status status) {
		statusDao.addStatus(status);
	}

	@Transactional
	public List<Status> listStatus() {
		// TODO Auto-generated method stub
		return statusDao.listStatus();
	}

	@Transactional
	public Status getStatusById(Long id) {
		// TODO Auto-generated method stub
		return statusDao.getStatusById(id);
	}
}
