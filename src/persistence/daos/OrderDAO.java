package persistence.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import entities.Address;
import entities.Customer;
import entities.Date;
import entities.Order;
import entities.OrderStatus;
import entities.PaymentMethod;
import entities.TimeZone;
import persistence.connection.DataBaseConnection;

public class OrderDAO {

	private static final String insert = "INSERT INTO tp2.Pedido(Hora,ImporteTotal,Cliente_DNI,DomicilioEntrega_idDomicilio,Fecha_idFecha,"
			+ "FormaDePago_idFormaDePago, EstadoPedido_idEstadoPedido,FranjaHoraria_idFranjaHoraria) VALUES(?,?,?,?,?,?,?,?)";
	private static final String find = "SELECT idPedido FROM tp2.Pedido WHERE Hora=? AND ImporteTotal=? AND Cliente_DNI=? AND DomicilioEntrega_idDomicilio=? AND Fecha_idFecha=?"
			+ " AND FormaDePago_idFormaDePago=? AND EstadoPedido_idEstadoPedido=? AND FranjaHoraria_idFranjaHoraria=?";
	private static final String update = "UPDATE tp2.Pedido set Hora=?, ImporteTotal=?,Cliente_DNI=?,DomicilioEntrega_idDomicilio=?,"
			+ "Fecha_idFecha=?,FormaDePago_idFormaDePago=?, EstadoPedido_idEstadoPedido=?,FranjaHoraria_idFranjaHoraria=? WHERE idPedido = ?";
	private static final String delete = "DELETE FROM tp2.Pedido WHERE idPedido = ?";
	private static final String readall = "SELECT * FROM tp2.Pedido";
	private static final String readById ="SELECT * FROM tp2.Pedido WHERE idPedido=?";
	private static final Connection sqlConection = DataBaseConnection.getConexion();

	public boolean insert(Order pedido) {

		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(insert);
			statement.setTime(1, pedido.getHora());
			statement.setBigDecimal(2, pedido.getImporteTotal());
			statement.setInt(3, pedido.getCliente().getDni());
			statement.setInt(4, pedido.getDomicilioEntrega().getId());
			statement.setInt(5, pedido.getFecha().getId());
			statement.setShort(6, pedido.getFormaDePago().getId());
			statement.setShort(7, pedido.getEstadoDePedido().getId());
			statement.setShort(8, pedido.getFranjaHoraria().getId());
			int rowsAffected = statement.executeUpdate();
			System.out.println("Se han agregado " + rowsAffected + " pedido/s");
			return true;
		} catch (SQLException e) {
			System.out.println("Error en OrderDAO.insert() " + e);
			return false;
		}
	}

	public boolean delete(Order order) {
		try{
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(delete);
			statement.setLong(1, order.getId());
			int rowsAffected = statement.executeUpdate();
			System.out.println("Se han eliminado " + rowsAffected + " pedido/s");
			return true;
		} catch (SQLException e) {
			System.out.println("Error en OrderDAO.delete() " + e);
			return false;
		}
	}

	public List<Order> readAll() {
		try{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readall);
			resultSet = statement.executeQuery();
			AddressDAO addresDao = new AddressDAO();
			List<Order> pedidos = new ArrayList<Order>();
			while (resultSet.next()) {
				Long id = resultSet.getLong("idPedido");
				Time hora = resultSet.getTime("Hora");
				BigDecimal importe = resultSet.getBigDecimal("ImporteTotal");
				Customer cliente = CustomerDAO.readByDNI(resultSet.getInt("Cliente_DNI"));
				Address domicilioEntrega = addresDao.readById(resultSet.getInt("DomicilioEntrega_idDomicilio"));
				Date fecha = DateDAO.readById(resultSet.getInt("Fecha_idFecha"));
				PaymentMethod formaDePago = PaymentMethodDAO.readById(resultSet.getShort("FormaDePago_idFormaDePago"));
				OrderStatus estadoPedido = OrderStatusDAO.readById(resultSet.getShort("EstadoPedido_idEstadoPedido"));
				TimeZone franjaHoraria = TimeZoneDAO.readById(resultSet.getShort("FranjaHoraria_idFranjaHoraria"));
				Order order = new Order(id, hora, importe, cliente, domicilioEntrega, fecha, formaDePago, estadoPedido,
						franjaHoraria);
				order.setProductOrder(new ProductOrderDAO().readAll(order));
				pedidos.add(order);
			}
			return pedidos;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean update(Order nuevopedido){
		try{
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(update);
			statement.setTime(1, nuevopedido.getHora());
			statement.setBigDecimal(2, nuevopedido.getImporteTotal());
			statement.setInt(3, nuevopedido.getCliente().getDni());
			statement.setInt(4, nuevopedido.getDomicilioEntrega().getId());
			statement.setInt(5, DateDAO.findDate(nuevopedido.getFecha()));
			statement.setShort(6, nuevopedido.getFormaDePago().getId());
			statement.setShort(7, nuevopedido.getEstadoDePedido().getId());
			statement.setShort(8, nuevopedido.getFranjaHoraria().getId());
			statement.setLong(9, nuevopedido.getId());
			int rowsAffected = statement.executeUpdate();
			System.out.println("Se han actualizado " + rowsAffected + " pedido/s");
			return true;
		}catch(SQLException e){
			System.out.println("Error en OrderDAO.update() "+e);
			return false;
		}
	}

	public static Order readById(Long idPedido) {
		try{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readById);
			statement.setLong(1, idPedido);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				AddressDAO addresDao = new AddressDAO();

				Long id = resultSet.getLong("idPedido");
				Time hora = resultSet.getTime("Hora");
				BigDecimal importe = resultSet.getBigDecimal("ImporteTotal");
				Integer cantidadProducto = resultSet.getInt("CantidadProducto");
				Customer cliente = CustomerDAO.readByDNI(resultSet.getInt("Cliente_DNI"));
				Address domicilioEntrega = addresDao.readById(resultSet.getInt("DomicilioEntrega_idDomicilio"));
				Date fecha = DateDAO.readById(resultSet.getInt("Fecha_idFecha"));
				PaymentMethod formaDePago = PaymentMethodDAO.readById(resultSet.getShort("FormaDePago_idFormaDePago"));
				OrderStatus estadoPedido = OrderStatusDAO.readById(resultSet.getShort("EstadoPedido_idEstadoPedido"));
				TimeZone franjaHoraria = TimeZoneDAO.readById(resultSet.getShort("FranjaHoraria_idFranjaHoraria"));
				return (new Order(id, hora, importe, cantidadProducto, cliente, domicilioEntrega, fecha, formaDePago,
						estadoPedido, franjaHoraria));
			}else{
				return null;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long find(Order order) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(find);
			statement.setTime(1, order.getHora());
			statement.setBigDecimal(2, order.getImporteTotal());
			statement.setInt(3, order.getCliente().getDni());
			statement.setInt(4, order.getDomicilioEntrega().getId());
			statement.setInt(5, order.getFecha().getId());
			statement.setShort(6, order.getFormaDePago().getId());
			statement.setShort(7, order.getEstadoDePedido().getId());
			statement.setShort(8, order.getFranjaHoraria().getId());
			ResultSet res = statement.executeQuery();
			if(res.next()){
				Long idPedido = res.getLong("idPedido");
				return idPedido;
			}else{
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error en OrderDAO.find() " + e);
			return null;
		}
	}

}
