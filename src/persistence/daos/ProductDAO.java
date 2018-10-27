package persistence.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.MeasurementUnit;
import entities.Product;
import entities.ProductCategory;
import persistence.connection.DataBaseConnection;

public class ProductDAO {

	private static final String insert = "INSERT INTO tp2.Producto(Codigo,Nombre, Descripcion, PrecioPorUnidad, UnidadDeMedida_idUnidadDeMedida,CategoriaProducto_idCategoriaProducto) VALUES(?,?,?,?,?,?)";
	private static final String update = "UPDATE tp2.Producto set Nombre=?, Descripcion=?, PrecioPorUnidad=?, UnidadDeMedida_idUnidadDeMedida=?,CategoriaProducto_idCategoriaProducto=? WHERE Codigo = ?";
	private static final String delete = "DELETE FROM tp2.Producto WHERE Codigo = ?";
	private static final String readall = "SELECT * FROM tp2.Producto";
	private static final String readByCode = "SELECT * FROM tp2.Producto WHERE Codigo=?";
	private static final String find = "SELECT * FROM tp2.Producto WHERE Codigo=? AND Nombre=? AND Descripcion=? AND PrecioPorUnidad=? AND UnidadDeMedida_idUnidadDeMedida=? AND CategoriaProducto_idCategoriaProducto=?";
	private static final Connection sqlConection = DataBaseConnection.getConexion();

	public boolean insert(Product producto) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(insert);
			statement.setLong(1, producto.getCodigo());
			statement.setString(2, producto.getNombre());
			statement.setString(3, producto.getDescripcion());
			statement.setBigDecimal(4, producto.getPrecioPorUnidad());
			statement.setShort(5, producto.getUnidadDeMedida().getId());
			statement.setShort(6, producto.getCategoriaProducto().getId());
			int rowsAffected = statement.executeUpdate();
			System.out.println("Se han insertado " + rowsAffected + " productos");
			return true;
		} catch (SQLException e) {
			System.out.println("Error en ProductDAO.insert() " + e);
			return false;
		}
	}

	public boolean delete(Product productoAeliminar) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(delete);
			statement.setString(1, Long.toString(productoAeliminar.getCodigo()));
			int rowsAffected = statement.executeUpdate();
			System.out.println("Se han eliminado " + rowsAffected + " productos");
			return true;
		} catch (SQLException e) {
			System.out.println("Error en ProductDAO.delete() " + e);
			return false;
		}
	}

	public List<Product> readAll() {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readall);
			resultSet = statement.executeQuery();

			List<Product> productos = new ArrayList<Product>();
			while (resultSet.next()) {
				Long cod = resultSet.getLong("Codigo");
				String nombre = resultSet.getString("Nombre");
				String descripcion = resultSet.getString("Descripcion");
				BigDecimal precio = resultSet.getBigDecimal("PrecioPorUnidad");
				MeasurementUnit unidadMedida = MeasurementUnitDAO.readById(resultSet.getShort("UnidadDeMedida_idUnidadDeMedida"));
				ProductCategory categoriaProducto = ProductCategoryDAO.readById(resultSet.getShort("CategoriaProducto_idCategoriaProducto"));
				productos.add(new Product(cod, nombre, descripcion, precio, unidadMedida, categoriaProducto));
			}
			return productos;
		} catch (SQLException e) {
			System.out.println("Error en ProductDAO.readAll() " + e);
			return null;
		}
	}

	public boolean update(Product nuevoProducto) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(update);
			statement.setString(1, nuevoProducto.getNombre());
			statement.setString(2, nuevoProducto.getDescripcion());
			statement.setBigDecimal(3, nuevoProducto.getPrecioPorUnidad());
			statement.setShort(4, nuevoProducto.getUnidadDeMedida().getId());
			statement.setShort(5, nuevoProducto.getCategoriaProducto().getId());
			statement.setLong(6, nuevoProducto.getCodigo());
			int rowsAffected = statement.executeUpdate();
			System.out.println("Se han actualizado " + rowsAffected + " productos");
			return true;
		} catch (SQLException e) {
			System.out.println("Error en ProductDAO.update() " + e);
			return false;
		}
	}

	public static Product readByCode(Long codigo) {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readByCode);
			statement.setLong(1, codigo);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				Long cod = resultSet.getLong("Codigo");
				String nombre = resultSet.getString("Nombre");
				String descripcion = resultSet.getString("Descripcion");
				BigDecimal precio = resultSet.getBigDecimal("PrecioPorUnidad");
				MeasurementUnit unidadMedida = MeasurementUnitDAO.readById(resultSet.getShort("UnidadDeMedida_idUnidadDeMedida"));
				ProductCategory categoriaProducto = ProductCategoryDAO.readById(resultSet.getShort("CategoriaProducto_idCategoriaProducto"));
				return (new Product(cod, nombre, descripcion, precio, unidadMedida, categoriaProducto));
			}else{
				return null;
			}
			
		} catch (SQLException e) {
			System.out.println("Error en ProductDAO.readByCode() " + e);
			return null;
		}
	}

	public static boolean find(Product product) {
		try {
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(find);
			statement.setLong(1, product.getCodigo());
			statement.setString(2, product.getNombre());
			statement.setString(3, product.getDescripcion());
			statement.setBigDecimal(4, product.getPrecioPorUnidad());
			statement.setShort(5, product.getUnidadDeMedida().getId());
			statement.setShort(6, product.getCategoriaProducto().getId());
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				int cod=resultSet.getInt("Codigo");
				if(cod==product.getCodigo()){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			System.out.println("Error en ProductDAO.readByCode() " + e);
			return false;
		}
	}

}
