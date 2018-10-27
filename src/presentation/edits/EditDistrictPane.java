package presentation.edits;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import entities.District;
import entities.Province;
import entities.Validar;
import presentation.extras.DeckeFont;
import presentation.utils.Colors;
import presentation.utils.Icon;

public class EditDistrictPane extends JPanel {

  private static final long serialVersionUID = 1L;
  private static javax.swing.Icon errIcon = Icon.load("/error-field.png");

  private JPanel infoDistrictPane;

  private int id;
  private JTextField name;
  private JLabel errName;
  private JComboBox<Province> provinceCombo;
  private JLabel errProvince;
  private ButtonsPane buttonsPane;

  public EditDistrictPane() {
    setLayout(new BorderLayout());
    add(createInfoDistrictPane(), BorderLayout.CENTER);
    add(createButtonsPane(), BorderLayout.SOUTH);
    addKeyListeners();
    addItemListenersCombos();
    disableFields();
  }

  private JPanel createInfoDistrictPane() {
    infoDistrictPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
    infoDistrictPane.setPreferredSize(new Dimension(300, 80));
    infoDistrictPane.setBackground(Colors.BACKGROUND.color());
    JPanel infoPane = new JPanel(new BorderLayout(0, 0));
    JPanel infoCenter = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
    infoCenter.setBackground(Colors.BACKGROUND.color());
    infoCenter.setPreferredSize(new Dimension(300, 100));
    infoCenter.add(createLabel("nombre"));
    infoCenter.add(name = new JTextField(12));
    infoCenter.add(errName = createErrLabel());
    infoCenter.add(createLabel("Provincia"));
    infoCenter.add(provinceCombo = createProvinceCombo());
    infoCenter.add(errProvince = createErrLabel());
    infoPane.add(infoCenter, BorderLayout.CENTER);
    infoDistrictPane.add(infoPane);
    return infoDistrictPane;
  }

  private JLabel createLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(DeckeFont.INSTANCE.getFont());
    Dimension dimension = new Dimension(100, 30);
    label.setPreferredSize(dimension);
    return label;
  }

  private JComboBox<Province> createProvinceCombo() {
    provinceCombo = new JComboBox<>();
    provinceCombo.setBackground(Colors.BACKGROUND.color());
    provinceCombo.setPreferredSize(new Dimension(136, 20));
    return provinceCombo;
  }

  private JLabel createErrLabel() {
    JLabel label = new JLabel();
    label.setPreferredSize(new Dimension(20, 20));
    return label;
  }

  private JPanel createButtonsPane() {
    buttonsPane = new ButtonsPane(85, 30);
    return buttonsPane;
  }

  public boolean dataReady() {
    return !name.getText().isEmpty();
  }

  public boolean validateFields() {
    boolean result = true;;
    if (!Validar.esNombreProvincia_Localidad(name.getText())) {
      errName.setIcon(errIcon);
      result = false;
    }
    if (provinceCombo.getItemCount() == 0) {
      errProvince.setIcon(errIcon);
      result = false;
    }
    hasErrors = !result;
    return result;
  }

  private void addKeyListeners() {
    addKeyListenerField(name);
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

  private void addItemListenersCombos() {
    addItemListener(provinceCombo);
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

  private void removeErrorIcons() {
    errName.setIcon(null);
    errProvince.setIcon(null);
    hasErrors = false;
  }

  public void clearFields() {
    name.setText("");
    if (hasErrors) {
      removeErrorIcons();;
    }
  }

  public void setCurrentDistrict(District district) {
    id = district.getId();
    name.setText(district.getName());
    provinceCombo.setSelectedItem(district.getProvince());
  }

  public District getCurrentDistrict() {
    String n = name.getText();
    Province p = (Province) provinceCombo.getSelectedItem();
    return new District(id, n, p);
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

  public void enableFields() {
    setEditableFields(true);
  }

  public void disableFields() {
    setEditableFields(false);
  }

  private void setEditableFields(boolean editable) {
    name.setEditable(editable);
    provinceCombo.setEnabled(editable);
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

  public void updateProvinces(List<Province> provinces) {
    provinceCombo.removeAllItems();
    for (Province province : provinces) {
      provinceCombo.addItem(province);
    }
  }

}
