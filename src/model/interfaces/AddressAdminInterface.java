package model.interfaces;

import java.util.List;

import entities.Address;

public interface AddressAdminInterface {

	boolean createAddress(Address address);

	boolean editAddress(Address address);

	boolean deleteAddress(Address address);
	
	Address readById(Integer idDomicilio);

	List<Address> readAllAddress();

}
