package persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.ProductCategory;
import persistence.connection.DataBaseConnection;

public class ProductCategoryDAO {

	private static final String insert = "INSERT INTO tp2.CategoriaProducto(CategoriaProducto) VALUES(?)";
	private static final String update = "UPDATE tp2.CategoriaProducto set CategoriaProducto = ? WHERE idCategoriaProducto = ?";
	private static final String delete = "DELETE FROM tp2.CategoriaProducto WHERE idCategoriaProducto = ?";
	private static final String readall = "SELECT * FROM tp2.CategoriaProducto";
	private static final String readByName = "SELECT * FROM tp2.CategoriaProducto WHERE CategoriaProducto=?";
	private static final String readById = "SELECT * FROM tp2.CategoriaProducto WHERE idCategoriaProducto=?";
	private static final Connection sqlConection = DataBaseConnection.getConexion();

	public boolean insert(ProductCategory categoriaProducto) {
		try {
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(insert);
			statement.setString(1, categoriaProducto.getName());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error en ProductCategoryDAO.insert() " + e);
			return false;
		}
	}

	public boolean delete(ProductCategory categoriaProductoAeliminar) {
		try{
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(delete);
			statement.setString(1, Short.toString(categoriaProductoAeliminar.getId()));
			statement.executeUpdate();
			return true;
		}catch (SQLException e) {
			System.out.println("Error en ProductCategoryDAO.delete() " + e);
			return false;
		}
	}

	public List<ProductCategory> readAll() {
		try{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readall);
			resultSet = statement.executeQuery();

			List<ProductCategory> categorias = new ArrayList<ProductCategory>();
			while (resultSet.next()) {
				Short id = resultSet.getShort("idCategoriaProducto");
				String nombre = resultSet.getString("CategoriaProducto");
				categorias.add(new ProductCategory(id, nombre));
			}
			return categorias;
		}catch (SQLException e) {
			System.out.println("Error en ProductCategoryDAO.readAll() " + e);
			return null;
		}
	}

	public boolean update(ProductCategory newProductCategory) {
		try{
			PreparedStatement statement;
			statement = sqlConection.prepareStatement(update);
			statement.setShort(2, newProductCategory.getId());
			statement.setString(1, newProductCategory.getName());
			statement.executeUpdate();
			return true;
		}catch (SQLException e) {
			System.out.println("Error en ProductCategoryDAO.update() " + e);
			return false;
		}
	}

	public static ProductCategory readById(Short id) {
		try{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readById);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				Short idProducto = resultSet.getShort("idCategoriaProducto");
				String nombre = resultSet.getString("CategoriaProducto");
				return (new ProductCategory(idProducto, nombre));
			}else{
				return null;
			}
			
		}catch (SQLException e) {
			System.out.println("Error en ProductCategoryDAO.readById() " + e);
			return null;
		}
	}
	public static ProductCategory readByName(String name) {
		try
		{
			PreparedStatement statement;
			ResultSet resultSet;
			statement = sqlConection.prepareStatement(readByName);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				Short idProducto = resultSet.getShort("idCategoriaProducto");
				String nombre = resultSet.getString("CategoriaProducto");
				return (new ProductCategory(idProducto, nombre));
			}else{
				return null;
			}
		}catch(SQLException e)
		{
			System.out.println("Error en ProductCategory.readByName() "+e);
			return null;
		}
	}

}
