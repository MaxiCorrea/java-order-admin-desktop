package persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Date;
import persistence.connection.DataBaseConnection;

public class DateDAO {

	private static final String insert = "INSERT INTO tp2.Fecha(Dia, Mes, Anio, NombreDia) VALUES(?,?,?,?)";
	private static final String update = "UPDATE tp2.Fecha set Dia=?, Mes=?, Anio=?, NombreDia=? WHERE idFecha = ?";
	private static final String delete = "DELETE FROM tp2.Fecha WHERE idFecha = ?";
	private static final String readall = "SELECT * FROM tp2.Fecha";
	private static final String find = "SELECT idFecha From tp2.Fecha WHERE Dia=? AND Mes=? AND Anio=?";
	private static final String readById = "SELECT * FROM tp2.Fecha WHERE Fecha.idFecha=?";
	private static final Connection sqlConection = DataBaseConnection.getConexion();

	public boolean insert(Date fecha) {
		try{
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(insert);
			statement.setByte(1, fecha.getDay());
			statement.setByte(2, fecha.getMonth());
			statement.setShort(3, fecha.getYear());
			statement.setString(4, fecha.getDayName());
			int rowsAffected=statement.executeUpdate();
			System.out.println("Se han agregado "+rowsAffected+" fecha");
			return true;
		}catch(SQLException e){
			System.out.println("Error en DateDAo.insert() "+e);
			return false;
		}
	}

	public int delete(Date fechaAeliminar) throws SQLException {
		return delete(fechaAeliminar.getId());
	}

	public int delete(Integer id) throws SQLException {
		PreparedStatement statement;
		statement = sqlConection.prepareStatement(delete);
		statement.setString(1, Integer.toString(id));
		return statement.executeUpdate();
	}

	public List<Date> readAll() throws SQLException {
		PreparedStatement statement;
		ResultSet resultSet;
		statement = sqlConection.prepareStatement(readall);
		resultSet = statement.executeQuery();

		List<Date> fechas = new ArrayList<Date>();
		while (resultSet.next()) {
			// Busco seg�n el nombre de la columna en la tabla contactos
			Integer id = resultSet.getInt("idFecha");
			Byte dia = resultSet.getByte("Dia");
			Byte mes = resultSet.getByte("Mes");
			Short year = resultSet.getShort("Anio");
			String dayName = resultSet.getString("NombreDia");
			fechas.add(new Date(id, dia, mes, year, dayName));
		}
		return fechas;
	}

	public int update(Integer id, Date newFecha) throws SQLException {
		PreparedStatement statement;
		statement = sqlConection.prepareStatement(update);
		statement.setByte(1, newFecha.getDay());
		statement.setByte(2, newFecha.getMonth());
		statement.setShort(3, newFecha.getYear());
		statement.setString(4, newFecha.getDayName());
		statement.setInt(5, id);
		return statement.executeUpdate();
	}

	public static Date readById(Integer id) {
		try{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readById);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				Integer idFecha = resultSet.getInt("idFecha");
				Byte dia = resultSet.getByte("Dia");
				Byte mes = resultSet.getByte("Mes");
				Short year = resultSet.getShort("Anio");
				String dayName = resultSet.getString("NombreDia");
				return (new Date(idFecha, dia, mes, year, dayName));
			}else{
				return null;
			}
		}catch (Exception e) {
			System.out.println("Error en DateDAO.readById() "+e);
			return null;
		}
	}

	public static Integer findDate(Date date) {
		try{
			PreparedStatement statement = sqlConection.prepareStatement(find);
			statement.setByte(1, date.getDay());
			statement.setByte(2, date.getMonth());
			statement.setShort(3, date.getYear());
			ResultSet set = statement.executeQuery();
			if(set.next()){
				Integer id=set.getInt("idFecha");
				return id;
			}else{
				return null;
			}
		} catch (Exception e) {
			System.out.println("Error en DateDAO.find() "+e);
			return null;
		}
	}

}
