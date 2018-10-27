package entities;

public class Customer {

  private int dni;
  private String nombre;
  private String apellido;
  private Address address;
  private String landline;
  private String cellPhone;

  public Customer(int dni, String nombre, String apellido, Address address, String phone1,
      String phone2) {
    super();
    this.dni = dni;
    this.nombre = nombre;
    this.apellido = apellido;
    this.address = address;
    this.landline = phone1;
    this.cellPhone = phone2;
  }

  public int getDni() {
    return dni;
  }

  public void setDni(int dni) {
    this.dni = dni;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getLandLine() {
    return landline;
  }

  public void setLandLine(String phone1) {
    this.landline = phone1;
  }

  public String getCellPhone() {
    return cellPhone;
  }

  public void setCellphone(String phone2) {
    this.cellPhone = phone2;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
    result = prime * result + dni;
    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
    result = prime * result + ((landline == null) ? 0 : landline.hashCode());
    result = prime * result + ((cellPhone == null) ? 0 : cellPhone.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Customer other = (Customer) obj;
    if (address == null) {
      if (other.address != null)
        return false;
    } else if (!address.equals(other.address))
      return false;
    if (apellido == null) {
      if (other.apellido != null)
        return false;
    } else if (!apellido.equals(other.apellido))
      return false;
    if (dni != other.dni)
      return false;
    if (nombre == null) {
      if (other.nombre != null)
        return false;
    } else if (!nombre.equals(other.nombre))
      return false;
    if (landline == null) {
      if (other.landline != null)
        return false;
    } else if (!landline.equals(other.landline))
      return false;
    if (cellPhone == null) {
      if (other.cellPhone != null)
        return false;
    } else if (!cellPhone.equals(other.cellPhone))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return nombre + " " + apellido;
  }

}
