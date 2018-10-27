package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import model.interfaces.DistrictAdminInterface;
import model.interfaces.MeasurementUnitAdminInterface;
import model.interfaces.ProductCategoryAdminInterface;
import model.interfaces.ProvinceAdminInterface;
import presentation.utils.Colors;

public class ToolsView extends JPanel {

  private static final long serialVersionUID = 1L;

  private JPanel centerPane;

  private DistrictView districtView;
  private ProvinceView provinceView;
  private MeasurementUnitView measurementUnitView;
  private ProductCategoryView productCategoryView;

  public ToolsView() {
    setLayout(new BorderLayout());
    add(createCenterPane(), BorderLayout.CENTER);
  }

  private JPanel createCenterPane() {
    centerPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6));
    centerPane.setBackground(Colors.BACKGROUND.color());
    centerPane.setPreferredSize(new Dimension(950, 500));
    centerPane.setBackground(Colors.BACKGROUND.color());
    centerPane.add(provinceView = new ProvinceView());
    centerPane.add(districtView = new DistrictView());
    centerPane.add(measurementUnitView = new MeasurementUnitView());
    centerPane.add(productCategoryView = new ProductCategoryView());
    return centerPane;
  }

  public void setProvinceAdmin(ProvinceAdminInterface admin) {
    provinceView.setProvinceAdminInterface(admin);
    districtView.updateProvinces(admin.readAllProvinces());
  }

  public void setDistrictAdmin(DistrictAdminInterface admin) {
    districtView.setDistrictAdminInterface(admin);
  }

  public void setMeasurementUnitAdmin(MeasurementUnitAdminInterface admin) {
    measurementUnitView.setMeasurementUnitAdmin(admin);
  }

  public void setCategoryProductAdmin(ProductCategoryAdminInterface admin) {
    productCategoryView.setProductCategoryAdminInterface(admin);
  }

  public void setCustomer(CustomerView customerView) {
    districtView.setCustomer(customerView);
    provinceView.setCustomer(customerView);
    provinceView.setDistrictView(districtView);
  }

  public void setProductView(ProductView productView) {
    measurementUnitView.setProductView(productView);
    productCategoryView.setProductView(productView);
  }

}
