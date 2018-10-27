package persistence.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Order;
import entities.Product;
import entities.ProductOrder;
import persistence.connection.DataBaseConnection;

public class ProductOrderDAO {

	private static final String insert = "INSERT INTO tp2.Producto_Pedido (Producto_Codigo,Pedido_idPedido,PrecioProducto,CantidadProducto) VALUES(?,?,?,?)";
	private static final String update = "UPDATE tp2.Producto_Pedido SET PrecioProducto=?,CantidadProducto=? WHERE Pedido_idPedido=? AND Producto_Codigo=?";
	private static final String delete = "DELETE FROM tp2.Producto_Pedido WHERE Pedido_idPedido=?";
	private static final String readall = "SELECT * FROM tp2.Producto_Pedido WHERE Pedido_idPedido=?";
	private static final String find = "SELECT * FROM tp2.Producto_Pedido WHERE Pedido_idPedido=? AND Producto_Codigo=?";
	private static final Connection sqlConection = DataBaseConnection.getConexion();

	public List<ProductOrder> readAll(Order order) {
		try{
			List<ProductOrder> productsOrder = new ArrayList<>();
			PreparedStatement st = sqlConection.prepareStatement(readall);
			st.setLong(1, order.getId());
			ResultSet set = st.executeQuery();
			while (set.next()) {
				Product product = ProductDAO.readByCode(set.getLong("Producto_Codigo"));
				BigDecimal price = set.getBigDecimal("PrecioProducto");
				int quantity = set.getInt("CantidadProducto");
				productsOrder.add(new ProductOrder(product, order.getId(), price, quantity));
			}
			return productsOrder;
		}catch(SQLException e){
			System.out.println("Error en ProductOrderDAO.readAll() "+e);
			return null;
		}
	}

	public boolean insert(ProductOrder productOrder) {
		try{
			PreparedStatement st = sqlConection.prepareStatement(insert);
			st.setLong(1, productOrder.getProduct().getCodigo());
			st.setLong(2, productOrder.getOrderId());
			st.setBigDecimal(3, productOrder.getPrice());
			st.setInt(4, productOrder.getQuantity());
			st.executeUpdate();
			return true;
		} catch (SQLException ex) {
			System.out.println("Error en ProductOrderDAO.insert() "+ex);
			return false;
		}
	}
	public boolean delete(ProductOrder productOrder) {
		try{
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(delete);
			statement.setLong(1, productOrder.getOrderId());
			int rowsAffected = statement.executeUpdate();
			System.out.println("Se han eliminado " + rowsAffected + " pedido/s");
			return true;
		} catch (SQLException e) {
			System.out.println("Error en OrderDAO.delete() " + e);
			return false;
		}
	}

	public boolean update(ProductOrder productOrder) {
		try{
			PreparedStatement st = sqlConection.prepareStatement(update);
			st.setLong(4, productOrder.getProduct().getCodigo());
			st.setLong(3, productOrder.getOrderId());
			st.setBigDecimal(1, productOrder.getPrice());
			st.setInt(2, productOrder.getQuantity());
			st.executeUpdate();
			return true;
		} catch (SQLException ex) {
			System.out.println("Error en ProductOrderDAO.update() "+ex);
			return false;
		}
	}
	
	public static ProductOrder find(ProductOrder po){
		try{
			PreparedStatement st = sqlConection.prepareStatement(find);
			st.setLong(1, po.getOrderId());
			st.setLong(2, po.getProduct().getCodigo());
			ResultSet set = st.executeQuery();
			if (set.next()) {
				Product product = ProductDAO.readByCode(set.getLong("Producto_Codigo"));
				BigDecimal price = set.getBigDecimal("PrecioProducto");
				int quantity = set.getInt("CantidadProducto");
				return (new ProductOrder(product, po.getOrderId(), price, quantity));
			}else{
				return null;
			}
		}catch(SQLException e){
			System.out.println("Error en ProductOrderDAO.find() "+e);
			return null;
		}
	}

}
