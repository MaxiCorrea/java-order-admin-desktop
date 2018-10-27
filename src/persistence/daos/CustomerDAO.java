package persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Address;
import entities.Customer;
import entities.TypePhoneCustomer;
import model.admins.AddressAdmin;
import persistence.connection.DataBaseConnection;

public class CustomerDAO {

	private static final String insert = "INSERT INTO tp2.Cliente(DNI,Nombre,Apellido,Domicilio_idDomicilio) VALUES(?,?,?,?)";
	private static final String update = "UPDATE tp2.Cliente set Nombre=?,Apellido=?,Domicilio_idDomicilio=? WHERE DNI = ?";
	private static final String delete = "DELETE FROM tp2.Cliente WHERE DNI = ?";
	private static final String readall = "SELECT * FROM tp2.Cliente";
	private static final String readByDNI = "SELECT * FROM tp2.Cliente WHERE DNI=?";
	private static final String readByName = "SELECT * FROM tp2.Cliente WHERE Nombre=?";
	private static final String readBySurname = "SELECT * FROM tp2.Cliente WHERE Apellido=?";
	private static final String readByNameAndSurname = "SELECT * FROM tp2.Cliente WHERE Name=? and Surname=?";
	private static final Connection sqlConection = DataBaseConnection.getConexion();

	public boolean insert(Customer cliente) {
		try
		{
			//Datos complementarios de cliente
			AddressAdmin addressAdmin=new AddressAdmin();
			addressAdmin.createAddress(cliente.getAddress());
			
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(insert);
			statement.setInt(1, cliente.getDni());
			statement.setString(2, cliente.getNombre());
			statement.setString(3, cliente.getApellido());
			Integer idDomicilio=AddressDAO.findAddress(cliente.getAddress());
			statement.setInt(4, idDomicilio);
			int rowsAffected=statement.executeUpdate();
			TypePhoneCustomerDAO typePhoneCustomerDao= new TypePhoneCustomerDAO();
			typePhoneCustomerDao.insert(new TypePhoneCustomer(TypePhoneDAO.readById((byte)1), cliente.getDni(), cliente.getLandLine()));
			typePhoneCustomerDao.insert(new TypePhoneCustomer(TypePhoneDAO.readById((byte)2), cliente.getDni(), cliente.getCellPhone()));
			System.out.println("Se han agregado "+rowsAffected+" cliente");
			return true;
		}catch(SQLException e)
		{
			System.out.println("Error en CustomerDAO.insert() "+e);
			return false;
		}
	}
	public boolean update(Customer nuevoCliente)
	{
		try
		{
			//Datos complementarios de cliente
			AddressDAO addressDao=new AddressDAO();
			addressDao.update(nuevoCliente.getAddress());
			
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(update);
			statement.setString(1, nuevoCliente.getNombre());
			statement.setString(2, nuevoCliente.getApellido());
			statement.setInt(3, nuevoCliente.getAddress().getId());
			statement.setInt(4, nuevoCliente.getDni());
			int rowsAffected=statement.executeUpdate();
			TypePhoneCustomerDAO typePhoneCustomerDao= new TypePhoneCustomerDAO();
			typePhoneCustomerDao.update(new TypePhoneCustomer(TypePhoneDAO.readById((byte)1), nuevoCliente.getDni(), nuevoCliente.getLandLine()));
			typePhoneCustomerDao.update(new TypePhoneCustomer(TypePhoneDAO.readById((byte)2), nuevoCliente.getDni(), nuevoCliente.getCellPhone()));
			System.out.println("Se han modificado "+rowsAffected+" cliente");
			return true;
		}catch(SQLException e)
		{
			System.out.println("Error en CustomerDAO.update() "+e);
			return false;
		}
	}


	public boolean delete(Customer clienteAeliminar)
	{
		try
		{
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(delete);
			statement.setString(1, Integer.toString(clienteAeliminar.getDni()));
			int rowsAffected= statement.executeUpdate();
			AddressDAO addressDAO=new AddressDAO();
			addressDAO.delete(clienteAeliminar.getAddress());
			System.out.println("Se han eliminado "+rowsAffected+" cliente");
			return true;
		}catch(SQLException e)
		{
			System.out.println("Error en CustomerDAO.delete() "+e);
			return false;
		}
	}

