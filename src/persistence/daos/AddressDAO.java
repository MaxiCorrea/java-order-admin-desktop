package persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import entities.Address;
import entities.District;
import persistence.connection.DataBaseConnection;

public class AddressDAO {

	private static final String insert = "INSERT INTO tp2.Domicilio(Calle, AlturaDomicilio, Piso, Departamento, CodigoPostal, Localidad_idLocalidad, Localidad_Provincia_idProvincia) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String update = "UPDATE tp2.Domicilio SET Calle=?, AlturaDomicilio=?, Piso=?, Departamento=?, CodigoPostal=?, Localidad_idLocalidad=?, Localidad_Provincia_idProvincia=? WHERE idDomicilio = ?";
	private static final String delete = "DELETE FROM tp2.Domicilio WHERE idDomicilio = ?";
	private static final String readall = "SELECT * FROM tp2.Domicilio";
	private static final String readById = "SELECT * FROM tp2.Domicilio WHERE Domicilio.idDomicilio=?";
	private static final String find = "SELECT tp2.Domicilio.idDomicilio FROM tp2.Domicilio WHERE Calle=? AND AlturaDomicilio=? AND Piso=? AND Departamento=? AND CodigoPostal=? AND Localidad_idLocalidad=? AND Localidad_Provincia_idProvincia=?";
	private static final String findWithoutFloor = "SELECT tp2.Domicilio.idDomicilio FROM tp2.Domicilio WHERE Calle=? AND AlturaDomicilio=? AND Departamento=? AND CodigoPostal=? AND Localidad_idLocalidad=? AND Localidad_Provincia_idProvincia=?";
	private static final String findWithoutFlat = "SELECT tp2.Domicilio.idDomicilio FROM tp2.Domicilio WHERE Calle=? AND AlturaDomicilio=? AND Piso=? AND CodigoPostal=? AND Localidad_idLocalidad=? AND Localidad_Provincia_idProvincia=?";
	private static final String findWithoutFloorAndFlat = "SELECT tp2.Domicilio.idDomicilio FROM tp2.Domicilio WHERE Calle=? AND AlturaDomicilio=? AND CodigoPostal=? AND Localidad_idLocalidad=? AND Localidad_Provincia_idProvincia=?";
	private static final Connection sqlConection = DataBaseConnection.getConexion();

