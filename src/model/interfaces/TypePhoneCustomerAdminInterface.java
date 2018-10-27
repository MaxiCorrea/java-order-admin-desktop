package model.interfaces;

import entities.TypePhoneCustomer;

public interface TypePhoneCustomerAdminInterface {

	public boolean createTypePhoneCustomer(TypePhoneCustomer typePhoneCustomer);
	public boolean updateTypePhoneCustomer(TypePhoneCustomer typePhoneCustomer);
	public boolean deleteTypePhoneCustomer(TypePhoneCustomer typePhoneCustomer);
	public String readTypePhoneCustomer(Byte idTypePhone, Integer CustomerDNI);
}
