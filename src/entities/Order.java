package entities;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Order {

  private Long id;
  private Time hora;
  private BigDecimal importeTotal;
  private Integer cantidadProducto;
  private Customer cliente;
  private Address domicilioEntrega;
  private Date fecha;
  private PaymentMethod formaDePago;
  private OrderStatus estadoDePedido;
  private TimeZone franjaHoraria;
  private List<ProductOrder> productsOrder;

  public Order(Long id, Time hora, BigDecimal importeTotal, Integer cantidadProducto,
      Customer cliente, Address domicilioEntrega, Date fecha, PaymentMethod formaDePago,
      OrderStatus estadoDePedido, TimeZone franjaHoraria) {
    super();
    this.id = id;
    this.hora = hora;
    this.importeTotal = importeTotal;
    this.cantidadProducto = cantidadProducto;
    this.cliente = cliente;
    this.domicilioEntrega = domicilioEntrega;
    this.fecha = fecha;
    this.formaDePago = formaDePago;
    this.estadoDePedido = estadoDePedido;
    this.franjaHoraria = franjaHoraria;
    productsOrder = new ArrayList<>();
  }

  public Order(Long id, Time hora, BigDecimal importeTotal, Customer cliente,
      Address domicilioEntrega, Date fecha, PaymentMethod formaDePago, OrderStatus estadoDePedido,
      TimeZone franjaHoraria) {
    super();
    this.id = id;
    this.hora = hora;
    this.importeTotal = importeTotal;
    this.cliente = cliente;
    this.domicilioEntrega = domicilioEntrega;
    this.fecha = fecha;
    this.formaDePago = formaDePago;
    this.estadoDePedido = estadoDePedido;
    this.franjaHoraria = franjaHoraria;
    productsOrder = new ArrayList<>();
  }

  public void setProductOrder(List<ProductOrder> productsOrder) {
    this.productsOrder = productsOrder;
  }

  public List<ProductOrder> getProductsOrder() {
    return productsOrder;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Time getHora() {
    return hora;
  }

  public void setHora(Time hora) {
    this.hora = hora;
  }

  public BigDecimal getImporteTotal() {
    return importeTotal;
  }

  public void setImporteTotal(BigDecimal importeTotal) {
    this.importeTotal = importeTotal;
  }

  public Integer getCantidadProducto() {
    return cantidadProducto;
  }

  public void setCantidadProducto(Integer cantidadProducto) {
    this.cantidadProducto = cantidadProducto;
  }

  public Customer getCliente() {
    return cliente;
  }

  public void setCliente(Customer cliente) {
    this.cliente = cliente;
  }

  public Address getDomicilioEntrega() {
    return domicilioEntrega;
  }

  public void setDomicilioEntrega(Address domicilioEntrega) {
    this.domicilioEntrega = domicilioEntrega;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public PaymentMethod getFormaDePago() {
    return formaDePago;
  }

  public void setFormaDePago(PaymentMethod formaDePago) {
    this.formaDePago = formaDePago;
  }

  public OrderStatus getEstadoDePedido() {
    return estadoDePedido;
  }

  public void setEstadoDePedido(OrderStatus estadoDePedido) {
    this.estadoDePedido = estadoDePedido;
  }

  public TimeZone getFranjaHoraria() {
    return franjaHoraria;
  }

  public void setFranjaHoraria(TimeZone franjaHoraria) {
    this.franjaHoraria = franjaHoraria;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cantidadProducto == null) ? 0 : cantidadProducto.hashCode());
    result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
    result = prime * result + ((domicilioEntrega == null) ? 0 : domicilioEntrega.hashCode());
    result = prime * result + ((estadoDePedido == null) ? 0 : estadoDePedido.hashCode());
    result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
    result = prime * result + ((formaDePago == null) ? 0 : formaDePago.hashCode());
    result = prime * result + ((franjaHoraria == null) ? 0 : franjaHoraria.hashCode());
    result = prime * result + ((hora == null) ? 0 : hora.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((importeTotal == null) ? 0 : importeTotal.hashCode());
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
    Order other = (Order) obj;
    if (cantidadProducto == null) {
      if (other.cantidadProducto != null)
        return false;
    } else if (!cantidadProducto.equals(other.cantidadProducto))
      return false;
    if (cliente == null) {
      if (other.cliente != null)
        return false;
    } else if (!cliente.equals(other.cliente))
      return false;
    if (domicilioEntrega == null) {
      if (other.domicilioEntrega != null)
        return false;
    } else if (!domicilioEntrega.equals(other.domicilioEntrega))
      return false;
    if (estadoDePedido == null) {
      if (other.estadoDePedido != null)
        return false;
    } else if (!estadoDePedido.equals(other.estadoDePedido))
      return false;
    if (fecha == null) {
      if (other.fecha != null)
        return false;
    } else if (!fecha.equals(other.fecha))
      return false;
    if (formaDePago == null) {
      if (other.formaDePago != null)
        return false;
    } else if (!formaDePago.equals(other.formaDePago))
      return false;
    if (franjaHoraria == null) {
      if (other.franjaHoraria != null)
        return false;
    } else if (!franjaHoraria.equals(other.franjaHoraria))
      return false;
    if (hora == null) {
      if (other.hora != null)
        return false;
    } else if (!hora.equals(other.hora))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (importeTotal == null) {
      if (other.importeTotal != null)
        return false;
    } else if (!importeTotal.equals(other.importeTotal))
      return false;
    return true;
  }

  public void setIdFecha(Integer idFecha) {
    this.fecha.setId(idFecha);
  }

}
