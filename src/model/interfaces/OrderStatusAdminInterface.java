package model.interfaces;

import java.util.List;

import entities.OrderStatus;

public interface OrderStatusAdminInterface {

	public boolean editStatus(OrderStatus estado);
	public OrderStatus readById(OrderStatus estado);
	List<OrderStatus> readAllOrderStatus();
	
}
