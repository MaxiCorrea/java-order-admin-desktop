package entities;

public class Address {

  private Integer id;
  private String street;
  private Short streetNumber;
  private Byte floor;
  private Byte flat;
  private Short CP;
  private District district;
  
  public Address(Integer id, String street, Short streetNumber, Byte floor, Byte flat, Short CP, District district)
  {
      super();
      this.id = id;
      this.street = street;
      this.streetNumber = streetNumber;
      this.floor = floor;
      this.flat = flat;
      this.CP = CP;
      this.district = district;
  }
  public Address(String street, Short streetNumber, Byte floor, Byte flat, Short CP, District district)
  {
      super();
      this.street = street;
      this.streetNumber = streetNumber;
      this.floor = floor;
      this.flat = flat;
      this.CP = CP;
      this.district = district;
  }

  public Integer getId()
  {
      return id;
  }

  public void setId(Integer id)
  {
      this.id = id;
  }

  public String getStreet()
  {
      return street;
  }

  public void setStreet(String street)
  {
      this.street = street;
  }

  public Short getStreetNumber()
  {
      return streetNumber;
  }

  public void setStreetNumber(Short streetNumber)
  {
      this.streetNumber = streetNumber;
  }

  public Byte getFloor()
  {
      return floor;
  }

  public void setFloor(Byte floor)
  {
      this.floor = floor;
  }

  public Byte getFlat()
  {
      return flat;
  }

  public void setFlat(Byte flat)
  {
      this.flat = flat;
  }

  public Short getCP()
  {
      return CP;
  }

  public void setCP(Short cP)
  {
      CP = cP;
  }

  public District getDistrict()
  {
      return district;
  }

  public void setDistrict(District district)
  {
      this.district = district;
  }
  
  public String toString()
  {
	  return (street+" "+streetNumber+" Piso: "+floor+" Dpto: "+flat+", "+district.getName()+", "+district.getProvince().getName()+".");
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((CP == null) ? 0 : CP.hashCode());
    result = prime * result + ((district == null) ? 0 : district.hashCode());
    result = prime * result + ((flat == null) ? 0 : flat.hashCode());
    result = prime * result + ((floor == null) ? 0 : floor.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((street == null) ? 0 : street.hashCode());
    result = prime * result + ((streetNumber == null) ? 0 : streetNumber.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Address other = (Address) obj;
    if (CP == null) {
      if (other.CP != null) return false;
    } else if (!CP.equals(other.CP)) return false;
    if (district == null) {
      if (other.district != null) return false;
    } else if (!district.equals(other.district)) return false;
    if (flat == null) {
      if (other.flat != null) return false;
    } else if (!flat.equals(other.flat)) return false;
    if (floor == null) {
      if (other.floor != null) return false;
    } else if (!floor.equals(other.floor)) return false;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (street == null) {
      if (other.street != null) return false;
    } else if (!street.equals(other.street)) return false;
    if (streetNumber == null) {
      if (other.streetNumber != null) return false;
    } else if (!streetNumber.equals(other.streetNumber)) return false;
    return true;
  }
    
}
