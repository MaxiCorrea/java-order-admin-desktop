package model.admins;

import java.util.ArrayList;
import java.util.List;

import entities.PaymentMethod;
import model.interfaces.PaymentMethodAdminInterface;
import persistence.daos.PaymentMethodDAO;


public class PaymentMethodAdmin implements PaymentMethodAdminInterface {

  private PaymentMethodDAO paymentMethodDAO;
  
  public PaymentMethodAdmin() {
    paymentMethodDAO = new PaymentMethodDAO();
  }
  
  @Override
  public List<PaymentMethod> readAllPaymentMethod() {
    try {
      return paymentMethodDAO.readAll();
    } catch(Exception ex) {
      ex.printStackTrace();
      return new ArrayList<PaymentMethod>();
    }
  }

}
