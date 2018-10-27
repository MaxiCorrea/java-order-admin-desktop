package persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.PaymentMethod;
import persistence.connection.DataBaseConnection;

public class PaymentMethodDAO {

	private static final String insert = "INSERT INTO tp2.FormaDePago(FormaDePago) VALUES(?)";
	private static final String update = "UPDATE tp2.FormaDePago set FormaDePago = ? WHERE idFormaDePago = ?";
	private static final String delete = "DELETE FROM tp2.FormaDePago WHERE idFormaDePago = ?";
	private static final String readall = "SELECT * FROM tp2.FormaDePago";
	private static final String readById="SELECT * FROM FormaDePago WHERE idFormaDePago=?";
	private static final String readByName="SELECT * FROM FormaDePago WHERE FormaDePago=?";
	private static final Connection sqlConection = DataBaseConnection.getConexion();

	public boolean insert(PaymentMethod metodoPago) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(insert);
			statement.setString(1, metodoPago.getPaymentMethod());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error en PaymentMethod.insert() " + e);
			return false;
		}
	}

	public boolean delete(PaymentMethod metodoPagoAeliminar) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(delete);
			statement.setString(1, Short.toString(metodoPagoAeliminar.getId()));
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error en PaymentMethod.delete() " + e);
			return false;
		}
	}

	public List<PaymentMethod> readAll() {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readall);
			resultSet = statement.executeQuery();

			List<PaymentMethod> formasDePago = new ArrayList<PaymentMethod>();
			while (resultSet.next()) {
				Short id = resultSet.getShort("idFormaDePago");
				String formaPago = resultSet.getString("FormaDePago");
				formasDePago.add(new PaymentMethod(id, formaPago));
			}
			return formasDePago;
		} catch (SQLException e) {
			System.out.println("Error en PaymentMethod.readAll() " + e);
			return null;
		}
	}

	public boolean update(Short id, PaymentMethod newPaymentMethod) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(update);
			statement.setString(2, newPaymentMethod.getPaymentMethod());
			statement.setShort(2, id);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error en PaymentMethod.update() " + e);
			return false;
		}
	}

	public static PaymentMethod readById(short id) {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readById);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				Short idFormaPago = resultSet.getShort("idFormaDePago");
				String formaPago = resultSet.getString("FormaDePago");
				return (new PaymentMethod(idFormaPago, formaPago));
			}else{
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error en PaymentMethod.readById() " + e);
			return null;
		}
	}
	
	public static PaymentMethod readByName(String name) {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readByName);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				Short idFormaPago = resultSet.getShort("idFormaDePago");
				String formaPago = resultSet.getString("FormaDePago");
				return (new PaymentMethod(idFormaPago, formaPago));
			}else{
				return null;
			}
			
		} catch (SQLException e) {
			System.out.println("Error en PaymentMethod.readByName() " + e);
			return null;
		}
	}

}
