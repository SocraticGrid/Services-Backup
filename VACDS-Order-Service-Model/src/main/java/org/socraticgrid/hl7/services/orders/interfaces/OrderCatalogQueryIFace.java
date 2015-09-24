package org.socraticgrid.hl7.services.orders.interfaces;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.orders.model.CatelogItem;
import org.socraticgrid.hl7.services.orders.model.CatelogItemTree;
import org.socraticgrid.hl7.services.orders.model.CatelogItemType;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

/**
 * Interface to query and navigate the order catalog.
 * 
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
@WebService(name = "ordercatalogquery", targetNamespace = "org.socraticgrid.hl7.services.orders")
public interface OrderCatalogQueryIFace {

	public CatelogItem getItemDetail(
			@WebParam(name = "itemId") Identifier itemId);

	public <T extends Order> OrderModel<T> getItemPrototype(
			@WebParam(name = "itemId") Identifier itemId);

	public List<CatelogItemType> queryItemTypes(
			@WebParam(name = "query") String query);

	public CatelogItemTree queryItemTree(
			@WebParam(name = "itemId") Identifier itemId);

}