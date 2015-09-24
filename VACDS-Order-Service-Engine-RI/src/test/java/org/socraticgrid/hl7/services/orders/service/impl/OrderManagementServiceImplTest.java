package org.socraticgrid.hl7.services.orders.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.socraticgrid.hl7.services.orders.exceptions.OrderingException;
import org.socraticgrid.hl7.services.orders.functional.EventLogger;
import org.socraticgrid.hl7.services.orders.functional.OrderDispatcher;
import org.socraticgrid.hl7.services.orders.interfaces.OrderManagementIFace;
import org.socraticgrid.hl7.services.orders.logging.EventLevel;
import org.socraticgrid.hl7.services.orders.logging.LogEntryType;
import org.socraticgrid.hl7.services.orders.model.ClinicalPractitioner;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.OrderSummary;
import org.socraticgrid.hl7.services.orders.model.Patient;
import org.socraticgrid.hl7.services.orders.model.Result;
import org.socraticgrid.hl7.services.orders.model.Subject;
import org.socraticgrid.hl7.services.orders.model.SubjectModel;
import org.socraticgrid.hl7.services.orders.model.operationresults.CreateOrderResult;
import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.socraticgrid.hl7.services.orders.model.requirements.CollectionRequirement;
import org.socraticgrid.hl7.services.orders.model.types.order.LabOrder;
import org.socraticgrid.hl7.services.orders.model.types.orderdetail.LabOrderDetail;
import org.socraticgrid.hl7.services.orders.model.types.orderitems.LabOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.DispatcherServlet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Test-OrderManagementServiceImplTest.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderManagementServiceImplTest {
	@Autowired
	private ApplicationContext applicationContext;

	
	@Autowired
	@Qualifier("OrderManagementServiceImpl")
	@InjectMocks
	private OrderManagementIFace service;

	// @Mock(name = "dlvMgr") DeliveryManager deliveryMgr;
	@Mock(name = "eventLogger")
	EventLogger mockEventLogger;
	
	@Mock(name = "Dispatcher")
	OrderDispatcher dispatcher;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateLabOrder() {
		LabOrder labOrder = createPrototypeLabOrder();

		OrderModel<LabOrder> order = new OrderModel<LabOrder>(labOrder);
		try {
			CreateOrderResult result = service.createOrder(order);
			assertNotNull(result);
			// Make sure it is calling event logger
			verify(mockEventLogger).logSummaryEvent(
					eq(LogEntryType.User_CreateOrder), eq(EventLevel.info),
					any(String.class), eq("Create Order"), any(String.class));
		} catch (OrderingException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testEmptyQueryReturn() {
		Subject unkSubject = new Patient();

		List<OrderSummary> result = service.queryOrders(unkSubject);
		assertNotNull("Query for orders returned a null when a Empty list is expected",result);
		assertTrue("Query for Orders list has contents for a unknown subject",result.isEmpty());

	}

	@Test
	public void testNonExistentOrder() {
		Identifier unkId = new Identifier();

		OrderModel<? extends Order> result = service.retrieveOrder(unkId);

		assertNull("Expected a null return order when a unknown id is used",
				result);

	}

	@Test
	public void testNonExistentResults() {
		Identifier unkId = new Identifier();

		List<Result> result = service.retrieveResults(unkId);

		assertNotNull(
				"Result list returned as Null when it should be an empty list",
				result);

	}

	public LabOrder createPrototypeLabOrder()
	{
		LabOrder out = new LabOrder();
		
		//Entered By
		Identifier enteredBy = new Identifier();
		enteredBy.setLabel("The Tester");
		enteredBy.setSystem("test");
		enteredBy.setUse("test");
		enteredBy.setValue("tester1");	
		out.setOrderEnteredBy(enteredBy);
		
		//Clinical Ordered By
		ClinicalPractitioner cp = new ClinicalPractitioner();
		cp.setId(enteredBy);
		cp.setName("The Tester");
		out.setOrderedBy(cp);
		
		//Subject
		SubjectModel sm = new SubjectModel();
		Patient pt = new Patient();
		pt.setName("Test Name");
		Identifier ptId = new Identifier();
		ptId.setValue("101010");
		ptId.setLabel("Test patient");
		ptId.setSystem("Test");
		pt.setIdentity(ptId);
		sm.setSubject(pt);
		out.setSubjectdetails(sm);
		
		//Order time.
		out.setOrderTime(new Date());
		
		//Order Details
		LabOrderDetail details = new LabOrderDetail();
		Code labCd = new Code();
		labCd.setCode("20202");
		labCd.setCodeSystem("Test");
		details.setLab(labCd);
		out.setOrderdetails(details);
		//Ordered Items
		LabOrderItem item = new LabOrderItem();
		item.setType(2);
		
		CollectionRequirement req = new CollectionRequirement();
		req.setId("9999");

		out.getRequirements().add(req);
		out.getOrdereditems().add(item);
		
		return out;
	}

	
}