	public List<Customer> readAll() {
		try
		{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readall);
			resultSet = statement.executeQuery();
			AddressDAO addresDao = new AddressDAO();

			List<Customer> clientes = new ArrayList<Customer>();
			while (resultSet.next()) {
				Integer dni = resultSet.getInt("DNI");
				String nombre = resultSet.getString("Nombre");
				String apellido = resultSet.getString("Apellido");
				Integer idDomicilio = resultSet.getInt("Domicilio_idDomicilio");
				Address domicilioDeCiente = addresDao.readById(idDomicilio);
				String phone1 = TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI((byte) 1, dni);
				String phone2 = TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI((byte) 2, dni);
				clientes.add(new Customer(dni, nombre, apellido, domicilioDeCiente, phone1, phone2));
			}
			return clientes;
		}catch(SQLException e)
		{
			System.out.println("Error en CustomerDAO.readAll() "+e);
			return null;
		}
	}

	public static Customer readByDNI(Integer dni) {
		try
		{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readByDNI);
			statement.setInt(1, dni);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				// Obtengo datos del cliente
				Integer dni_ = resultSet.getInt("DNI");
				String nombre = resultSet.getString("Nombre");
				String apellido = resultSet.getString("Apellido");
				Integer idDomicilio = resultSet.getInt("Domicilio_idDomicilio");
				// Obtengo datos del Domicilio del Cliente y Telefono
				AddressDAO addresDao = new AddressDAO();
				Address domicilioDeCiente = addresDao.readById(idDomicilio);
				String phone1 = TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI((byte) 1, dni);
				String phone2 = TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI((byte) 2, dni);
				return (new Customer(dni_, nombre, apellido, domicilioDeCiente, phone1, phone2));
			}else{
				return null;
			}
		}catch(SQLException e)
		{
			System.out.println("Error en CustomerDAO.readByDNI() "+e);
			return null;
		}
	}
	public static Customer readByName(String name) {
		try
		{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readByName);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			// Obtengo datos del cliente
			Integer dni_ = resultSet.getInt("DNI");
			String nombre = resultSet.getString("Nombre");
			String apellido = resultSet.getString("Apellido");
			Integer idDomicilio = resultSet.getInt("Domicilio_idDomicilio");
			// Obtengo datos del Domicilio del Cliente y Telefono
			AddressDAO addresDao = new AddressDAO();
			Address domicilioDeCiente = addresDao.readById(idDomicilio);
			String phone1 = TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI((byte) 1, dni_);
			String phone2 = TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI((byte) 2, dni_);
			return (new Customer(dni_, nombre, apellido, domicilioDeCiente, phone1, phone2));
		}catch(SQLException e)
		{
			System.out.println("Error en CustomerDAO.readByName() "+e);
			return null;
		}
	}
	public static Customer readBySurname(String surname) {
		try
		{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readBySurname);
			statement.setString(1, surname);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				// Obtengo datos del cliente
				Integer dni_ = resultSet.getInt("DNI");
				String nombre = resultSet.getString("Nombre");
				String apellido = resultSet.getString("Apellido");
				Integer idDomicilio = resultSet.getInt("Domicilio_idDomicilio");
				// Obtengo datos del Domicilio del Cliente y Telefono
				AddressDAO addresDao = new AddressDAO();
				Address domicilioDeCiente = addresDao.readById(idDomicilio);
				String phone1 = TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI((byte) 1, dni_);
				String phone2 = TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI((byte) 2, dni_);
				return (new Customer(dni_, nombre, apellido, domicilioDeCiente, phone1, phone2));
			}else{
				return null;
			}	
		}catch(SQLException e)
		{
			System.out.println("Error en CustomerDAO.readBySurname() "+e);
			return null;
		}
	}
	public static Customer readByNameAndSurname(String name,String surname) {
		try
		{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readByNameAndSurname);
			statement.setString(1, name);
			statement.setString(2, surname);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				// Obtengo datos del cliente
				Integer dni_ = resultSet.getInt("DNI");
				String nombre = resultSet.getString("Nombre");
				String apellido = resultSet.getString("Apellido");
				Integer idDomicilio = resultSet.getInt("Domicilio_idDomicilio");
				// Obtengo datos del Domicilio del Cliente y Telefono
				AddressDAO addresDao = new AddressDAO();
				Address domicilioDeCiente = addresDao.readById(idDomicilio);
				String phone1 = TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI((byte) 1, dni_);
				String phone2 = TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI((byte) 2, dni_);
				return (new Customer(dni_, nombre, apellido, domicilioDeCiente, phone1, phone2));
			}else{
				return null;
			}
		}catch(SQLException e)
		{
			System.out.println("Error en CustomerDAO.readByNameAndSurmane() "+e);
			return null;
		}
	}
}
