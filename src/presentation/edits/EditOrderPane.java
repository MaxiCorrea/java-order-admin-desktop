package presentation.edits;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import org.jdesktop.swingx.JXDatePicker;
import entities.Order;
import entities.OrderStatus;
import entities.PaymentMethod;
import entities.TimeZone;
import presentation.extras.DeckeFont;
import presentation.utils.Colors;
import presentation.utils.Icon;

public class EditOrderPane extends JPanel {
  private static final long serialVersionUID = 1L;
  private static javax.swing.Icon errIcon = Icon.load("/error-field.png");

  private JPanel infoOrderPane;

  private JTextField id;
  private JLabel errId;

  private JXDatePicker datePicker;
  private JLabel errDatePicker;

  private JSpinner timeSpinner;
  private JLabel errTime;

  private JComboBox<PaymentMethod> paymentMethodCombo;
  private JLabel errPaymentMethod;

  private JComboBox<TimeZone> timeZoneCombo;
  private JLabel errTimeZone;

  private JComboBox<OrderStatus> orderStatusCombo;
  private JLabel errStatus;
  private JLabel totalLabel;

  private ButtonsPane buttonsPane;


  public EditOrderPane() {
    setLayout(new BorderLayout());
    add(createInfoOrderPane(), BorderLayout.CENTER);
    add(createButtonsPane(), BorderLayout.SOUTH);
    addItemListenersCombos();
    disableFields();
  }

