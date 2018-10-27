package presentation.edits;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import entities.MeasurementUnit;
import entities.Validar;
import presentation.extras.DeckeFont;
import presentation.utils.Colors;
import presentation.utils.Icon;

public class EditMeasurementUnitPane extends JPanel {
  private static final long serialVersionUID = 1L;
  private static javax.swing.Icon errIcon = Icon.load("/error-field.png");

  private JPanel infoUnidadDeMedidaPane;

  private JTextField id;
  private JLabel errId;
  private JTextField name;
  private JLabel errName;
  private ButtonsPane buttonsPane;

  public EditMeasurementUnitPane() {
    setLayout(new BorderLayout());
    add(createInfoProvincePane(), BorderLayout.CENTER);
    add(createButtonsPane(), BorderLayout.SOUTH);
    addKeyListeners();
    disableFields();
  }

  private JPanel createInfoProvincePane() {
    infoUnidadDeMedidaPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
    infoUnidadDeMedidaPane.setPreferredSize(new Dimension(300, 80));
    infoUnidadDeMedidaPane.setBackground(Colors.BACKGROUND.color());
    JPanel infoPane = new JPanel(new BorderLayout(0, 0));
    JPanel infoCenter = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
    infoCenter.setBackground(Colors.BACKGROUND.color());
    infoCenter.setPreferredSize(new Dimension(300, 100));
    infoCenter.add(createLabel("id"));
    infoCenter.add(id = new JTextField(12));
    infoCenter.add(errId = createErrLabel());
    id.setEditable(false);
    infoCenter.add(createLabel("nombre"));
    infoCenter.add(name = new JTextField(12));
    infoCenter.add(errName = createErrLabel());
    infoPane.add(infoCenter, BorderLayout.CENTER);
    infoUnidadDeMedidaPane.add(infoPane);
    return infoUnidadDeMedidaPane;
  }

  private JLabel createLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(DeckeFont.INSTANCE.getFont());
    Dimension dimension = new Dimension(100, 30);
    label.setPreferredSize(dimension);
    return label;
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
    return !id.getText().isEmpty();
  }

  public boolean validateFields() {
    boolean result = true;
    if (!Validar.esUnidadMedida(name.getText())) {
      errName.setIcon(errIcon);
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

  private void removeErrorIcons() {
    errId.setIcon(null);
    errName.setIcon(null);
    hasErrors = false;
  }

  public void clearFields() {
    id.setText("");
    name.setText("");
    if (hasErrors) {
      removeErrorIcons();
    }
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

  public void setCurrentUnidadDeMedida(MeasurementUnit p) {
    this.id.setText("" + p.getId());
    this.name.setText(p.getUnit());
  }

  public MeasurementUnit getCurrentUnit() {
    if (id.getText().isEmpty()) {
      return new MeasurementUnit(name.getText());
    } else {
      short id = Short.valueOf(this.id.getText());
      return new MeasurementUnit(id, name.getText());
    }
  }

  public void enableFields() {
    setEditableFields(true);
  }

  public void disableFields() {
    setEditableFields(false);
  }

  private void setEditableFields(boolean editable) {
    name.setEditable(editable);;
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

}
