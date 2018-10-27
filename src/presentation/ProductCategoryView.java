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
import entities.ProductCategory;
import model.interfaces.ProductCategoryAdminInterface;
import presentation.edits.EditProductCategoryPane;
import presentation.utils.Colors;

public class ProductCategoryView extends JPanel {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JPanel southPane;
  private JPanel centerPane;

  private JTable table;
  private TableModel tableModel;
  private EditProductCategoryPane edit;
  private ProductCategoryAdminInterface productCategoryAdmin;
  private List<ProductCategory> categories;

  public ProductCategoryView() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder("Categorias de Producto"));
    setBackground(Colors.BACKGROUND.color());
    add(getContentPane(), BorderLayout.CENTER);
    addActionsListenerToEditPane();
    addTableListeners();
  }

  public void setProductCategoryAdminInterface(ProductCategoryAdminInterface admin) {
    this.productCategoryAdmin = admin;
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
    edit = new EditProductCategoryPane();
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
              if (productCategoryAdmin.createProductCategory(edit.getCurrentProductCategory())) {
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
              if (productCategoryAdmin.editProductCategory(edit.getCurrentProductCategory())) {
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
              if (productCategoryAdmin.deleteProductCategory(edit.getCurrentProductCategory())) {
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
        edit.setCurrentProductCategory(categories.get(table.getSelectedRow()));
      }
    });
  }


  private class TableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private String[] names = {"Id", "Categoria"};

    private TableModel() {
      categories = new ArrayList<>();
    }

    public void update() {
      List<ProductCategory> data = productCategoryAdmin.readAllProductCategory();
      if (data != null) {
        categories = data;
        productView.updateProductCategoy(categories);
        fireTableDataChanged();
      }
    }

    @Override
    public int getRowCount() {
      return categories.size();
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
        return categories.get(rowIndex).getId();
      }
      if (columnIndex == 1) {
        return categories.get(rowIndex).getName();
      }
      return null;
    }
  }

  private ProductView productView;

  public void setProductView(ProductView productView) {
    this.productView = productView;
  }

}
