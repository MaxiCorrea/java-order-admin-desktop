package presentation.edits;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import entities.Address;
import entities.Customer;
import entities.District;
import entities.Province;
import entities.Validar;
import presentation.extras.DeckeFont;
import presentation.utils.Colors;
import presentation.utils.Icon;

public class EditCustomerPane extends JPanel {

  private static final long serialVersionUID = 1L;
  private static javax.swing.Icon errIcon = Icon.load("/error-field.png");
  private JPanel infoCustomerPane;
  private JTextField dni;
  private JLabel errDni;
  private JTextField nombre;
  private JLabel errNombre;
  private JTextField apellido;
  private JLabel errApellido;
  private JTextField alturaDomicilio;
  private JLabel errAlturaDomincilio;
  private JTextField piso;
  private JLabel errPiso;
  private JTextField departamento;
  private JLabel errDepartamento;
  private JTextField codigoPostal;
  private JLabel errCodigoPostal;
  private JComboBox<Province> provincesCombo;
  private JLabel errProvince;
  private JComboBox<District> districtCombo;
  private JLabel errDistrict;
  private JTextField calle;
  private JLabel errCalle;
  private JTextField telefonoDomicilio;
  private JLabel errTelDomicilio;
  private JTextField telefonoMovil;
  private JLabel errTelMovil;

  private ButtonsPane buttonsPane;

  public EditCustomerPane() {
    setLayout(new BorderLayout());
    add(createInfoCustomerPane(), BorderLayout.CENTER);
    add(createButtonsPane(), BorderLayout.SOUTH);
    addKeyListeners();
    addItemListenersCombos();
    disableFields();
  }

