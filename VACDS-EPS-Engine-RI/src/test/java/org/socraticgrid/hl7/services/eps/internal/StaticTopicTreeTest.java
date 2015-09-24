package org.socraticgrid.hl7.services.eps.internal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.socraticgrid.hl7.services.eps.internal.interfaces.TopicIFace;
import org.socraticgrid.hl7.services.eps.internal.model.TransientTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Test-TransientTopicEnv.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class StaticTopicTreeTest {
	@Autowired
	private ApplicationContext applicationContext;

	
	@Autowired
	//@InjectMocks
	private StaticTopicTree staticTopicTree;

	//@Mock(name = "dlvMgr")
	///DeliveryManager deliveryMgr;
	//@Mock(name = "msgMgr")
	//MessageManager mockMsgMgr;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}



	@Test
	public void testLocateTopic() {
		String path ="MissingTopic";
		TopicIFace topic = staticTopicTree.locateTopic(path);
		assertNull("Missing Topic mot returnign null",topic);


	}

	@Test
	public void testGetRootTopic() {
		String path ="/";
		TopicIFace topic = staticTopicTree.locateTopic(path);
		assertNotNull("Root location is returning null",topic);

		TopicIFace root = staticTopicTree.getRootTopic();
		assertNotNull("Root topic is returning null",root);
		
		assertTrue("Topic root fetch does not match getRootTopic",topic==root);
		
	}

	@Test
	public void testSetRootTopic() {
		//fail("Not yet implemented");
		TransientTopic newRoot = new TransientTopic();
		staticTopicTree.setRootTopic(newRoot);
		TopicIFace check = staticTopicTree.getRootTopic();
		assertTrue("GetRootTopic did not return the new root topic",newRoot==check);
		String path ="/";
		check = staticTopicTree.locateTopic(path);
		assertTrue("Locate Root topic did not return the new root topic",newRoot==check);
		
		
	}

}
