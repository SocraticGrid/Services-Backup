package org.socraticgrid.hl7.services.orders;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.orders.interfaces.OrderCatalogQueryIFace;
import org.socraticgrid.hl7.services.orders.model.CatelogItem;
import org.socraticgrid.hl7.services.orders.model.CatelogItemTree;
import org.socraticgrid.hl7.services.orders.model.CatelogItemType;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@WebService(name = "ordercatalogquery", targetNamespace = "org.socraticgrid.hl7.services.orders")
public class OrderCatalogQueryService implements OrderCatalogQueryIFace {

	private final Logger logger = LoggerFactory
			.getLogger(OrderCatalogQueryService.class);

	@Autowired
	@Qualifier("OrderCatalogQueryServiceImpl")
	OrderCatalogQueryIFace service;

	@Override
	public CatelogItem getItemDetail(@WebParam(name = "item") Identifier item) {
		return service.getItemDetail(item);
	}

	@Override
	public <T extends Order> OrderModel<T> getItemPrototype(
			@WebParam(name = "item") Identifier item) {
		return service.getItemPrototype(item);
	}

	@Override
	public CatelogItemTree queryItemTree(
			@WebParam(name = "item") Identifier item) {
		return service.queryItemTree(item);
	}

	@Override
	public List<CatelogItemType> queryItemTypes(
			@WebParam(name = "query") String query) {
		return service.queryItemTypes(query);
	}

}
