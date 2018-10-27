package persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.TypePhone;
import persistence.connection.DataBaseConnection;

public class TypePhoneDAO {

	private static final String insert = "INSERT INTO tp2.TipoTelefono(TipoDeTelefono) VALUES(?)";
	private static final String update = "UPDATE tp2.TipoTelefono set FormaDePago = ? WHERE idTipoTelefono = ?";
	private static final String delete = "DELETE FROM tp2.TipoTelefono WHERE idTipoTelefono = ?";
	private static final String readall = "SELECT * FROM tp2.TipoTelefono";
	private static final String readById = "SELECT * FROM tp2.TipoTelefono WHERE idTipoTelefono=?";
	private static final Connection sqlConection = DataBaseConnection.getConexion();

	public boolean insert(TypePhone tipoTel) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(insert);
			statement.setString(1, tipoTel.getType());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error en TypePhone.insert() " + e);
			return false;
		}
	}

	public boolean delete(TypePhone typePhoneAeliminar) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(delete);
			statement.setString(1, Byte.toString(typePhoneAeliminar.getId()));
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error en TypePhone.delete() " + e);
			return false;
		}
	}

	public List<TypePhone> readAll(){
		try{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readall);
			resultSet = statement.executeQuery();

			List<TypePhone> tiposTel = new ArrayList<TypePhone>();
			while (resultSet.next()) {
				Byte id = resultSet.getByte("idTipoTelefono");
				String nombre = resultSet.getString("TipoDeTelefono");
				tiposTel.add(new TypePhone(id, nombre));
			}
			return tiposTel;
		}catch (SQLException e) {
			System.out.println("Error en TypePhone.readAll() " + e);
			return null;
		}
	}

	public boolean update(Byte id, TypePhone newTypePhone) {
		try{
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(update);
			statement.setString(1, newTypePhone.getType());
			statement.setByte(2, id);
			statement.executeUpdate();
			return true;
		}catch (SQLException e) {
			System.out.println("Error en TypePhone.update() " + e);
			return false;
		}
	}

	public static TypePhone readById(Byte idTypePhone) {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readById);
			statement.setByte(1, idTypePhone);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				Byte id = resultSet.getByte("idTipoTelefono");
				String nombre = resultSet.getString("TipoDeTelefono");
				return (new TypePhone(id, nombre));
			}else{
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error en TypePhoneDAO.readById() " + e);
			return null;
		}
	}

}
