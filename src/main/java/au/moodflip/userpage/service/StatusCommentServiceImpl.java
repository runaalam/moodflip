package au.moodflip.userpage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.userpage.dao.StatusCommentDao;
import au.moodflip.userpage.model.Status;
import au.moodflip.userpage.model.StatusComment;
//
@Service
@Transactional
public class StatusCommentServiceImpl implements StatusCommentService {

	@Autowired
	private StatusCommentDao statusCommentDao;
	
	@Override
	public void addComment(StatusComment statusComment) {
		statusComment.setCommentDate(new Date());
		statusCommentDao.addComment(statusComment);
	}

	@Override
	public List<StatusComment> listStatusComment() {
		ArrayList<StatusComment> list = (ArrayList<StatusComment>) statusCommentDao.listStatusComment();
		Collections.reverse((List<?>) list);
		return list;
	}

	@Override
	public List<StatusComment> listStatusComment(Long statusId) {
		return statusCommentDao.listStatusComment(statusId);
	}

	@Override
	public StatusComment getCommentById(Long id) {
		return statusCommentDao.getCommentById(id);
	}

}