	public boolean insert(Address address) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(insert);
			statement.setString(1, address.getStreet());
			statement.setShort(2, address.getStreetNumber());
			try {
				statement.setByte(3, address.getFloor());
			} catch (NullPointerException e) {
				statement.setNull(3, Types.TINYINT);
			}
			try {
				statement.setByte(4, address.getFlat());
			} catch (NullPointerException e) {
				statement.setNull(4, Types.TINYINT);
			}
			statement.setShort(5, address.getCP());
			statement.setInt(6, address.getDistrict().getId());
			statement.setShort(7, address.getDistrict().getProvince().getId());
			int rowsAffeted = statement.executeUpdate();
			System.out.println("Se han insertado " + rowsAffeted + " domicilio/s");
			return true;
		} catch (SQLException e) {
			System.out.println("Error en AddressDAO.insert() " + e);
			return false;
		}
	}

	public boolean delete(Address addressAeliminar) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(delete);
			statement.setInt(1, addressAeliminar.getId());
			int rowsAffeted = statement.executeUpdate();
			System.out.println("Se han eliminado " + rowsAffeted + " domicilio/s");
			return true;
		} catch (SQLException e) {
			System.out.println("Error en AddressDAO.delete() " + e);
			return false;
		}
	}

	public List<Address> readAll() {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readall);
			resultSet = statement.executeQuery();

			List<Address> address = new ArrayList<Address>();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("idDomicilio");
				String street = resultSet.getString("Calle");
				Short streetNumber = resultSet.getShort("AlturaDomicilio");
				Byte floor = resultSet.getByte("Piso");
				Byte flat = resultSet.getByte("Departamento");
				Short CP = resultSet.getShort("CodigoPostal");
				Integer idLocaliad = resultSet.getInt("Localidad_idLocalidad");
				District district = DistrictDAO.readById(idLocaliad);
				address.add(new Address(id, street, streetNumber, floor, flat, CP, district));
			}
			return address;
		} catch (SQLException e) {
			System.out.println("Error en AddressDAO.readAll() " + e);
			return null;
		}
	}

	public Address readById(Integer idDomicilio) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(readById);
			statement.setString(1, Integer.toString(idDomicilio));
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				Integer id = resultSet.getInt("idDomicilio");
				String street = resultSet.getString("Calle");
				Short streetNumber = resultSet.getShort("AlturaDomicilio");
				Byte floor = resultSet.getByte("Piso");
				Byte flat = resultSet.getByte("Departamento");
				Short CP = resultSet.getShort("CodigoPostal");
				Integer idLocaliad = resultSet.getInt("Localidad_idLocalidad");
				District district = DistrictDAO.readById(idLocaliad);
				return new Address(id, street, streetNumber, floor, flat, CP, district);
			}else{
				return null;
			}
			
		} catch (SQLException e) {
			System.out.println("Error en AddressDAO.readById() " + e);
			return null;
		}
	}

	public static Integer findAddress(Address address) {
		Integer idDomicilio = null;
		try {
			PreparedStatement statement;
			if (address.getFloor() == null || address.getFlat() == null) {
				if (address.getFloor() == null && address.getFlat() == null) {
					statement = sqlConection.prepareStatement(findWithoutFloorAndFlat);
					statement.setString(1, address.getStreet());
					statement.setShort(2, address.getStreetNumber());
					statement.setShort(3, address.getCP());
					statement.setInt(4, address.getDistrict().getId());
					statement.setShort(5, address.getDistrict().getProvince().getId());
					ResultSet resultSet = statement.executeQuery();
					if(resultSet.next()){
						idDomicilio = resultSet.getInt("idDomicilio");
						return idDomicilio;
					}else{
						return null;
					}
				} else if (address.getFloor() == null && address.getFlat() != null) {
					statement = sqlConection.prepareStatement(findWithoutFloor);
					statement.setString(1, address.getStreet());
					statement.setShort(2, address.getStreetNumber());
					statement.setByte(3, address.getFlat());
					statement.setShort(4, address.getCP());
					statement.setInt(5, address.getDistrict().getId());
					statement.setShort(6, address.getDistrict().getProvince().getId());
					ResultSet resultSet = statement.executeQuery();
					if(resultSet.next()){
						idDomicilio = resultSet.getInt("idDomicilio");
						return idDomicilio;
					}else{
						return null;
					}
				} else if (address.getFloor() != null && address.getFlat() == null) {
					statement = sqlConection.prepareStatement(findWithoutFlat);
					statement.setString(1, address.getStreet());
					statement.setShort(2, address.getStreetNumber());
					statement.setByte(3, address.getFloor());
					statement.setShort(4, address.getCP());
					statement.setInt(5, address.getDistrict().getId());
					statement.setShort(6, address.getDistrict().getProvince().getId());
					ResultSet resultSet = statement.executeQuery();
					if(resultSet.next()){
						idDomicilio = resultSet.getInt("idDomicilio");
						return idDomicilio;
					}else{
						return null;
					}
				}
				return null;
			} else {
				statement = sqlConection.prepareStatement(find);
				statement.setString(1, address.getStreet());
				statement.setShort(2, address.getStreetNumber());
				statement.setByte(3, address.getFloor());
				statement.setByte(4, address.getFlat());
				statement.setShort(5, address.getCP());
				statement.setInt(6, address.getDistrict().getId());
				statement.setShort(7, address.getDistrict().getProvince().getId());
				ResultSet resultSet = statement.executeQuery();
				if(resultSet.next()){
					idDomicilio = resultSet.getInt("idDomicilio");
					return idDomicilio;
				}else{
					return null;
				}
			}
		} catch (SQLException e) {
			System.out.println("Error en AddressDAO.find() " + e);
			return null;
		}
	}

	public boolean update(Address address) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(update);
			statement.setString(1, address.getStreet());
			statement.setShort(2, address.getStreetNumber());
			try {
				statement.setByte(3, address.getFloor());
			} catch (NullPointerException e) {
				statement.setNull(3, Types.TINYINT);
			}
			try {
				statement.setByte(4, address.getFlat());
			} catch (NullPointerException e) {
				statement.setNull(4, Types.TINYINT);
			}
			statement.setShort(5, address.getCP());
			statement.setInt(6, address.getDistrict().getId());
			statement.setShort(7, address.getDistrict().getProvince().getId());
			statement.setInt(8, address.getId());
			int rowsAffeted = statement.executeUpdate();
			System.out.println("Se han modificado " + rowsAffeted + " domicilio/s");
			return true;
		} catch (SQLException e) {
			System.out.println("Error en AddressDAO.update() " + e);
			return false;
		}
	}

}
