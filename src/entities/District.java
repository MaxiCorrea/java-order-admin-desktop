package entities;

public class District {

  private Integer id;
  private String name;
  private Province province;

  public District(String name, Province province) {
    this.name = name;
    this.province = province;
  }

  public District(Integer idLocalidad, String name, Province province) {
    this.name = name;
    this.province = province;
    this.id = idLocalidad;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setProvince(Province province) {
    this.province = province;
  }

  public Province getProvince() {
    return this.province;
  }

  public String toString() {
    return this.name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((province == null) ? 0 : province.hashCode());
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
    District other = (District) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (province == null) {
      if (other.province != null)
        return false;
    } else if (!province.equals(other.province))
      return false;
    return true;
  }

}
