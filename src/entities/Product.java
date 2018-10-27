package entities;

import java.math.BigDecimal;

public class Product {

  private Long codigo;
  private String nombre;
  private String descripcion;
  private BigDecimal precioPorUnidad;
  private MeasurementUnit unidadDeMedida;
  private ProductCategory categoriaProducto;

  public Product(Long codigo, String nombre, String descripcion, BigDecimal precioPorUnidad,
      MeasurementUnit unidadDeMedida, ProductCategory categoriaProducto) {
    super();
    this.codigo = codigo;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precioPorUnidad = precioPorUnidad;
    this.unidadDeMedida = unidadDeMedida;
    this.categoriaProducto = categoriaProducto;
  }

  public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public BigDecimal getPrecioPorUnidad() {
    return precioPorUnidad;
  }

  public void setPrecioPorUnidad(BigDecimal precioPorUnidad) {
    this.precioPorUnidad = precioPorUnidad;
  }

  public MeasurementUnit getUnidadDeMedida() {
    return unidadDeMedida;
  }

  public void setUnidadDeMedida(MeasurementUnit unidadDeMedida) {
    this.unidadDeMedida = unidadDeMedida;
  }

  public ProductCategory getCategoriaProducto() {
    return categoriaProducto;
  }

  public void setCategoriaProducto(ProductCategory categoriaProducto) {
    this.categoriaProducto = categoriaProducto;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((categoriaProducto == null) ? 0 : categoriaProducto.hashCode());
    result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
    result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
    result = prime * result + ((precioPorUnidad == null) ? 0 : precioPorUnidad.hashCode());
    result = prime * result + ((unidadDeMedida == null) ? 0 : unidadDeMedida.hashCode());
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
    Product other = (Product) obj;
    if (categoriaProducto == null) {
      if (other.categoriaProducto != null)
        return false;
    } else if (!categoriaProducto.equals(other.categoriaProducto))
      return false;
    if (codigo == null) {
      if (other.codigo != null)
        return false;
    } else if (!codigo.equals(other.codigo))
      return false;
    if (descripcion == null) {
      if (other.descripcion != null)
        return false;
    } else if (!descripcion.equals(other.descripcion))
      return false;
    if (nombre == null) {
      if (other.nombre != null)
        return false;
    } else if (!nombre.equals(other.nombre))
      return false;
    if (precioPorUnidad == null) {
      if (other.precioPorUnidad != null)
        return false;
    } else if (!precioPorUnidad.equals(other.precioPorUnidad))
      return false;
    if (unidadDeMedida == null) {
      if (other.unidadDeMedida != null)
        return false;
    } else if (!unidadDeMedida.equals(other.unidadDeMedida))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return nombre;
  }

}
