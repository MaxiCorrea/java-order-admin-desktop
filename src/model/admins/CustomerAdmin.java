package model.admins;

import java.util.ArrayList;
import java.util.List;
import entities.Customer;
import model.interfaces.CustomerAdminInterface;
import persistence.daos.CustomerDAO;

public class CustomerAdmin implements CustomerAdminInterface {

	private CustomerDAO customerDAO;

	public CustomerAdmin() {
		customerDAO = new CustomerDAO();
	}

	@Override
	public boolean createCustomer(Customer customer) {
		return customerDAO.insert(customer);
	}

	@Override
	public boolean editCustomer(Customer customer) {
		return customerDAO.update(customer);
	}

	@Override
	public boolean deleteCustomer(Customer customer) {
		return customerDAO.delete(customer);
	}

	@Override
	public List<Customer> readAllCustomer() {
		try {
			return customerDAO.readAll();
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<Customer>();
		}
	}

	@Override
	public Customer readByDNI(Integer dni) {
		try {
			return CustomerDAO.readByDNI(dni);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Customer readByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer readBySurname(String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer readByNameAndSurname(String name, String surname) {
		// TODO Auto-generated method stub
		return null;
	}

}
