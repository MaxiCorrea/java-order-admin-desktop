package model.admins;

import entities.TypePhoneCustomer;
import model.interfaces.TypePhoneCustomerAdminInterface;
import persistence.daos.TypePhoneCustomerDAO;

public class TypePhoneCustomerAdmin implements TypePhoneCustomerAdminInterface{

	private TypePhoneCustomerDAO typePhoneCustomerDAO;
	  
	  public TypePhoneCustomerAdmin() {
		  typePhoneCustomerDAO = new TypePhoneCustomerDAO();
	  }
	  
	@Override
	public boolean createTypePhoneCustomer(TypePhoneCustomer typePhoneCustomer) {
		String phone=TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI(typePhoneCustomer.getTypePhone().getId(),typePhoneCustomer.getCustomerDNI());
		if(phone==null){
			typePhoneCustomerDAO.insert(typePhoneCustomer);
			return true;
		}else{
			System.out.println("El telefono ya existe en la DB");
			return false;
		}
	}

	@Override
	public boolean updateTypePhoneCustomer(TypePhoneCustomer typePhoneCustomer) {
		return typePhoneCustomerDAO.update(typePhoneCustomer);
	}

	@Override
	public boolean deleteTypePhoneCustomer(TypePhoneCustomer typePhoneCustomer) {
		String phone=TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI(typePhoneCustomer.getTypePhone().getId(),typePhoneCustomer.getCustomerDNI());
		if(phone!=null){
			typePhoneCustomerDAO.delete(typePhoneCustomer);
			return true;
		}else{
			System.out.println("El telefono que intenta eliminar NO existe en la DB");
			return false;
		}
	}

	@Override
	public String readTypePhoneCustomer(Byte idTypePhone, Integer CustomerDNI) {
		return TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI(idTypePhone, CustomerDNI);
	}
}
