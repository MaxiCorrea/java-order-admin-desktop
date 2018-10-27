package persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.TypePhone;
import entities.TypePhoneCustomer;
import persistence.connection.DataBaseConnection;

public class TypePhoneCustomerDAO {

	private static final String insert = "INSERT INTO tp2.TipoTelefono_Cliente(Tipo_Telefono_idTipo_Telefono,Cliente_DNI,Telefono) VALUES(?,?,?)";
	private static final String update = "UPDATE tp2.TipoTelefono_Cliente set Telefono=? WHERE TipoTelefono_Cliente.Tipo_Telefono_idTipo_Telefono = ? and TipoTelefono_Cliente.Cliente_DNI=?";
	private static final String delete = "DELETE FROM tp2.TipoTelefono_Cliente WHERE TipoTelefono_Cliente.Tipo_Telefono_idTipo_Telefono = ? and TipoTelefono_Cliente.Cliente_DNI=?";
	private static final String readall = "SELECT * FROM tp2.TipoTelefono_Cliente";
	private static final String readBy = "SELECT * FROM tp2.TipoTelefono_Cliente WHERE TipoTelefono_Cliente.Tipo_Telefono_idTipo_Telefono = ? and TipoTelefono_Cliente.Cliente_DNI=?";
	private static final Connection sqlConection = DataBaseConnection.getConexion();

	public boolean insert(TypePhoneCustomer tipoTelCliente) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(insert);
			statement.setByte(1, tipoTelCliente.getTypePhone().getId());
			statement.setInt(2, tipoTelCliente.getCustomerDNI());
			statement.setString(3, tipoTelCliente.getPhoneNumber());
			int rowsAffected = statement.executeUpdate();
			System.out.println("Se han agregado " + rowsAffected + " telefono");
			return true;
		} catch (SQLException e) {
			System.out.println("Error en TypePhoneCustomerDAO.insert() " + e);
			return false;
		}
	}

	public boolean delete(TypePhoneCustomer tipoTelCliente) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(delete);
			statement.setInt(1, tipoTelCliente.getTypePhone().getId());
			statement.setInt(2, tipoTelCliente.getCustomerDNI());
			int rowsAffected = statement.executeUpdate();
			System.out.println("Se han eliminado " + rowsAffected + " telefono");
			return true;
		} catch (SQLException e) {
			System.out.println("Error en TypePhoneCustomerDAO.delete() " + e);
			return false;
		}
	}

	public List<TypePhoneCustomer> readAll() {
		try
		{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readall);
			resultSet = statement.executeQuery();

			List<TypePhoneCustomer> tipoTelClientes = new ArrayList<TypePhoneCustomer>();
			while (resultSet.next()) {
				Byte idTypePhone = resultSet.getByte("Tipo_Telefono_idTipo_Telefono");
				Integer dniCustomer = resultSet.getInt("Cliente_DNI");
				String tel = resultSet.getString("Telefono");
				TypePhone tipoTel = TypePhoneDAO.readById(idTypePhone);
				tipoTelClientes.add(new TypePhoneCustomer(tipoTel, dniCustomer, tel));
			}
			return tipoTelClientes;
		}catch(SQLException e)
		{
			System.out.println("Error en TypePhoneCustomerDAO.readAll() " + e);
			return null;
		}
	}
	public static String readPhoneNumber_ByTypePhoneAndCustomerDNI(Byte idTypePhone, Integer CustomerDNI)
	{
		try
		{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readBy);
			statement.setByte(1, idTypePhone);
			statement.setInt(2, CustomerDNI);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				String tel = resultSet.getString("Telefono");
				return tel;
			}else{
				return null;
			}
		}catch(SQLException e)
		{
			System.out.println("Error en TypePhoneCustomerDAO.readPhoneNumber_ByTypePhoneAndCustomerDNI() " + e);
			return null;
		}
	}

	public boolean update(TypePhoneCustomer nuevotipoTelCliente){
		try
		{
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(update);
			statement.setString(1, nuevotipoTelCliente.getPhoneNumber());
			statement.setByte(2, nuevotipoTelCliente.getTypePhone().getId());
			statement.setInt(3, nuevotipoTelCliente.getCustomerDNI());
			int rowsAffected = statement.executeUpdate();
			System.out.println("Se han modificado " + rowsAffected + " telefono");
			return true;
		}catch(SQLException e)
		{
			System.out.println("Error en TypePhoneCustomerDAO.update() " + e);
			return false;
		}
	}

}
