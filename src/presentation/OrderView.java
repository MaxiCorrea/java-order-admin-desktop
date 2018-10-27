package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import entities.Customer;
import entities.Order;
import entities.OrderStatus;
import entities.PaymentMethod;
import entities.Product;
import entities.ProductOrder;
import entities.TimeZone;
import model.interfaces.OrderAdminInterface;
import presentation.edits.EditOrderPane;
import presentation.utils.Colors;


public class OrderView extends JPanel {
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JPanel eastPane;
  private JPanel westPane;
  private JTable ordersTable;
  private TableModelOrder modelTableOrder;
  private EditOrderPane edit;
  private List<Order> orders;
  private JTable productsOrderTable;
  private TableModelProductsOrder modelTableProducts;
  private OrderAdminInterface orderAdmin;
  private JComboBox<Customer> customerCombo;
  private JComboBox<Product> productCombo;
  private List<ProductOrder> productsOrder;
  private JTextField quantityField;
  private JTextField totalField;
  private JButton addButton;
  private JButton remButton;

  public OrderView() {
    setLayout(new BorderLayout());
    setBackground(Colors.BACKGROUND.color());
    add(getContentPane(), BorderLayout.CENTER);
    addActionsListenerToEditPane();
    addTableOrderListeners();
    addButtonsListeners();
    clearFields();
    disableFields();
  }

  public void setOrderAdminInterface(OrderAdminInterface orderAdmin) {
    this.orderAdmin = orderAdmin;
  }

  private JPanel getContentPane() {
    contentPane = new JPanel(new BorderLayout());
    contentPane.setPreferredSize(new Dimension(950, 500));
    contentPane.setBackground(Colors.BACKGROUND.color());
    contentPane.add(createWestPane(), BorderLayout.WEST);
    contentPane.add(createEastPane(), BorderLayout.EAST);
    return contentPane;
  }

