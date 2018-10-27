package model.interfaces;

import java.util.List;

import entities.PaymentMethod;

public interface PaymentMethodAdminInterface {

  List<PaymentMethod> readAllPaymentMethod();

}