  private JPanel createInfoOrderPane() {
    infoOrderPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
    infoOrderPane.setPreferredSize(new Dimension(300, 490));
    infoOrderPane.setBackground(Colors.BACKGROUND.color());

    JPanel infoPane = new JPanel(new BorderLayout());
    JPanel infoCenter = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 1));
    infoCenter.setBackground(Colors.BACKGROUND.color());
    infoCenter.setPreferredSize(new Dimension(400, 282));
    infoCenter.setBorder(BorderFactory.createTitledBorder("Informacion"));

    infoCenter.add(createLabel("numero "));
    infoCenter.add(id = new JTextField(12));
    id.setEnabled(false);

    infoCenter.add(errId = createErrLabel());
    infoCenter.add(createLabel("fecha "));
    infoCenter.add(datePicker = createDatePicker());
    infoCenter.add(errDatePicker = createErrLabel());
    infoCenter.add(createLabel("hora "));
    infoCenter.add(timeSpinner = createSpinner());
    timeSpinner.setEnabled(false);
    infoCenter.add(errTime = createErrLabel());
    infoCenter.add(createLabel("pago "));
    infoCenter.add(paymentMethodCombo = createPaymentMethodCombo());
    infoCenter.add(errPaymentMethod = createErrLabel());
    infoCenter.add(createLabel("franja horaria "));
    infoCenter.add(timeZoneCombo = createTimeZoneCombo());
    infoCenter.add(errTimeZone = createErrLabel());
    infoCenter.add(createLabel("estado "));
    infoCenter.add(orderStatusCombo = createOrderStatusCombo());
    infoCenter.add(errStatus = createErrLabel());

    JLabel space = new JLabel();
    space.setPreferredSize(new Dimension(350, 4));
    infoCenter.add(space);

    infoCenter.add(totalLabel = createTotalLabel());

    infoPane.add(infoCenter, BorderLayout.CENTER);
    infoOrderPane.add(infoPane);
    return infoOrderPane;
  }

  private JLabel createLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(DeckeFont.INSTANCE.getFont());
    Dimension dimension = new Dimension(140, 30);
    label.setPreferredSize(dimension);
    return label;
  }

  private JLabel createErrLabel() {
    JLabel label = new JLabel("");
    label.setPreferredSize(new Dimension(20, 20));
    return label;
  }

  private JLabel createTotalLabel() {
    JLabel label = new JLabel("Total A Pagar : $100", JLabel.CENTER);
    label.setOpaque(true);
    label.setBackground(Colors.PANEL_COLOR.color());
    label.setForeground(Color.YELLOW);
    label.setFont(DeckeFont.INSTANCE.getFont().deriveFont(25f));
    label.setPreferredSize(new Dimension(380, 60));
    return label;
  }

  private JXDatePicker createDatePicker() {
    datePicker = new JXDatePicker(new Date());
    datePicker.setLocale(new Locale("es", "ES"));
    datePicker.getEditor().setEditable(false);
    datePicker.getEditor().setBackground(Colors.BACKGROUND.color());
    datePicker.setPreferredSize(new Dimension(136, 20));
    ((JButton) datePicker.getComponent(1)).setBackground(Colors.BACKGROUND.color());
    datePicker.setEnabled(false);
    return datePicker;
  }

  private JComboBox<PaymentMethod> createPaymentMethodCombo() {
    paymentMethodCombo = new JComboBox<>();
    paymentMethodCombo.setBackground(Colors.BACKGROUND.color());
    paymentMethodCombo.setPreferredSize(new Dimension(136, 20));
    return paymentMethodCombo;
  }

  private JComboBox<TimeZone> createTimeZoneCombo() {
    timeZoneCombo = new JComboBox<>();
    timeZoneCombo.setBackground(Colors.BACKGROUND.color());
    timeZoneCombo.setPreferredSize(new Dimension(136, 20));
    return timeZoneCombo;
  }

  private JComboBox<OrderStatus> createOrderStatusCombo() {
    orderStatusCombo = new JComboBox<>();
    orderStatusCombo.setBackground(Colors.BACKGROUND.color());
    orderStatusCombo.setPreferredSize(new Dimension(136, 20));
    return orderStatusCombo;
  }

  private JSpinner createSpinner() {
    SpinnerDateModel dateModel = new SpinnerDateModel();
    JSpinner spinner = new JSpinner(dateModel);
    JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
    spinner.setEditor(timeEditor);
    spinner.setPreferredSize(new Dimension(136, 20));
    return spinner;
  }

  public void enableFields() {
    setEditableFields(true);
  }

  public void disableFields() {
    setEditableFields(false);
  }

  public void enableFieldsToEdit() {
    setEditableFields(true);
    id.setEditable(false);
  }

  private void setEditableFields(boolean editable) {
    paymentMethodCombo.setEnabled(editable);;
    paymentMethodCombo.setEnabled(editable);
    orderStatusCombo.setEnabled(editable);
    timeZoneCombo.setEnabled(editable);
  }

  public boolean checkOrderStatus() {
    if (orderStatusCombo.getItemCount() == 0) {
      return false;
    }
    if (((OrderStatus) orderStatusCombo.getSelectedItem()).getStatus().equals("En Edicion")) {
      return true;
    }
    if (((OrderStatus) orderStatusCombo.getSelectedItem()).getStatus().equals("Ingresado")) {
      return true;
    }
    return false;
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

  public String getAction() {
    return buttonsPane.getAction();
  }

  public void clearFields() {
    id.setText("");
    totalLabel.setText("Importe Total $0");
    if (hasErrors) {
      removeErrorIcons();
    }
  }

  public boolean dataReady() {
    return !id.getText().isEmpty();
  }

  public boolean validateFields() {
    boolean result = true;
    if (paymentMethodCombo.getItemCount() == 0) {
      errPaymentMethod.setIcon(errIcon);
      result = false;
    }
    if (orderStatusCombo.getItemCount() == 0) {
      errStatus.setIcon(errIcon);
      result = false;
    }
    if (timeZoneCombo.getItemCount() == 0) {
      errTimeZone.setIcon(errIcon);
      result = false;
    }
    hasErrors = !result;
    return result;
  }

  private void addItemListenersCombos() {
    addItemListener(timeZoneCombo);
    addItemListener(orderStatusCombo);
    addItemListener(timeZoneCombo);
  }

  private boolean hasErrors;

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
    errDatePicker.setIcon(null);
    errId.setIcon(null);
    errPaymentMethod.setIcon(null);
    errStatus.setIcon(null);
    errTime.setIcon(null);
    errTimeZone.setIcon(null);
    hasErrors = false;
  }

  public void setCurrentOrder(Order order) {
    id.setText("" + order.getId());
    timeSpinner.getModel().setValue(order.getHora());
    datePicker.setDate(toUtilDate(order.getFecha()));
    paymentMethodCombo.setSelectedItem(order.getFormaDePago());
    orderStatusCombo.setSelectedItem(order.getEstadoDePedido());
    timeZoneCombo.setSelectedItem(order.getFranjaHoraria());
    totalLabel.setText("Importe Total $" + order.getImporteTotal());
  }

  public void setTatalLabel(BigDecimal bigDecimal) {
    totalLabel.setText("importe Total $" + bigDecimal);
  }

  public Order getCurrentOrder() {
    Long id = null;
    if (!this.id.getText().isEmpty()) {
      id = Long.valueOf(this.id.getText());
    }
    Time time = new Time(System.currentTimeMillis());
    PaymentMethod method = (PaymentMethod) paymentMethodCombo.getSelectedItem();
    OrderStatus status = (OrderStatus) orderStatusCombo.getSelectedItem();
    TimeZone timeZone = (TimeZone) timeZoneCombo.getSelectedItem();
    return new Order(id, time, null, null, null, null, createDate(datePicker.getDate()), method,
        status, timeZone);
  }


  private entities.Date createDate(java.util.Date date) {
    String[] names = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    String dayName = names[localDate.getDayOfWeek().getValue() - 1];
    return new entities.Date((byte) localDate.getDayOfMonth(), (byte) localDate.getMonthValue(),
        (short) (localDate.getYear()), dayName);
  }


  public Date toUtilDate(entities.Date date) {
    LocalDate localDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
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

  public void updatePaymentMethods(List<PaymentMethod> methods) {
    paymentMethodCombo.removeAllItems();
    for (PaymentMethod method : methods) {
      paymentMethodCombo.addItem(method);
    }
  }

  public void updateOrderStatus(List<OrderStatus> status) {
    orderStatusCombo.removeAllItems();
    for (OrderStatus s : status) {
      orderStatusCombo.addItem(s);
    }
  }

  public void updateTimeZoneCombo(List<TimeZone> timeZones) {
    timeZoneCombo.removeAllItems();
    for (TimeZone z : timeZones) {
      timeZoneCombo.addItem(z);
    }
  }

}
