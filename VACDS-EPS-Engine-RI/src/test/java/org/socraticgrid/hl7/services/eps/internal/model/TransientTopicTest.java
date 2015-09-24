package org.socraticgrid.hl7.services.eps.internal.model;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.ExpiredException;
import org.socraticgrid.hl7.services.eps.exceptions.FeatureNotAvailableException;
import org.socraticgrid.hl7.services.eps.exceptions.IncompleteDataException;
import org.socraticgrid.hl7.services.eps.exceptions.InvalidDataException;
import org.socraticgrid.hl7.services.eps.exceptions.MediaFormatNotExceptedException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.model.ContractType;
import org.socraticgrid.hl7.services.eps.model.Durability;
import org.socraticgrid.hl7.services.eps.model.Subscription;
import org.socraticgrid.hl7.services.eps.model.SubscriptionType;
import org.socraticgrid.hl7.services.eps.model.Topic;
import org.socraticgrid.hl7.services.eps.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Test-TransientTopicEnv.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)

public class TransientTopicTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	@Qualifier("TestTopicBean")
	@InjectMocks
	private TransientTopic testTopic;

	//@Mock(name = "dlvMgr")
	///DeliveryManager deliveryMgr;
	//@Mock(name = "msgMgr")
	//MessageManager mockMsgMgr;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}



	@Ignore
	@Test
	public void testDeleteEvent() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testHoldEventForApproval() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testInitialize() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetTopicBufferSize() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testSetTopicBufferSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTopic() {

		Topic topic = testTopic.getTopic();
		assertNotNull("Internal Model Topic is Missing",topic);
		
	}

	@Ignore
	@Test
	public void testSetTopic() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetPublicationInterventions() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetValidationSteps() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetSubTopic() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testSetParentTopic() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetParentTopic() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testSetSubTopics() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetSubTopics() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetSubscriptions() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddSubscription() {

		
		Subscription newSub = new Subscription();
		User user = new User();
		user.setName("junit");
		newSub.setSubscriber(user);
		newSub.setType(SubscriptionType.Pull);
		newSub.setContractType(ContractType.Pull);
		newSub.setDurability(Durability.Transient);
		
		try {
			String subscriptionId;
			subscriptionId = testTopic.addSubscription(newSub);
			assertNotNull("Null Subsubscription Id returned",subscriptionId);
			
		} catch ( NotAuthorizedException | AuthenicationRequiredException
				| ExpiredException | FeatureNotAvailableException
				| InvalidDataException | NoSuchTopicException e) {
			fail(e.toString());
		}
	}
	@Ignore
	@Test
	public void testSetPublicationInterventions() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testSetValidationSteps() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testSetSubscriptions() {
		fail("Not yet implemented");
	}

}
