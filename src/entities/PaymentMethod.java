package entities;

public class PaymentMethod {

  private short id;
  private String paymentMethod;

  public PaymentMethod(short id, String paymentMethod) {
    super();
    this.id = id;
    this.paymentMethod = paymentMethod;
  }

  public short getId() {
    return id;
  }

  public void setId(short id) {
    this.id = id;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  @Override
  public String toString() {
    return this.paymentMethod;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((paymentMethod == null) ? 0 : paymentMethod.hashCode());
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
    PaymentMethod other = (PaymentMethod) obj;
    if (id != other.id)
      return false;
    if (paymentMethod == null) {
      if (other.paymentMethod != null)
        return false;
    } else if (!paymentMethod.equals(other.paymentMethod))
      return false;
    return true;
  }

}
