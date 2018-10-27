package persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.TimeZone;
import persistence.connection.DataBaseConnection;

public class TimeZoneDAO {

	private static final String insert = "INSERT INTO tp2.FranjaHoraria(FranjaHoraria) VALUES(?)";
	private static final String update = "UPDATE tp2.FranjaHoraria set FranjaHoraria = ? WHERE idFranjaHoraria = ?";
	private static final String delete = "DELETE FROM tp2.FranjaHoraria WHERE idFranjaHoraria = ?";
	private static final String readall = "SELECT * FROM tp2.FranjaHoraria";
	private static final String readById = "SELECT * FROM FranjaHoraria WHERE idFranjaHoraria=?";
	private static final Connection sqlConection = DataBaseConnection.getConexion();

	public boolean insert(TimeZone franjaHoraria) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(insert);
			statement.setString(1, franjaHoraria.getZone());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error en TimeZoneDAO.insert() " + e);
			return false;
		}
	}

	public boolean delete(TimeZone timeZoneAeliminar) {
		try{
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(delete);
			statement.setString(1, Short.toString(timeZoneAeliminar.getId()));
			statement.executeUpdate();
			return true;
		}catch (SQLException e) {
			System.out.println("Error en TimeZoneDAO.delete() " + e);
			return false;
		}
	}

	public List<TimeZone> readAll() {
		try{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readall);
			resultSet = statement.executeQuery();

			List<TimeZone> franjasHorarias = new ArrayList<TimeZone>();
			while (resultSet.next()) {
				Short id = resultSet.getShort("idFranjaHoraria");
				String nombre = resultSet.getString("FranjaHoraria");
				franjasHorarias.add(new TimeZone(id, nombre));
			}
			return franjasHorarias;
		}catch (SQLException e) {
			System.out.println("Error en TimeZoneDAO.readAll() " + e);
			return null;
		}
	}

	public boolean update(Short id, TimeZone nuevaTimeZone) {
		try{
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(update);
			statement.setString(1, nuevaTimeZone.getZone());
			statement.setShort(2, id);
			statement.executeUpdate();
			return true;
		}catch (SQLException e) {
			System.out.println("Error en TimeZoneDAO.update() " + e);
			return false;
		}
	}

	public static TimeZone readById(short id) {
		try{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readById);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				Short idFranjaHoraria = resultSet.getShort("idFranjaHoraria");
				String nombre = resultSet.getString("FranjaHoraria");
				return (new TimeZone(idFranjaHoraria, nombre));
			}else{
				return null;
			}
		}catch (SQLException e) {
			System.out.println("Error en TimeZoneDAO.readById() " + e);
			return null;
		}
	}

}
