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

import entities.MeasurementUnit;
import model.interfaces.MeasurementUnitAdminInterface;
import presentation.edits.EditMeasurementUnitPane;
import presentation.utils.Colors;

public class MeasurementUnitView extends JPanel{

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JPanel southPane;
  private JPanel centerPane;
 
  private JTable table;
  private TableModel tableModel;
  private EditMeasurementUnitPane edit;
  private MeasurementUnitAdminInterface admin;
  private List<MeasurementUnit> unidades;
  
  public MeasurementUnitView() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder("Unidades De Medida"));
    setBackground(Colors.BACKGROUND.color());
    add(getContentPane(), BorderLayout.CENTER);
    addActionsListenerToEditPane();
    addTableListeners();
  }
  
  public void setMeasurementUnitAdmin(MeasurementUnitAdminInterface admin) {
   this.admin = admin;
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
    edit = new EditMeasurementUnitPane();
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
        if(edit.dataReady()) {
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
              if(admin.createMeasurementUnit(edit.getCurrentUnit())) {
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
              if(admin.editMeasurementUnit(edit.getCurrentUnit())) {
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
            if(edit.dataReady()) {
              if(admin.deleteMeasurementUnit(edit.getCurrentUnit())) {
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
        edit.setCurrentUnidadDeMedida(unidades.get(table.getSelectedRow()));
      }
    });
  }
  
  private class TableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private String[] names = {"Id","Nombre"};
    
    private TableModel() {
      unidades = new ArrayList<>();
    }
    
    public void update() {
      List<MeasurementUnit> data = admin.readAllMeasurementUnit();
      if(data != null) {
        unidades = data;
        fireTableDataChanged();
        productView.updateMeasurementUnit(unidades);
      }
    }
    
    @Override
    public int getRowCount() {
      return unidades.size();
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
      if(columnIndex == 0) {
        return unidades.get(rowIndex).getId();
      }
      if(columnIndex == 1) {
        return unidades.get(rowIndex).getUnit();
      }
      return null;
    }
    
  }
  
  
  private ProductView productView;
  public void setProductView(ProductView productView) {
    this.productView = productView;
  }
  
}
