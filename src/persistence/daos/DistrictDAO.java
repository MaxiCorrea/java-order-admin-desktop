package persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.District;
import entities.Province;
import persistence.connection.DataBaseConnection;

public class DistrictDAO {

	private static final String insert = "INSERT INTO tp2.Localidad(Nombre, Provincia_idProvincia) VALUES(?,?)";
	private static final String update = "UPDATE tp2.Localidad SET Nombre = ?, Provincia_idProvincia=? WHERE idLocalidad = ?";
	private static final String delete = "DELETE FROM tp2.Localidad WHERE Nombre = ? AND Provincia_idProvincia=?";
	private static final String readall = "SELECT * FROM tp2.Localidad";
	private static final String readById = "SELECT * FROM tp2.Localidad WHERE idLocalidad=?";
	private static final String readByName = "SELECT * FROM tp2.Localidad WHERE Nombre=?";
	private static final Connection sqlConection = DataBaseConnection.getConexion();

	public boolean insert(String districtName, Province province) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(insert);
			statement.setString(1, districtName);
			statement.setShort(2, province.getId());
			int rowsAffeted = statement.executeUpdate();
			System.out.println("Se han insertado " + rowsAffeted + " localidad/es");
			return true;
		} catch (SQLException e) {
			System.out.println("Error en DistrictDAO.insert() " + e);
			return false;
		}
	}

	public boolean delete(District localidadAeliminar) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(delete);
			statement.setString(1, localidadAeliminar.getName());
			statement.setInt(2, localidadAeliminar.getProvince().getId());
			int rowsAffeted = statement.executeUpdate();
			System.out.println("Se han eliminado " + rowsAffeted + " localidad/es");
			return true;
		} catch (SQLException sqlE) {
			System.out.println("Error en DistrictDAO.deleteByName() " + sqlE);
			return false;
		}
	}

	public List<District> readAll() {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readall);
			resultSet = statement.executeQuery();
			List<District> localidades = new ArrayList<District>();
			while (resultSet.next()) {
				Integer idLocalidad = resultSet.getInt("idLocalidad");
				String nombre = resultSet.getString("Nombre");
				Short provincia_idProvincia = resultSet.getShort("Provincia_idProvincia");
				Province provincia = ProvinceDAO.readById(provincia_idProvincia);
				localidades.add(new District(idLocalidad, nombre, provincia));
			}
			return localidades;
		} catch (SQLException e) {
			System.out.println("Error en DistrictDAO.readALL() " + e);
			return null;
		}
	}

	public static District readById(Integer id) {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readById);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				Integer idLocalidad = resultSet.getInt("idLocalidad");
				String nombre = resultSet.getString("Nombre");
				Short provincia_idProvincia = resultSet.getShort("Provincia_idProvincia");
				Province provincia = ProvinceDAO.readById(provincia_idProvincia);
				return (new District(idLocalidad, nombre, provincia));
			}else{
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error en DistrictDAO.radById() " + e);
			return null;
		}
	}

	public boolean update(District nuevaLocalidad) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(update);
			statement.setString(1, nuevaLocalidad.getName());
			statement.setShort(2, nuevaLocalidad.getProvince().getId());
			statement.setInt(3, nuevaLocalidad.getId());
			int rowsAffeted = statement.executeUpdate();
			System.out.println("Se han modificado " + rowsAffeted + " localidad/es");
			return true;
		} catch (SQLException e) {
			System.out.println("Error en DistrictDAO.update() " + e);
			return false;
		}
	}

	public District readByName(String districtName) {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readByName);
			statement.setString(1, districtName);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				Integer idLocalidad = resultSet.getInt("idLocalidad");
				String nombre = resultSet.getString("Nombre");
				Short provincia_idProvincia = resultSet.getShort("Provincia_idProvincia");
				Province provincia = ProvinceDAO.readById(provincia_idProvincia);
				return (new District(idLocalidad, nombre, provincia));
			}else{
				return null;
			}
		} catch (SQLException sqlE) {
			System.out.println("Error en DistrictDAO.readByName() " + sqlE);
			return null;
		}
	}

}
