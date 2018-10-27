package model.interfaces;

import java.util.List;

import entities.Customer;

public interface CustomerAdminInterface {

	boolean createCustomer(Customer customer);

	boolean editCustomer(Customer customer);

	boolean deleteCustomer(Customer customer);
	
	Customer readByDNI(Integer dni);

	List<Customer> readAllCustomer();

	Customer readByName(String name);
	
	Customer readBySurname(String surname);
	
	Customer readByNameAndSurname(String name,String surname);

}
