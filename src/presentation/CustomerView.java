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
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import entities.Customer;
import entities.District;
import entities.Province;
import model.interfaces.CustomerAdminInterface;
import presentation.edits.EditCustomerPane;
import presentation.utils.Colors;

public class CustomerView extends JPanel {
  private static final long serialVersionUID = 1L;

  private JPanel contentPane;
  private JPanel eastPane;
  private JPanel westPane;

  private JTable table;
  private TableModel tableModel;
  private EditCustomerPane edit;

  private CustomerAdminInterface customerAdmin;
  private List<Customer> customers;

  private OrderView orderView;

  public CustomerView() {
    setLayout(new BorderLayout());
    setBackground(Colors.BACKGROUND.color());
    add(getContentPane(), BorderLayout.CENTER);
    addActionsListenerToEditPane();
    addTableListeners();
  }

  public void setOrderView(OrderView orderView) {
    this.orderView = orderView;
  }

  public void setCustomerAdmin(CustomerAdminInterface admin) {
    this.customerAdmin = admin;
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
    westPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
    westPane.setBackground(Colors.BACKGROUND.color());
    westPane.setPreferredSize(new Dimension(500, 540));
    JScrollPane pane = new JScrollPane(createTableAndModel());
    pane.setPreferredSize(new Dimension(480, 485));
    pane.setBackground(Colors.BACKGROUND.color());
    pane.getViewport().setBackground(Colors.BACKGROUND.color());
    pane.setBorder(BorderFactory.createTitledBorder("Clientes"));
    westPane.add(pane);
    return westPane;
  }

  private JTable createTableAndModel() {
    tableModel = new TableModel();
    table = new JTable(tableModel);
    table.setGridColor(Colors.BACKGROUND.color());
    table.getTableHeader().setBackground(Color.WHITE);
    Font newFont = table.getTableHeader().getFont().deriveFont(Font.BOLD);
    table.getTableHeader().setFont(newFont);
    return table;
  }

  private JPanel createEastPane() {
    eastPane = new JPanel(new BorderLayout());
    eastPane.setPreferredSize(new Dimension(450, 400));
    eastPane.setBackground(Colors.BACKGROUND.color());
    edit = new EditCustomerPane();
    eastPane.add(edit, BorderLayout.CENTER);
    return eastPane;
  }

  private void addTableListeners() {
    table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        edit.setCurrentCustomer(customers.get(table.getSelectedRow()));
      }
    });
  }

  private void addActionsListenerToEditPane() {
    edit.getNewButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        edit.clearFields();
        edit.enableFields();
        edit.hideOneTwoThreeFourButtons();
        edit.showCancelAndAcceptButton("Listo", "Cancelar");
      }
    });

    edit.getEditButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (edit.dataReady()) {
          edit.enableFieldsToEdit();
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
        }
      }
    });

    edit.getToListButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        tableModel.update();
      }
    });

    edit.getCancelButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        edit.hideCancelAndAcceptButton();
        edit.showOneTwoThreeFourButtons();
        edit.clearFields();
        edit.disableFields();
      }
    });

    edit.getAcceptButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String action = edit.getAction();
        switch (action) {
          case "new": {
            if (edit.validateFields()) {
              if (customerAdmin.createCustomer(edit.getCurrentCustomer())) {
                edit.clearFields();
                edit.disableFields();
                tableModel.update();
                edit.hideCancelAndAcceptButton();
                edit.showOneTwoThreeFourButtons();
              }
            }
            break;
          }
          case "edit": {
            if (edit.validateFields()) {
              if (customerAdmin.editCustomer(edit.getCurrentCustomer(
                  customers.get(table.getSelectedRow()).getAddress().getId()))) {
                edit.clearFields();
                edit.disableFields();
                tableModel.update();
                edit.hideCancelAndAcceptButton();
                edit.showOneTwoThreeFourButtons();
              }
            }
            break;
          }
          case "delete": {
            if (edit.dataReady()) {
              if (customerAdmin.deleteCustomer(edit.getCurrentCustomer(
                  customers.get(table.getSelectedRow()).getAddress().getId()))) {
                edit.clearFields();
                edit.disableFields();
                tableModel.update();
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

  private class TableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private String[] columnsNames = {"Dni", "Nombre", "Apellido", "Domicilio"};

    public TableModel() {
      customers = new ArrayList<>();
    }

    public void update() {
      List<Customer> list = customerAdmin.readAllCustomer();
      if (list != null) {
        customers = list;
        fireTableDataChanged();
        orderView.updateCustomers(customers);
      }
    }

    @Override
    public int getRowCount() {
      return customers.size();
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
        return customers.get(rowIndex).getDni();
      }
      if (columnIndex == 1) {
        return customers.get(rowIndex).getNombre();
      }
      if (columnIndex == 2) {
        return customers.get(rowIndex).getApellido();
      }
      if (columnIndex == 3) {
        return customers.get(rowIndex).getAddress().getDistrict().getName();
      }
      return null;
    }

  }

  public void updateDistricts(List<District> districts) {
    edit.updateDistricts(districts);
  }

  public void updateProvinces(List<Province> provinces) {
    edit.updateProvinces(provinces);
  }

  public void initializeCustomers() {
    tableModel.update();
  }

}
