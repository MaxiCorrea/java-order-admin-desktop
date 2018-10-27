package entities;

public class TypePhoneCustomer {

  private TypePhone typePhone;
  private Integer customerDNI;
  private String phoneNumber;

  public TypePhoneCustomer(TypePhone typePhone, Integer customerDNI, String phoneNumber) {
    super();
    this.typePhone = typePhone;
    this.customerDNI = customerDNI;
    this.phoneNumber = phoneNumber;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((customerDNI == null) ? 0 : customerDNI.hashCode());
    result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
    result = prime * result + ((typePhone == null) ? 0 : typePhone.hashCode());
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
    TypePhoneCustomer other = (TypePhoneCustomer) obj;
    if (customerDNI == null) {
      if (other.customerDNI != null)
        return false;
    } else if (!customerDNI.equals(other.customerDNI))
      return false;
    if (phoneNumber == null) {
      if (other.phoneNumber != null)
        return false;
    } else if (!phoneNumber.equals(other.phoneNumber))
      return false;
    if (typePhone == null) {
      if (other.typePhone != null)
        return false;
    } else if (!typePhone.equals(other.typePhone))
      return false;
    return true;
  }

  public TypePhone getTypePhone() {
    return typePhone;
  }

  public void setTypePhone(TypePhone typePhone) {
    this.typePhone = typePhone;
  }

  public Integer getCustomerDNI() {
    return customerDNI;
  }

  public void setCustomerDNI(Integer customerDNI) {
    this.customerDNI = customerDNI;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String toString() {
    return "" + phoneNumber;
  }
}
