package au.moodflip.comm.dao;

import java.util.Date;
import java.util.List;

import au.moodflip.comm.model.PrivateMessage;
import au.moodflip.personalisation.model.User;

public interface PrivateMessageDao {

	public PrivateMessage createPrivateMessage(PrivateMessage pMessage);

//	public List<PrivateMessage> listPrivateMessage();
//
//	public List<PrivateMessage> listPrivateMessageBySenderId(Long senderId);
//
//	public List<PrivateMessage> listPrivateMessageByReceiverId(Long receiverId);
//
//	public List<PrivateMessage> listPrivateMessageByUserId(Long userId);

	public PrivateMessage getPrivateMessageById(Long id);

	public void editPrivateMessage(PrivateMessage pMessage);

	public void removePrivateMessage(Long id);

	public List<PrivateMessage> listPrivateMessageBySenderAndReceiverId(
			User sender, User receiver);

	public List<PrivateMessage> updatePrivateMessageBySenderAndReceiverId(
			User sender, User receiver, Date datetime);

}