  private JPanel createInfoCustomerPane() {
    infoCustomerPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
    infoCustomerPane.setPreferredSize(new Dimension(300, 490));
    infoCustomerPane.setBackground(Colors.BACKGROUND.color());
    JPanel personalData = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 1));
    personalData.setBackground(Colors.BACKGROUND.color());
    personalData.setPreferredSize(new Dimension(400, 200));
    personalData.setBorder(BorderFactory.createTitledBorder("Personal"));
    personalData.add(createLabel("DNI "));
    personalData.add(dni = new JTextField(12));
    personalData.add(errDni = createErrLabel());
    personalData.add(createLabel("Nombre "));
    personalData.add(nombre = new JTextField(12));
    personalData.add(errNombre = createErrLabel());
    personalData.add(createLabel("Apellido "));
    personalData.add(apellido = new JTextField(12));
    personalData.add(errApellido = createErrLabel());
    personalData.add(createLabel("Telefono Domicilio "));
    personalData.add(telefonoDomicilio = new JTextField(12));
    personalData.add(errTelDomicilio = createErrLabel());
    personalData.add(createLabel("Telefono Movil"));
    personalData.add(telefonoMovil = new JTextField(12));
    personalData.add(errTelMovil = createErrLabel());
    infoCustomerPane.add(personalData);

    JPanel homeData = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 1));
    homeData.setBackground(Colors.BACKGROUND.color());
    homeData.setPreferredSize(new Dimension(400, 280));
    homeData.setBorder(BorderFactory.createTitledBorder("Domicilio"));
    homeData.add(createLabel("Altura "));
    homeData.add(alturaDomicilio = new JTextField(12));
    homeData.add(errAlturaDomincilio = createErrLabel());
    homeData.add(createLabel("Calle "));
    homeData.add(calle = new JTextField(12));
    homeData.add(errCalle = createErrLabel());
    homeData.add(createLabel("piso "));
    homeData.add(piso = new JTextField(12));
    homeData.add(errPiso = createErrLabel());
    homeData.add(createLabel("Departamento "));
    homeData.add(departamento = new JTextField(12));
    homeData.add(errDepartamento = createErrLabel());
    homeData.add(createLabel("Codigo Postal "));
    homeData.add(codigoPostal = new JTextField(12));
    homeData.add(errCodigoPostal = createErrLabel());
    homeData.add(createLabel("Provincia "));
    homeData.add(provincesCombo = createProvinceCombo());
    homeData.add(errProvince = createErrLabel());
    homeData.add(createLabel("Localidad "));
    homeData.add(districtCombo = createDistrictCombo());
    homeData.add(errDistrict = createErrLabel());

    infoCustomerPane.add(homeData);
    return infoCustomerPane;
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

  private JComboBox<Province> createProvinceCombo() {
    provincesCombo = new JComboBox<>();
    provincesCombo.setBackground(Colors.BACKGROUND.color());
    provincesCombo.setPreferredSize(new Dimension(136, 20));
    return provincesCombo;
  }

  private JComboBox<District> createDistrictCombo() {
    districtCombo = new JComboBox<>();
    districtCombo.setBackground(Colors.BACKGROUND.color());
    districtCombo.setPreferredSize(new Dimension(136, 20));
    return districtCombo;
  }

  public void enableFields() {
    setEditableFields(true);
  }

  public void disableFields() {
    setEditableFields(false);
  }

  public void enableFieldsToEdit() {
    setEditableFields(true);
    dni.setEditable(false);
  }

  private void setEditableFields(boolean editable) {
    dni.setEditable(editable);
    nombre.setEditable(editable);
    apellido.setEditable(editable);
    alturaDomicilio.setEditable(editable);
    piso.setEditable(editable);
    departamento.setEditable(editable);
    codigoPostal.setEditable(editable);
    provincesCombo.setEnabled(editable);
    districtCombo.setEnabled(editable);
    calle.setEditable(editable);
    telefonoDomicilio.setEditable(editable);
    telefonoMovil.setEditable(editable);
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
    dni.setText("");
    nombre.setText("");
    apellido.setText("");
    alturaDomicilio.setText("");
    piso.setText("");
    departamento.setText("");
    codigoPostal.setText("");
    telefonoMovil.setText("");
    telefonoDomicilio.setText("");
    calle.setText("");
    if (hasErrors) {
      removeErrorIcons();
    }
  }

  public boolean dataReady() {
    return !dni.getText().isEmpty();
  }

  public boolean validateFields() {
    boolean result = true;
    Validar validar = new Validar();
    if (!validar.esDNI(dni.getText())) {
      errDni.setIcon(errIcon);
      result = false;
    }
    if (!validar.esNombre_ApellidoCliente(nombre.getText())) {
      errNombre.setIcon(errIcon);
      result = false;
    }
    if (!validar.esNombre_ApellidoCliente(apellido.getText())) {
      errApellido.setIcon(errIcon);
      result = false;
    }
    if (!validar.esAlturaDomicilio(alturaDomicilio.getText())) {
      errAlturaDomincilio.setIcon(errIcon);
      result = false;
    }
    if (piso.getText().isEmpty()) {
      piso.setText(null);
    } else {
      if (!validar.esPiso_Dpto(piso.getText())) {
        errPiso.setIcon(errIcon);
        result = false;
      }
    }
    if (departamento.getText().isEmpty()) {
      departamento.setText(null);
    } else {
      if (!validar.esPiso_Dpto(departamento.getText())) {
        errDepartamento.setIcon(errIcon);
        result = false;
      }
    }
    if (!validar.esCP(codigoPostal.getText())) {
      errCodigoPostal.setIcon(errIcon);
      result = false;
    }
    if (!validar.esTelefono(telefonoDomicilio.getText())) {
      errTelDomicilio.setIcon(errIcon);
      result = false;
    }
    if (!validar.esTelefono(telefonoMovil.getText())) {
      errTelMovil.setIcon(errIcon);
      result = false;
    }
    if (!validar.esCalle(calle.getText())) {
      errCalle.setIcon(errIcon);
      result = false;
    }
    if (provincesCombo.getItemCount() == 0) {
      errProvince.setIcon(errIcon);
      result = false;
    }
    if (districtCombo.getItemCount() == 0) {
      errDistrict.setIcon(errIcon);
      result = false;
    }

    hasErrors = !result;
    return result;
  }

  private void addKeyListeners() {
    addKeyListenerField(dni);
    addKeyListenerField(nombre);
    addKeyListenerField(apellido);
    addKeyListenerField(alturaDomicilio);
    addKeyListenerField(piso);
    addKeyListenerField(departamento);
    addKeyListenerField(codigoPostal);
    addKeyListenerField(telefonoDomicilio);
    addKeyListenerField(telefonoMovil);
    addKeyListenerField(calle);
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
    errDni.setIcon(null);
    errNombre.setIcon(null);
    errApellido.setIcon(null);
    errAlturaDomincilio.setIcon(null);
    errPiso.setIcon(null);
    errDepartamento.setIcon(null);
    errCodigoPostal.setIcon(null);
    errProvince.setIcon(null);
    errDistrict.setIcon(null);
    errCalle.setIcon(null);
    errTelDomicilio.setIcon(null);
    errTelMovil.setIcon(null);
    hasErrors = false;
  }

  private void addItemListenersCombos() {
    addItemListener(provincesCombo);
    addItemListener(districtCombo);
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

  public void setCurrentCustomer(Customer customer) {
    dni.setText("" + customer.getDni());
    nombre.setText(customer.getNombre());
    apellido.setText(customer.getApellido());
    alturaDomicilio.setText("" + customer.getAddress().getStreetNumber());
    if (customer.getAddress().getFloor() == 0) {
      piso.setText("");
    } else {
      piso.setText("" + customer.getAddress().getFloor());
    }
    if (customer.getAddress().getFlat() == 0) {
      departamento.setText("");
    } else {
      departamento.setText("" + customer.getAddress().getFlat());
    }
    codigoPostal.setText("" + customer.getAddress().getCP());
    provincesCombo.setSelectedItem(customer.getAddress().getDistrict().getProvince());
    districtCombo.setSelectedItem(customer.getAddress().getDistrict());
    telefonoDomicilio.setText("" + customer.getLandLine());
    telefonoMovil.setText("" + customer.getCellPhone());
    calle.setText(customer.getAddress().getStreet());
  }

  public Customer getCurrentCustomer() {

    int id = Integer.parseInt(dni.getText());
    String n = nombre.getText();
    String a = apellido.getText();
    String piso = this.piso.getText();
    Byte floor;
    String dpto = departamento.getText();
    Byte flat;
    if (!piso.isEmpty()) {
      floor = Byte.valueOf(piso);
    } else {
      floor = null;
    }
    if (!dpto.isEmpty()) {
      flat = Byte.valueOf(dpto);
    } else {
      flat = null;
    }
    short streetNumber = Short.valueOf(alturaDomicilio.getText());
    short cp = Short.valueOf(codigoPostal.getText());
    District district = (District) districtCombo.getSelectedItem();
    String street = calle.getText();
    Address address = new Address(street, streetNumber, floor, flat, cp, district);

    String landline = telefonoDomicilio.getText();
    String cellPhone = telefonoMovil.getText();
    Customer customer = new Customer(id, n, a, address, landline, cellPhone);
    return customer;
  }

  public Customer getCurrentCustomer(Integer idAddress) {

    int id = Integer.parseInt(dni.getText());
    String n = nombre.getText();
    String a = apellido.getText();
    String piso = this.piso.getText();
    Byte floor;
    String dpto = departamento.getText();
    Byte flat;
    if (!piso.isEmpty()) {
      floor = Byte.valueOf(piso);
    } else {
      floor = null;
    }
    if (!dpto.isEmpty()) {
      flat = Byte.valueOf(dpto);
    } else {
      flat = null;
    }
    short streetNumber = Short.valueOf(alturaDomicilio.getText());
    short cp = Short.valueOf(codigoPostal.getText());
    District district = (District) districtCombo.getSelectedItem();
    String street = calle.getText();
    Address address = new Address(idAddress, street, streetNumber, floor, flat, cp, district);

    String landline = telefonoDomicilio.getText();
    String cellPhone = telefonoMovil.getText();
    Customer customer = new Customer(id, n, a, address, landline, cellPhone);
    return customer;
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

  public void updateDistricts(List<District> districts) {
    districtCombo.removeAllItems();
    for (District district : districts) {
      districtCombo.addItem(district);
    }
  }

  public void updateProvinces(List<Province> provinces) {
    provincesCombo.removeAllItems();
    for (Province province : provinces) {
      provincesCombo.addItem(province);;
    }
  }

}
