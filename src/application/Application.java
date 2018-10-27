package application;

import javax.swing.SwingUtilities;
import model.admins.CustomerAdmin;
import model.admins.DistrictAdmin;
import model.admins.MeasurementUnitAdmin;
import model.admins.OrderAdmin;
import model.admins.OrderStatusAdmin;
import model.admins.PaymentMethodAdmin;
import model.admins.ProductAdmin;
import model.admins.ProductCategoryAdmin;
import model.admins.ProvinceAdmin;
import model.admins.TimeZoneAdmin;
import persistence.connection.DataBaseConnection;
import presentation.Views;

public class Application {

  public static void main(String[] args) {
    DataBaseConnection.getConexion();

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        Views views = new Views();
        /*views.setCustomerAdmin(new CustomerAdmin());
        views.setOrderAdmin(new OrderAdmin());
        views.setProductAdmin(new ProductAdmin());
        views.setDistrictAdmin(new DistrictAdmin());
        views.setMeasurementUnitAdmin(new MeasurementUnitAdmin());
        views.setProvinceAdmin(new ProvinceAdmin());
        views.setCategoryProductAdmin(new ProductCategoryAdmin());
        views.setPaymentMethod(new PaymentMethodAdmin());
        views.setOrderStatusAdmin(new OrderStatusAdmin());
        views.setTimeZoneAdmin(new TimeZoneAdmin());*/
        views.show();
      }
    });
  }

}
