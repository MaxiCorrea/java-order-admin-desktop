package presentation.edits;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import entities.MeasurementUnit;
import entities.Product;
import entities.ProductCategory;
import entities.Validar;
import presentation.extras.DeckeFont;
import presentation.utils.Colors;
import presentation.utils.Icon;

public class EditProductPane extends JPanel {

  private static final long serialVersionUID = 1L;
  private static javax.swing.Icon errIcon = Icon.load("/error-field.png");

  private JPanel infoProductPane;
  private JTextField code;
  private JLabel errCade;
  private JTextField name;
  private JLabel errName;
  private JTextArea description;
  private JTextField precioPorUnidad;
  private JLabel errPrecionPorUnidad;
  private JComboBox<MeasurementUnit> units;
  private JLabel errUnits;
  private JComboBox<ProductCategory> categoriaCombo;
  private JLabel errCategoria;

  private ButtonsPane buttonsPane;

  public EditProductPane() {
    setLayout(new BorderLayout());
    add(createInfoProductPane(), BorderLayout.CENTER);
    add(createButtonsPane(), BorderLayout.SOUTH);
    addKeyListeners();
    addItemListenersCombos();
    disableFields();
  }

  private JPanel createInfoProductPane() {
    infoProductPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 1));
    infoProductPane.setPreferredSize(new Dimension(300, 490));
    infoProductPane.setBackground(Colors.BACKGROUND.color());

    JPanel infoPane = new JPanel(new BorderLayout());
    JPanel infoCenter = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 1));
    infoCenter.setBackground(Colors.BACKGROUND.color());
    infoCenter.setPreferredSize(new Dimension(400, 298));
    infoCenter.setBorder(BorderFactory.createTitledBorder("Informacion"));
    infoCenter.add(createLabel("codigo "));
    infoCenter.add(code = new JTextField(12));
    infoCenter.add(errCade = createErrLabel());
    infoCenter.add(createLabel("nombre "));
    infoCenter.add(name = new JTextField(12));
    infoCenter.add(errName = createErrLabel());
    infoCenter.add(createLabel("unidad de medida "));
    infoCenter.add(units = createUnidadDeMedidaCombo());
    infoCenter.add(errUnits = createErrLabel());
    infoCenter.add(createLabel("precio por unidad "));
    infoCenter.add(precioPorUnidad = new JTextField(12));
    infoCenter.add(errPrecionPorUnidad = createErrLabel());
    infoCenter.add(createLabel("categoria "));
    infoCenter.add(categoriaCombo = createProductCategoryCombo());
    infoCenter.add(errCategoria = createErrLabel());
    infoPane.add(infoCenter, BorderLayout.CENTER);

    description = new JTextArea(11, 20);
    description.setBorder(BorderFactory.createTitledBorder("Descripcion"));
    infoPane.add(description, BorderLayout.SOUTH);

    infoProductPane.add(infoPane);
    return infoProductPane;
  }

  private JLabel createLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(DeckeFont.INSTANCE.getFont());
    Dimension dimension = new Dimension(140, 30);
    label.setPreferredSize(dimension);
    return label;
  }

  private JLabel createErrLabel() {
    JLabel label = new JLabel();
    label.setPreferredSize(new Dimension(20, 20));
    return label;
  }

  private JComboBox<ProductCategory> createProductCategoryCombo() {
    categoriaCombo = new JComboBox<>();
    categoriaCombo.setBackground(Colors.BACKGROUND.color());
    categoriaCombo.setPreferredSize(new Dimension(136, 20));
    return categoriaCombo;
  }

  private JComboBox<MeasurementUnit> createUnidadDeMedidaCombo() {
    units = new JComboBox<>();
    units.setBackground(Colors.BACKGROUND.color());
    units.setPreferredSize(new Dimension(136, 20));
    return units;
  }

  public void enableFields() {
    setEditableFields(true);
  }

  public void disableFields() {
    setEditableFields(false);
  }

  public void enableFieldsToEdit() {
    setEditableFields(true);
    code.setEditable(false);
  }

  private void setEditableFields(boolean editable) {
    code.setEditable(editable);
    name.setEditable(editable);
    units.setEnabled(editable);
    categoriaCombo.setEnabled(editable);;
    precioPorUnidad.setEditable(editable);
    description.setEditable(editable);
  }

  private JPanel createButtonsPane() {
    buttonsPane = new ButtonsPane();
    return buttonsPane;
  }

  public void showCancelAndAcceptButton(String s1, String s2) {
    buttonsPane.showCancelAndAcceptButton(s1, s2);
  }

  public void hideCancelAndAcceptButton() {
    buttonsPane.hideCancelAndAcceptButton();
  }

  public void hideOneTwoThreeFourButtons() {
    buttonsPane.hideNewEditDeleteToListButtons();
  }

  public void showOneTwoThreeFourButtons() {
    buttonsPane.showNewEditDeleteToListButtons();
  }

  public void clearFields() {
    code.setText("");
    name.setText("");
    precioPorUnidad.setText("");
    description.setText("");
    if (hasErrors) {
      removeErrorIcons();
    }
  }

  public boolean dataReady() {
    return !code.getText().isEmpty();
  }

  public boolean validateFields() {
    boolean result = true;
    Validar validar = new Validar();
    if (!validar.esCodProducto(code.getText())) {
      errCade.setIcon(errIcon);
      result = false;
    }
    if (!validar.esNombreProducto(name.getText())) {
      errName.setIcon(errIcon);
      result = false;
    }
    if (!validar.esPrecio(precioPorUnidad.getText())) {
      errPrecionPorUnidad.setIcon(errIcon);
      result = false;
    }
    if (units.getItemCount() == 0) {
      errUnits.setIcon(errIcon);
      result = false;
    }
    if (categoriaCombo.getItemCount() == 0) {
      errCategoria.setIcon(errIcon);
      result = false;
    }
    hasErrors = !result;
    return result;
  }

  private void addItemListenersCombos() {
    addItemListener(units);
    addItemListener(categoriaCombo);
  }

  private void addItemListener(JComboBox<?> combo) {
    combo.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          if (hasErrors) {
            removeErrorIcons();
          }
        }
      }
    });
  }

  private void addKeyListeners() {
    addKeyListenerField(code);
    addKeyListenerField(name);
    addKeyListenerField(precioPorUnidad);
  }

  private boolean hasErrors = false;

  private void addKeyListenerField(JTextField field) {
    field.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        if (hasErrors) {
          removeErrorIcons();
        }
      }
    });
  }

  private void removeErrorIcons() {
    errCade.setIcon(null);
    errName.setIcon(null);
    errPrecionPorUnidad.setIcon(null);
    errUnits.setIcon(null);
    errCategoria.setIcon(null);
    hasErrors = false;
  }

  public void setCurrentProduct(Product product) {
    code.setText("" + product.getCodigo());
    name.setText(product.getNombre());
    description.setText(product.getDescripcion());
    precioPorUnidad.setText("" + product.getPrecioPorUnidad());
    units.setSelectedItem(product.getUnidadDeMedida());
    categoriaCombo.setSelectedItem(product.getCategoriaProducto());
  }

  public Product getCurrentProduct() {
    long codigo = Long.valueOf(code.getText());
    String n = name.getText();
    String d = description.getText();
    BigDecimal precio = new BigDecimal(precioPorUnidad.getText());
    MeasurementUnit unit = (MeasurementUnit) units.getSelectedItem();
    ProductCategory category = (ProductCategory) categoriaCombo.getSelectedItem();
    return new Product(codigo, n, d, precio, unit, category);
  }

  public JButton getNewButton() {
    return buttonsPane.getNewButton();
  }

  public JButton getEditButton() {
    return buttonsPane.getEditButton();
  }

  public JButton getDeleteButton() {
    return buttonsPane.getDeleteButton();
  }

  public JButton getToListButton() {
    return buttonsPane.getToListButton();
  }

  public JButton getCancelButton() {
    return buttonsPane.getCancelButton();
  }

  public JButton getAcceptButton() {
    return buttonsPane.getAcceptButton();
  }

  public String getAction() {
    return buttonsPane.getAction();
  }

  public void updateMeasurementUnit(List<MeasurementUnit> data) {
    units.removeAllItems();
    for (MeasurementUnit uni : data) {
      units.addItem(uni);
    }
  }

  public void updateProductCategoyCombo(List<ProductCategory> categories) {
    categoriaCombo.removeAllItems();
    for (ProductCategory p : categories) {
      categoriaCombo.addItem(p);
    }
  }

}
