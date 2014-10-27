package au.moodflip.test.comm;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-persistence-context.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class BaseTest extends TestCase {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected static boolean setupDone;

	@Before
	public void setUp() throws Exception {
//		TransactionSynchronizationManager.bindResource(sessionFactory,
//				new SessionHolder(sessionFactory.openSession()));
	}
	
	protected void setSetupDone() {
		setupDone = true;
	}

	@After
	public void tearDown() throws Exception {
//		SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager
//				.unbindResource(sessionFactory);
//		SessionFactoryUtils.closeSession(sessionHolder.getSession());
	}

}
