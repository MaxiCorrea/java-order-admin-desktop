package model.admins;


import java.util.List;
import entities.OrderStatus;
import model.interfaces.OrderStatusAdminInterface;
import persistence.daos.OrderStatusDAO;

public class OrderStatusAdmin implements OrderStatusAdminInterface {

	private OrderStatusDAO dao;
	
	public OrderStatusAdmin() {
		dao = new OrderStatusDAO();
	}
	
	@Override
	public List<OrderStatus> readAllOrderStatus() {
		return dao.readAll();
	}

	@Override
	public boolean editStatus(OrderStatus estado) {
		return dao.update(estado);
	}

	@Override
	public OrderStatus readById(OrderStatus estado) {
		return OrderStatusDAO.readById(estado.getId());
	}

}
