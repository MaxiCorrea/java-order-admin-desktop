package model.admins;

import java.util.List;

import entities.Address;
import model.interfaces.AddressAdminInterface;
import persistence.daos.AddressDAO;

public class AddressAdmin implements AddressAdminInterface {

	private AddressDAO addressDAO;
	
	public AddressAdmin() {
		super();
		this.addressDAO = new AddressDAO();
	}

	@Override
	public boolean createAddress(Address address) {
		Integer alreadyExists=AddressDAO.findAddress(address);
		if(alreadyExists==null)
		{
			return addressDAO.insert(address);
		}else
		{
			System.out.println("El domicilio ya existe en la DB, su id es:"+alreadyExists);
			return false;
		}
	}

	@Override
	public boolean editAddress(Address address) {
		return addressDAO.update(address);
	}

	@Override
	public boolean deleteAddress(Address addressAeliminar) {
		return addressDAO.delete(addressAeliminar);
	}

	@Override
	public List<Address> readAllAddress() {
		return addressDAO.readAll();
	}

	@Override
	public Address readById(Integer idDomicilio) {
		return addressDAO.readById(idDomicilio);
	}

}
