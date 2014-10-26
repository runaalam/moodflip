package au.moodflip.comm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.comm.dao.PrivateMessageDao;
import au.moodflip.comm.model.PrivateMessage;
import au.moodflip.personalisation.model.User;

@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {

	@Autowired
    private PrivateMessageDao privateMessageDao;
	
	@Transactional
	public PrivateMessage createPrivateMessage(PrivateMessage pMessage) {
		return privateMessageDao.createPrivateMessage(pMessage);
	}
	
	@Transactional
	public PrivateMessage createPrivateMessage(String message, User sender, User receiver) {
		PrivateMessage pm = new PrivateMessage();
		pm.setContent(message);
		pm.setSender(sender);
		pm.setReceiver(receiver);
		pm.setCreatedAt(new Date());
		return createPrivateMessage(pm);
	}
	
	@Transactional
	public List<PrivateMessage> listPrivateMessageBySenderAndReceiverId(User sender, User receiver) {
		return privateMessageDao.listPrivateMessageBySenderAndReceiverId(sender, receiver);
	}
	
	@Transactional
	public List<PrivateMessage> updatePrivateMessageBySenderAndReceiverId(User sender, User receiver, Date datetime) {
		return privateMessageDao.updatePrivateMessageBySenderAndReceiverId(sender, receiver, datetime);
	}

	@Transactional
	public PrivateMessage getPrivateMessageById(Long id) {
		return privateMessageDao.getPrivateMessageById(id);
	}

	@Transactional
	public void editPrivateMessage(PrivateMessage pMessage) {
		privateMessageDao.editPrivateMessage(pMessage);
	}

	@Transactional
	public void removePrivateMessage(Long id) {
		privateMessageDao.removePrivateMessage(id);
	}

}
