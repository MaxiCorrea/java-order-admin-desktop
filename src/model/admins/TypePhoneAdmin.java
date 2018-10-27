package model.admins;

import java.util.ArrayList;
import java.util.List;

import entities.TypePhone;
import model.interfaces.TypePhoneAdminInterface;
import persistence.daos.TypePhoneDAO;

public class TypePhoneAdmin implements TypePhoneAdminInterface {

  private TypePhoneDAO typePhoneDAO;
  
  public TypePhoneAdmin() {
    typePhoneDAO = new TypePhoneDAO();
  }
  
  @Override
  public List<TypePhone> reallAllTypePhones() {
    try {
      return typePhoneDAO.readAll();
    } catch(Exception ex) {
      ex.printStackTrace();
      return new ArrayList<TypePhone>();
    }
  }

}
