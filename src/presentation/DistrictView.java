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

import entities.District;
import entities.Province;
import model.interfaces.DistrictAdminInterface;
import presentation.edits.EditDistrictPane;
import presentation.utils.Colors;

public class DistrictView extends JPanel {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JPanel southPane;
  private JPanel centerPane;

  private JTable table;
  private TableModel tableModel;
  private EditDistrictPane edit;
  private List<District> districts;

  private DistrictAdminInterface districtAdmin;

  public DistrictView() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder("Localidades"));
    setBackground(Colors.BACKGROUND.color());
    add(getContentPane(), BorderLayout.CENTER);
    addActionsListenerToEditPane();
    addTableListeners();
  }

  public void setDistrictAdminInterface(DistrictAdminInterface admin) {
    this.districtAdmin = admin;
  }

  private JPanel getContentPane() {
    contentPane = new JPanel(new BorderLayout());
    contentPane.setPreferredSize(new Dimension(900, 110));
    contentPane.setBackground(Colors.BACKGROUND.color());
    contentPane.add(createCenterPane(), BorderLayout.CENTER);
    contentPane.add(createSouthPane(), BorderLayout.EAST);
    return contentPane;
  }

  private JPanel createCenterPane() {
    centerPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
    centerPane.setBackground(Colors.BACKGROUND.color());
    centerPane.setPreferredSize(new Dimension(300, 110));
    JScrollPane pane = new JScrollPane(createTableAndModel());
    pane.setPreferredSize(new Dimension(400, 90));
    pane.setBackground(Colors.BACKGROUND.color());
    pane.getViewport().setBackground(Colors.BACKGROUND.color());
    centerPane.add(pane);
    return centerPane;
  }

  private JPanel createSouthPane() {
    southPane = new JPanel(new BorderLayout());
    southPane.setPreferredSize(new Dimension(400, 110));
    southPane.setBackground(Colors.BACKGROUND.color());
    edit = new EditDistrictPane();
    southPane.add(edit, BorderLayout.CENTER);
    return southPane;
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
          edit.enableFields();
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
              if (districtAdmin.createDistrict(edit.getCurrentDistrict())) {
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
              if (districtAdmin.editDistrict(edit.getCurrentDistrict())) {
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
              if (districtAdmin.deleteDistrict(edit.getCurrentDistrict())) {
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

  private void addTableListeners() {
    table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        edit.setCurrentDistrict(districts.get(table.getSelectedRow()));
      }
    });
  }

  private class TableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private String[] names = {"Id", "Nombre", "Provincia"};

    private TableModel() {
      districts = new ArrayList<>();
    }

    public void update() {
      List<District> data = districtAdmin.readAllDistrict();
      if (data != null) {
        districts = data;
        customerView.updateDistricts(districts);
        fireTableDataChanged();
      }
    }

    @Override
    public int getRowCount() {
      return districts.size();
    }

    @Override
    public String getColumnName(int column) {
      return names[column];
    }

    @Override
    public int getColumnCount() {
      return names.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      if (columnIndex == 0) {
        return districts.get(rowIndex).getId();
      }
      if (columnIndex == 1) {
        return districts.get(rowIndex).getName();
      }
      if (columnIndex == 2) {
        return districts.get(rowIndex).getProvince().getName();
      }
      return null;
    }

  }

  private CustomerView customerView;

  public void setCustomer(CustomerView customerView) {
    this.customerView = customerView;
  }

  public void updateProvinces(List<Province> provinces) {
    edit.updateProvinces(provinces);
  }

}
