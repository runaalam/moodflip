package au.moodflip.comm.dao;

import java.util.Date;
import java.util.List;

import au.moodflip.comm.model.PrivateMessage;
//
public interface PrivateMessageDao {

	public void createPrivateMessage(PrivateMessage pMessage);

	public List<PrivateMessage> listPrivateMessage();

	public List<PrivateMessage> listPrivateMessageBySenderId(Long senderId);

	public List<PrivateMessage> listPrivateMessageByReceiverId(Long receiverId);

	public List<PrivateMessage> listPrivateMessageByUserId(Long userId);

	public PrivateMessage getPrivateMessageById(Long id);

	public void editPrivateMessage(PrivateMessage pMessage);

	public void removePrivateMessage(Long id);

	public List<PrivateMessage> listPrivateMessageBySenderAndReceiverId(
			Long userId_1, Long userId_2);

	public List<PrivateMessage> updatePrivateMessageBySenderAndReceiverId(
			Long userId_1, Long userId_2, Long msgId);

}
