package entities;

public class MeasurementUnit {

  private Short id;
  private String unit;

  public MeasurementUnit(String unit) {
    super();
    this.unit = unit;
  }

  public MeasurementUnit(Short id, String unit) {
    super();
    this.id = id;
    this.unit = unit;
  }

  public Short getId() {
    return id;
  }

  public void setId(Short id) {
    this.id = id;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  @Override
  public String toString() {
    return this.unit;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
    MeasurementUnit other = (MeasurementUnit) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (unit == null) {
      if (other.unit != null)
        return false;
    } else if (!unit.equals(other.unit))
      return false;
    return true;
  }

}
