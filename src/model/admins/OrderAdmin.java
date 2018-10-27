package model.admins;

import java.util.ArrayList;
import java.util.List;
import entities.Order;
import entities.ProductOrder;
import model.interfaces.OrderAdminInterface;
import persistence.daos.DateDAO;
import persistence.daos.OrderDAO;
import persistence.daos.ProductOrderDAO;

public class OrderAdmin implements OrderAdminInterface {

	private OrderDAO orderDAO;
	private DateDAO dateDAO;
	private ProductOrderDAO productOrderDAO;

	public OrderAdmin() {
		orderDAO = new OrderDAO();
		dateDAO = new DateDAO();
		productOrderDAO = new ProductOrderDAO();
	}

	@Override
	public boolean createOrder(Order order) {
		Integer idFecha = DateDAO.findDate(order.getFecha());
		if (idFecha == null) {
			dateDAO.insert(order.getFecha());
			idFecha = DateDAO.findDate(order.getFecha());
			order.setIdFecha(idFecha);
		} else {
			order.setIdFecha(idFecha);
		}
		if (orderDAO.insert(order)) {
			List<ProductOrder> productos = order.getProductsOrder();
			Long orderId = orderDAO.find(order);
			for (ProductOrder productOrder : productos) {
				productOrder.setOrderId(orderId);
				productOrderDAO.insert(productOrder);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean editOrder(Order order) {
		if (orderDAO.update(order)) {
			List<ProductOrder> productos = order.getProductsOrder();
			for (ProductOrder productOrder : productos) {
				ProductOrder po = ProductOrderDAO.find(productOrder);
				if (po != null) {
					productOrder.setOrderId(order.getId());
					productOrderDAO.update(productOrder);
				} else {
					productOrder.setOrderId(order.getId());
					productOrderDAO.insert(productOrder);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteOrder(Order order) {
		return orderDAO.delete(order);
	}

	@Override
	public List<Order> readAllOrder() {
		try {
			return orderDAO.readAll();
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<Order>();
		}
	}

	@Override
	public Order readByID(Long id) {
		try {
			return OrderDAO.readById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
