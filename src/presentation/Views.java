package presentation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import model.interfaces.CustomerAdminInterface;
import model.interfaces.DistrictAdminInterface;
import model.interfaces.MeasurementUnitAdminInterface;
import model.interfaces.OrderAdminInterface;
import model.interfaces.OrderStatusAdminInterface;
import model.interfaces.PaymentMethodAdminInterface;
import model.interfaces.ProductAdminInterface;
import model.interfaces.ProductCategoryAdminInterface;
import model.interfaces.ProvinceAdminInterface;
import model.interfaces.TimeZoneAdminInterface;
import presentation.extras.MoveLabel;
import presentation.utils.Colors;

public class Views {

  private final static String TITLE = "Administracion De Pedidos";
  private final static int WIDTH = 1100;
  private final static int HEIGHT = 600;

  private JFrame mainFrame;
  private JPanel contentPane;
  private JPanel northPane;
  private JPanel centerPane;
  private JPanel southPane;
  private JPanel eastPane;
  private JPanel westPane;

  private OperationsView operationsView;
  private CustomerView customerView;
  private OrderView orderView;
  private ProductView productView;
  private ToolsView toolsView;

  public Views() {
    UIManager.put("ScrollBar.thumbShadow", Colors.BACKGROUND.color());
    UIManager.put("ScrollBar.background", Colors.BACKGROUND.color());
    UIManager.put("ScrollBar.foreground", Colors.BACKGROUND.color());
    UIManager.put("ScrollBar.width", new Integer(13));
    createMainFrame();
    customerView.setOrderView(orderView);
    toolsView.setCustomer(customerView);
    toolsView.setProductView(productView);
    productView.setOrderView(orderView);
  }

  public void setCustomerAdmin(CustomerAdminInterface admin) {
    customerView.setCustomerAdmin(admin);
    orderView.updateCustomers(admin.readAllCustomer());
  }

  public void setProductAdmin(ProductAdminInterface productAdmin) {
    productView.setProductAdmin(productAdmin);
    orderView.updateProducts(productAdmin.readAllProduct());
  }

  public void setProvinceAdmin(ProvinceAdminInterface provinceAdmin) {
    toolsView.setProvinceAdmin(provinceAdmin);
    customerView.updateProvinces(provinceAdmin.readAllProvinces());
  }

  public void setDistrictAdmin(DistrictAdminInterface districtAdmin) {
    toolsView.setDistrictAdmin(districtAdmin);
    customerView.updateDistricts(districtAdmin.readAllDistrict());;
  }

  public void setMeasurementUnitAdmin(MeasurementUnitAdminInterface admin) {
    toolsView.setMeasurementUnitAdmin(admin);
    productView.updateMeasurementUnit(admin.readAllMeasurementUnit());
  }

  public void setCategoryProductAdmin(ProductCategoryAdminInterface admin) {
    toolsView.setCategoryProductAdmin(admin);
    productView.updateProductCategoy(admin.readAllProductCategory());
  }

  public void setOrderAdmin(OrderAdminInterface admin) {
    orderView.setOrderAdminInterface(admin);
  }

  public void setPaymentMethod(PaymentMethodAdminInterface admin) {
    orderView.updatePaymentMethod(admin.readAllPaymentMethod());
  }

  public void setOrderStatusAdmin(OrderStatusAdminInterface admin) {
    orderView.updateOrderStatus(admin.readAllOrderStatus());
  }

  public void setTimeZoneAdmin(TimeZoneAdminInterface admin) {
    orderView.updateTimeZone(admin.readAllTimeZone());
  }

  private void createMainFrame() {
    mainFrame = new JFrame(TITLE);
    mainFrame.setSize(WIDTH, HEIGHT);
    mainFrame.setUndecorated(true);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setContentPane(getContentPane());
    mainFrame.setResizable(false);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.pack();
  }

  private JPanel getContentPane() {
    contentPane = new JPanel();
    contentPane.setLayout(new BorderLayout(0, 0));
    contentPane.setBackground(Colors.BACKGROUND.color());
    contentPane.add(createNorthPane(), "North");
    contentPane.add(createCenterPane(), "Center");
    contentPane.add(createSouthPane(), "South");
    contentPane.add(createEastPane(), "East");
    contentPane.add(createWestPane(), "West");
    return contentPane;
  }

  private JPanel createNorthPane() {
    northPane = new JPanel(new BorderLayout(0, 0));
    northPane.setBackground(Colors.PANEL_COLOR.color());
    northPane.add(new MoveLabel(mainFrame));
    return northPane;
  }

  private JPanel createCenterPane() {
    centerPane = new JPanel(new CardLayout());
    centerPane.add(customerView = new CustomerView(), "0");
    centerPane.add(productView = new ProductView(), "1");
    centerPane.add(orderView = new OrderView(), "2");
    centerPane.add(toolsView = new ToolsView(), "3");
    centerPane.add(new AboutView(), "4");
    return centerPane;
  }

  private JPanel createSouthPane() {
    southPane = new JPanel();
    southPane.setBackground(Colors.PANEL_COLOR.color());
    return southPane;
  }

  private JPanel createEastPane() {
    eastPane = new JPanel();
    eastPane.setBackground(Colors.PANEL_COLOR.color());
    return eastPane;
  }

  private JPanel createWestPane() {
    westPane = new JPanel();
    westPane.setBackground(Colors.PANEL_COLOR.color());
    operationsView = new OperationsView();
    operationsView.setViews(centerPane);
    westPane.add(operationsView);
    return westPane;
  }

  public void show() {
    mainFrame.setVisible(true);
    customerView.initializeCustomers();
    productView.initializeProducts();
  }

  public void close() {
    mainFrame.setVisible(false);
    mainFrame.dispose();
    System.exit(0);
  }

}
