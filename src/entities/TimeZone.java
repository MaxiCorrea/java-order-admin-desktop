package entities;

public class TimeZone {

  private Short id;
  private String timeZone;

  public TimeZone(Short id, String zone) {
    this.id = id;
    this.timeZone = zone;
  }

  public void setId(Short id) {
    this.id = id;
  }

  public Short getId() {
    return id;
  }

  public void setZone(String zone) {
    this.timeZone = zone;
  }

  public String getZone() {
    return timeZone;
  }

  @Override
  public String toString() {
    return this.timeZone;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((timeZone == null) ? 0 : timeZone.hashCode());
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
    TimeZone other = (TimeZone) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (timeZone == null) {
      if (other.timeZone != null)
        return false;
    } else if (!timeZone.equals(other.timeZone))
      return false;
    return true;
  }

}
