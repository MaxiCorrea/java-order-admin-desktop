package entities;

import java.math.BigDecimal;

public class ProductOrder {

  private Product product;
  private Long orderId;
  private BigDecimal price;
  private int quantity;

  public ProductOrder(Product product, Long orderId, BigDecimal price, int quantity) {
    super();
    this.product = product;
    this.orderId = orderId;
    this.price = price;
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product productCode) {
    this.product = productCode;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
    result = prime * result + ((price == null) ? 0 : price.hashCode());
    result = prime * result + ((product == null) ? 0 : product.hashCode());
    result = prime * result + quantity;
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
    ProductOrder other = (ProductOrder) obj;
    if (orderId == null) {
      if (other.orderId != null)
        return false;
    } else if (!orderId.equals(other.orderId))
      return false;
    if (price == null) {
      if (other.price != null)
        return false;
    } else if (!price.equals(other.price))
      return false;
    if (product == null) {
      if (other.product != null)
        return false;
    } else if (!product.equals(other.product))
      return false;
    if (quantity != other.quantity)
      return false;
    return true;
  }

}