  private JPanel createWestPane() {
    westPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 1));
    westPane.setBackground(Colors.BACKGROUND.color());
    westPane.setPreferredSize(new Dimension(500, 540));

    JPanel orderPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
    orderPane.setBackground(Colors.BACKGROUND.color());
    orderPane.setPreferredSize(new Dimension(480, 286));
    orderPane.setBorder(BorderFactory.createTitledBorder("Pedido"));

    JLabel space = new JLabel("");
    space.setPreferredSize(new Dimension(300, 2));
    orderPane.add(space);
    orderPane.add(customerCombo = createCustomerCombo());
    customerCombo.setBorder(BorderFactory.createTitledBorder("cliente"));
    orderPane.add(productCombo = createProductCombo());
    productCombo.setBorder(BorderFactory.createTitledBorder("producto"));

    orderPane.add(quantityField = new JTextField(10));
    quantityField.setPreferredSize(new Dimension(100, 45));
    quantityField.setBorder(BorderFactory.createTitledBorder("cantidad"));

    orderPane.add(totalField = new JTextField(10));
    totalField.setPreferredSize(new Dimension(100, 45));
    totalField.setBorder(BorderFactory.createTitledBorder("total"));
    totalField.setEnabled(false);

    JScrollPane scProducts = new JScrollPane(createTableProducts());
    scProducts.setPreferredSize(new Dimension(450, 100));
    scProducts.setBackground(Colors.BACKGROUND.color());
    scProducts.getViewport().setBackground(Colors.BACKGROUND.color());
    scProducts.setBorder(BorderFactory.createTitledBorder("Productos"));
    orderPane.add(scProducts);
    orderPane.add(addButton = createAddButton("Agregar"));
    orderPane.add(remButton = createRemButton("Quitar"));
    westPane.add(orderPane);

    JScrollPane scOrders = new JScrollPane(createTableOrders());
    scOrders.setPreferredSize(new Dimension(480, 200));
    scOrders.setBackground(Colors.BACKGROUND.color());
    scOrders.getViewport().setBackground(Colors.BACKGROUND.color());
    scOrders.setBorder(BorderFactory.createTitledBorder("Pedidos"));
    westPane.add(scOrders);

    return westPane;
  }

  private JComboBox<Customer> createCustomerCombo() {
    customerCombo = new JComboBox<>();
    customerCombo.setBackground(Colors.BACKGROUND.color());
    customerCombo.setPreferredSize(new Dimension(450, 45));
    AutoCompleteDecorator.decorate(customerCombo);
    return customerCombo;
  }

  private JComboBox<Product> createProductCombo() {
    productCombo = new JComboBox<>();
    productCombo.setBackground(Colors.BACKGROUND.color());
    productCombo.setPreferredSize(new Dimension(200, 45));
    AutoCompleteDecorator.decorate(productCombo);
    return productCombo;
  }

  private JTable createTableOrders() {
    modelTableOrder = new TableModelOrder();
    ordersTable = new JTable(modelTableOrder);
    ordersTable.setGridColor(Colors.BACKGROUND.color());
    ordersTable.getTableHeader().setBackground(Color.WHITE);
    Font newFont = ordersTable.getTableHeader().getFont().deriveFont(Font.BOLD);
    ordersTable.getTableHeader().setFont(newFont);
    return ordersTable;
  }

  private JTable createTableProducts() {
    modelTableProducts = new TableModelProductsOrder();
    productsOrderTable = new JTable(modelTableProducts);
    productsOrderTable.setGridColor(Colors.BACKGROUND.color());
    productsOrderTable.getTableHeader().setBackground(Color.WHITE);
    Font newFont = productsOrderTable.getTableHeader().getFont().deriveFont(Font.BOLD);
    productsOrderTable.getTableHeader().setFont(newFont);
    return productsOrderTable;
  }

  private JPanel createEastPane() {
    eastPane = new JPanel(new BorderLayout());
    eastPane.setPreferredSize(new Dimension(450, 400));
    eastPane.setBackground(Colors.BACKGROUND.color());
    edit = new EditOrderPane();
    eastPane.add(edit, BorderLayout.CENTER);
    return eastPane;
  }

  private JButton createAddButton(String text) {
    JButton button = new JButton(text);
    button.setBorderPainted(false);
    button.setForeground(Color.WHITE);
    button.setFocusable(false);
    button.setBackground(Color.BLUE);
    button.setPreferredSize(new Dimension(100, 30));
    return button;
  }

  private JButton createRemButton(String text) {
    JButton button = new JButton(text);
    button.setBorderPainted(false);
    button.setForeground(Color.WHITE);
    button.setFocusable(false);
    button.setBackground(Color.RED);
    button.setPreferredSize(new Dimension(100, 30));
    return button;
  }

  private void addActionsListenerToEditPane() {
    edit.getNewButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        clearFields();
        enableFields();
        edit.hideOneTwoThreeFourButtons();
        edit.showCancelAndAcceptButton("Listo", "Cancelar");
        productsOrder = new ArrayList<>();
        modelTableProducts.fireTableDataChanged();
      }
    });

    edit.getEditButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (edit.dataReady() && edit.checkOrderStatus()) {
          edit.enableFieldsToEdit();
          enableFields();
          edit.hideOneTwoThreeFourButtons();
          edit.showCancelAndAcceptButton("Guardar", "Cancelar");
        }
      }
    });

    edit.getDeleteButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (edit.dataReady()) {
          edit.hideOneTwoThreeFourButtons();
          edit.showCancelAndAcceptButton("Confirmar", "Cancelar");
          productsOrder = new ArrayList<>();
        }
      }
    });

    edit.getToListButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        modelTableOrder.update();
      }
    });

    edit.getCancelButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        edit.hideCancelAndAcceptButton();
        edit.showOneTwoThreeFourButtons();
        clearFields();
        disableFields();
      }
    });

    edit.getAcceptButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String action = edit.getAction();
        switch (action) {
          case "new": {
            if (validateFields()) {
              if (orderAdmin.createOrder(getCurrentOrder())) {
                clearFields();
                disableFields();
                modelTableOrder.update();
                edit.hideCancelAndAcceptButton();
                edit.showOneTwoThreeFourButtons();
              }
            }
            break;
          }
          case "edit": {
            if (validateFields()) {
              if (orderAdmin.editOrder(getCurrentOrder())) {
                clearFields();
                disableFields();
                modelTableOrder.update();
                edit.hideCancelAndAcceptButton();
                edit.showOneTwoThreeFourButtons();
              }
            }
            break;
          }
          case "delete": {
            if (edit.dataReady()) {
              if (orderAdmin.deleteOrder(getCurrentOrder())) {
                clearFields();
                disableFields();
                modelTableOrder.update();
                modelTableProducts.update();
                edit.hideCancelAndAcceptButton();
                edit.showOneTwoThreeFourButtons();
              }
            }
            break;
          }
        }
      }
    });
  }

  private boolean validateFields() {
    return edit.validateFields();
  }

  private Order getCurrentOrder() {
    Order order = edit.getCurrentOrder();
    Customer c = (Customer) customerCombo.getSelectedItem();
    order.setCliente(c);
    order.setDomicilioEntrega(c.getAddress());
    order.setCantidadProducto(productsOrder.size());
    order.setImporteTotal(calculateImporteTotal());
    order.setProductOrder(productsOrder);
    return order;
  }

  private void addTableOrderListeners() {
    ordersTable.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        Order order = orders.get(ordersTable.getSelectedRow());
        edit.setCurrentOrder(order);
        productsOrder = order.getProductsOrder();
        modelTableProducts.fireTableDataChanged();
      }
    });
  }

  private ProductOrder createOrderProduct() {
    Order order = edit.getCurrentOrder();
    Product product = (Product) productCombo.getSelectedItem();
    int quantity = Integer.valueOf(quantityField.getText());
    BigDecimal price = product.getPrecioPorUnidad();
    return new ProductOrder(product, order.getId(), price, quantity);
  }

  private BigDecimal calculateImporteTotal() {
    BigDecimal result = BigDecimal.ZERO;
    for (ProductOrder pd : this.productsOrder) {
      BigDecimal a = pd.getProduct().getPrecioPorUnidad();
      BigDecimal b = new BigDecimal(pd.getQuantity());
      result = result.add(a.multiply(b));
    }
    return result;
  }


  private void disableFields() {
    edit.disableFields();
    customerCombo.setEnabled(false);
    productCombo.setEnabled(false);
    quantityField.setEditable(false);
    totalField.setEditable(false);
    addButton.setEnabled(false);
    remButton.setEnabled(false);
  }

  private void clearFields() {
    edit.clearFields();
    quantityField.setText("");
    totalField.setText("");
  }

  private void enableFields() {
    edit.enableFields();
    customerCombo.setEnabled(true);
    productCombo.setEnabled(true);
    quantityField.setEditable(true);
    totalField.setEditable(true);
    addButton.setEnabled(true);
    remButton.setEnabled(true);
  }

  private class TableModelOrder extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private String[] columnsNames = {"Numero", "Fecha", "Hora", "Cliente", "Pago"};

    public TableModelOrder() {
      orders = new ArrayList<>();
    }

    public void update() {
      List<Order> list = orderAdmin.readAllOrder();
      if (list != null) {
        orders = list;
        fireTableDataChanged();
      }
    }

    @Override
    public int getRowCount() {
      return orders.size();
    }

    @Override
    public String getColumnName(int column) {
      return columnsNames[column];
    }

    @Override
    public int getColumnCount() {
      return columnsNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      if (columnIndex == 0) {
        return orders.get(rowIndex).getId();
      }
      if (columnIndex == 1) {
        return orders.get(rowIndex).getFecha().toString();
      }
      if (columnIndex == 2) {
        return orders.get(rowIndex).getHora().toString();
      }
      if (columnIndex == 3) {
        return orders.get(rowIndex).getCliente().getNombre();
      }
      if (columnIndex == 4) {
        return orders.get(rowIndex).getFormaDePago().getPaymentMethod();
      }
      return null;
    }

  }

  private class TableModelProductsOrder extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private String[] columnsNames = {"Codigo", "Producto", "Cantidad", "Precio", "Total"};

    private TableModelProductsOrder() {
      productsOrder = new ArrayList<>();
    }

    public void update() {
      fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
      return productsOrder.size();
    }

    @Override
    public String getColumnName(int column) {
      return columnsNames[column];
    }

    @Override
    public int getColumnCount() {
      return columnsNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      if (columnIndex == 0) {
        return productsOrder.get(rowIndex).getProduct().getCodigo();
      }
      if (columnIndex == 1) {
        return productsOrder.get(rowIndex).getProduct().getNombre();
      }
      if (columnIndex == 2) {
        return productsOrder.get(rowIndex).getQuantity();
      }
      if (columnIndex == 3) {
        return "$" + productsOrder.get(rowIndex).getPrice();
      }
      if (columnIndex == 4) {
        BigDecimal result = productsOrder.get(rowIndex).getPrice();
        return "$" + result.multiply(new BigDecimal(productsOrder.get(rowIndex).getQuantity()));
      }
      return null;
    }

  }

  private void addButtonsListeners() {
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addOrderProductToList();
        modelTableProducts.fireTableDataChanged();
        edit.setTatalLabel(calculateImporteTotal());
      }
    });
    remButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int index = productsOrderTable.getSelectedRow();
        if (index != -1) {
          productsOrder.remove(index);
          modelTableProducts.fireTableDataChanged();
        }
        edit.setTatalLabel(calculateImporteTotal());
      }
    });
  }

  private void addOrderProductToList() {
    ProductOrder newProductOrder = createOrderProduct();
    Long codigo = newProductOrder.getProduct().getCodigo();
    for (ProductOrder currProductOrder : productsOrder) {
      if (currProductOrder.getProduct().getCodigo().equals(codigo)) {
        int currQuantity = currProductOrder.getQuantity();
        int newQuantity = currQuantity + newProductOrder.getQuantity();
        currProductOrder.setQuantity(newQuantity);
        return;
      }
    }
    productsOrder.add(newProductOrder);
  }

  public void updateCustomers(List<Customer> customers) {
    customerCombo.removeAllItems();
    for (Customer c : customers) {
      customerCombo.addItem(c);
    }
  }

  public void updateProducts(List<Product> products) {
    productCombo.removeAllItems();
    for (Product p : products) {
      productCombo.addItem(p);
    }
  }

  public void updatePaymentMethod(List<PaymentMethod> methods) {
    edit.updatePaymentMethods(methods);
  }

  public void updateOrderStatus(List<OrderStatus> status) {
    edit.updateOrderStatus(status);
  }

  public void updateTimeZone(List<TimeZone> timesZone) {
    edit.updateTimeZoneCombo(timesZone);
  }

}
