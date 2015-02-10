package personalisation;

import static org.junit.Assert.*;

import java.util.Date;

import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.model.User.Privacy;
import au.moodflip.personalisation.service.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import junit.framework.TestCase;

//
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:test-persistence-context.xml" })
//@TransactionConfiguration(defaultRollback = false)
//@Transactional

public class UserTest extends TestCase{

	@Autowired
	private UserManager userManager;
	@Autowired
	private User user;
	@Autowired
	private User user1;
	
	public void setUp() throws Exception {
		user = new User();
		

		
	}
	@Test
	public void testUserNameGetterSetter(){
		user.setUsername("james");
		assertEquals("james",user.getUsername());
	}
	@Test
	public void testNameGetterSetter(){
		user.setName("tom");
		assertEquals("tom",user.getName());
	}
	@Test
	public void testSetGetBan(){
		user.setBanned(true);
		assertEquals(true,user.isBanned());
	}
	@Test
	public void testSetGetPassword(){
		user.setPassword("password");
		assertEquals("password",user.getPassword());
	}
	@Test
	public void testSetGetPrivacy(){
		user.setPrivacy(Privacy.OPEN);
		assertEquals(Privacy.OPEN,user.getPrivacy());
	}
	@Test
	public void testSetGetDate(){
		Date date = new Date();
		user.setCreateAt(date);
		assertEquals(date,user.getCreatedAt());
	}
	@Test
	public void testGetSetPoints() {
		user.setPoints(100);
		assertEquals(100,user.getPoints());
	}

	
//	public void testGetById() {
//		user1.setBanned(false);
//		user1.setName("james");
//		user1.setPassword("user1");
//		user1.setCreateAt(new Date());
//		user1.setPrivacy(Privacy.OPEN);
//		userManager.addUser(user);
//		assertNotNull(userManager.getUserByUsername("james"));
//	}

	
	public void testSaveUser() {
		User user1 = new User();
//		user1.setBanned(false);
//		
//		user1.setUsername("qwerty");
//		user1.setName("jake");
//		user1.setPassword("user1");
//		user1.setCreateAt(new Date());
//		user1.setPrivacy(Privacy.OPEN);
//		userManager.addUser(user1);
//		
//		String username = user1.getUsername();
//		String name = user1.getName();
//		boolean banned = user1.isBanned();
//		//assertNotNull(userManager.getUserByUsername("jake"));
//		assertEquals(username, userManager.getUserByUsername("jake").getUsername());
//		assertEquals(name, userManager.getUserByUsername("jake").getName());
//		assertEquals(banned,userManager.getUserByUsername("jake").isBanned());
	}
	
	
//	@Test
//	public void testEditUser() {
//		User user = userManager.getUserById(1L);
//		user.setBanned(false);
//		user.setName("miles");
//		user.setPassword("user1");
//		user.setPrivacy(Privacy.OPEN);
//		userManager.updateUser(user);
//		
//		String username = user.getUsername();
//		String name = user.getName();
//		boolean banned = user.isBanned();
//		
//		
//		assertNotNull(userManager.getUserById(1L));
//		assertEquals(username, userManager.getUserById(1L).getUsername());
//		assertEquals(name,userManager.getUserById(1L).getName());
//		assertEquals(banned,userManager.getUserById(1L).isBanned());
//		
//	
//	}

//	@Test
//	public void testDeleteTopic() {
//		
//		User user = userManager.getUserById(1L);
//		userManager.deleteUser(1L);;
//		assertNull(userManager.getUserById(1L));
//	}
//	
//	@Test
//	public void testTestBanUser() {
//		Topic topic = topicDao.getTopicById(1L);
//		int oldUpVote = topic.getUpVote();
//		topicDao.upVoteTopic(1L);
//		User user = userManager.getUserById(1L);
//		userManager.deleteUser(1L);;
//		assertNull(userManager.getUserById(1L));
//		
//		assertEquals(oldUpVote + 1, topicDao.getTopicById(1L).getUpVote());
//	}
//	
//	@Test
//	public void testDownVoteTopic() {
//		Topic topic = topicDao.getTopicById(1L);
//		int oldDownVote = topic.getDownVote();
//		topicDao.downVoteTopic(1L);
//		
//		assertEquals(oldDownVote + 1, topicDao.getTopicById(1L).getDownVote());
//	}

}
