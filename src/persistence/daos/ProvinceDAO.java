package persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Province;
import persistence.connection.DataBaseConnection;

public class ProvinceDAO {

	private static final String insert = "INSERT INTO tp2.Provincia(Nombre) VALUES(?)";
	private static final String update = "UPDATE tp2.Provincia set Nombre= ? WHERE idProvincia = ?";
	private static final String deleteByName = "DELETE FROM tp2.Provincia WHERE Provincia.Nombre = ?";
	private static final String deleteById = "DELETE FROM tp2.Provincia WHERE Provincia.idProvincia = ?";
	private static final String readall = "SELECT * FROM Provincia";
	private static final String readById = "SELECT * FROM tp2.Provincia WHERE Provincia.idProvincia=?";
	private static final String readByName = "SELECT * FROM tp2.Provincia WHERE Provincia.Nombre=?";
	private static final Connection sqlConection = DataBaseConnection.getConexion();

	public boolean insert(Province province) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(insert);
			statement.setString(1, province.getName());
			int rowsAffeted = statement.executeUpdate();
			System.out.println("Se han insertado " + rowsAffeted + " provincia/s");
			return true;
		} catch (SQLException sqlE) {
			System.out.println("Error en ProvinceDAO.insert() " + sqlE);
			return false;
		}

	}

	public boolean deleteByName(Province provinceAeliminar) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(deleteByName);
			statement.setString(1, provinceAeliminar.getName());
			int rowsAffeted = statement.executeUpdate();
			System.out.println("Se han modificado " + rowsAffeted + " provincia/s");
			return true;
		} catch (SQLException sqlE) {
			System.out.println("Error en ProvinceDAO.deleteByName() " + sqlE);
			return false;
		}
	}

	public boolean deleteById(Province provinceAeliminar) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(deleteById);
			statement.setInt(1, provinceAeliminar.getId());
			int rowsAffeted = statement.executeUpdate();
			System.out.println("Se han eliminado " + rowsAffeted + " provincia/s");
			return true;
		} catch (SQLException sqlE) {
			System.out.println("Error en ProvinceDAO.deleteById() " + sqlE);
			return false;
		}
	}

	public List<Province> readAll() {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readall);
			resultSet = statement.executeQuery();

			List<Province> provinces = new ArrayList<Province>();
			while (resultSet.next()) {
				Short idProvince = resultSet.getShort("idProvincia");
				String nombre = resultSet.getString("Nombre");
				provinces.add(new Province(idProvince, nombre));
			}
			return provinces;
		} catch (SQLException sqlE) {
			System.out.println("Error en ProvinceDAO.readAll() " + sqlE);
			return null;
		}
	}

	public static Province readById(Short idProvincia) {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readById);
			statement.setShort(1, idProvincia);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				Short idProvince = resultSet.getShort("idProvincia");
				String nombre = resultSet.getString("Nombre");
				return (new Province(idProvince, nombre));
			}else{
				return null;
			}
			
		} catch (SQLException sqlE) {
			System.out.println("Error en Province.readById() " + sqlE);
			return null;
		}
	}

	public Province readByName(String name) {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readByName);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				Short idProvince = resultSet.getShort("idProvincia");
				String nombre = resultSet.getString("Nombre");
				return (new Province(idProvince, nombre));
			}else{
				return null;
			}
		} catch (SQLException sqlE) {
			System.out.println("Error en ProvinceDAO.readByName() " + sqlE);
			return null;
		}
	}

	public boolean update(Province nuevaProvincia) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(update);
			statement.setString(1, nuevaProvincia.getName());
			statement.setShort(2, nuevaProvincia.getId());
			statement.executeUpdate();
			return true;
		} catch (SQLException sqlE) {
			System.out.println("Error en ProvinciaDAO.update() " + sqlE);
			return false;
		}
	}

}
