package org.socraticgrid.hl7.services.orders.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.socraticgrid.hl7.services.orders.interfaces.OrderCatalogQueryIFace;
import org.socraticgrid.hl7.services.orders.model.CatelogItem;
import org.socraticgrid.hl7.services.orders.model.CatelogItemTree;
import org.socraticgrid.hl7.services.orders.model.CatelogItemType;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

public class OrderCatelogQueryImpl implements OrderCatalogQueryIFace {

	@Override
	public CatelogItem getItemDetail(Identifier itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Order> OrderModel<T> getItemPrototype(Identifier itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatelogItemTree queryItemTree(Identifier itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatelogItemType> queryItemTypes(String query) {
		// TODO Auto-generated method stub
		List<CatelogItemType> result = new LinkedList<CatelogItemType>();
		return result;
	}

	

}
