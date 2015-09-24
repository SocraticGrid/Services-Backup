package org.socraticgrid.hl7.services.orders.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.socraticgrid.hl7.services.orders.exceptions.OrderingException;
import org.socraticgrid.hl7.services.orders.functional.EventLogger;
import org.socraticgrid.hl7.services.orders.interfaces.OrderManagementIntf;
import org.socraticgrid.hl7.services.orders.logging.EventLevel;
import org.socraticgrid.hl7.services.orders.logging.LogEntryType;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.OrderSummary;
import org.socraticgrid.hl7.services.orders.model.Patient;
import org.socraticgrid.hl7.services.orders.model.Result;
import org.socraticgrid.hl7.services.orders.model.Subject;
import org.socraticgrid.hl7.services.orders.model.operationresults.CreateOrderResult;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.socraticgrid.hl7.services.orders.model.types.order.LabOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Test-OrderManagementServiceImplTest.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderManagementServiceImplTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	@InjectMocks
	private OrderManagementIntf service;

	// @Mock(name = "dlvMgr") DeliveryManager deliveryMgr;
	@Mock(name = "eventLogger")
	EventLogger mockEventLogger;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateLabOrder() {
		LabOrder labOrder = new LabOrder();
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


	
}
