package model.interfaces;

import java.util.List;

import entities.Order;

public interface OrderAdminInterface {

  boolean createOrder(Order order);
  
  boolean editOrder(Order order);
  
  boolean deleteOrder(Order order);
  
  List<Order> readAllOrder();
  
  Order readByID(Long id);
  
}
