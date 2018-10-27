package entities;

public class Date {

  private Integer id;
  private Byte day;
  private Byte month;
  private Short year;
  private String dayName;

  public Date(Integer id, Byte day, Byte month, Short year, String dayName) {
    super();
    this.id = id;
    this.day = day;
    this.month = month;
    this.year = year;
    this.dayName = dayName;
  }

  public Date(Byte day, Byte month, Short year, String dayName) {
    super();
    this.day = day;
    this.month = month;
    this.year = year;
    this.dayName = dayName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Byte getDay() {
    return day;
  }

  public void setDay(Byte day) {
    this.day = day;
  }

  public Byte getMonth() {
    return month;
  }

  public void setMonth(Byte month) {
    this.month = month;
  }

  public Short getYear() {
    return year;
  }

  public void setYear(Short year) {
    this.year = year;
  }

  public String getDayName() {
    return dayName;
  }

  public void setDayName(String dayName) {
    this.dayName = dayName;
  }

  @Override
  public String toString() {
    return (day + "/" + month + "/" + year);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((day == null) ? 0 : day.hashCode());
    result = prime * result + ((dayName == null) ? 0 : dayName.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((month == null) ? 0 : month.hashCode());
    result = prime * result + ((year == null) ? 0 : year.hashCode());
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
    Date other = (Date) obj;
    if (day == null) {
      if (other.day != null)
        return false;
    } else if (!day.equals(other.day))
      return false;
    if (dayName == null) {
      if (other.dayName != null)
        return false;
    } else if (!dayName.equals(other.dayName))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (month == null) {
      if (other.month != null)
        return false;
    } else if (!month.equals(other.month))
      return false;
    if (year == null) {
      if (other.year != null)
        return false;
    } else if (!year.equals(other.year))
      return false;
    return true;
  }

}
