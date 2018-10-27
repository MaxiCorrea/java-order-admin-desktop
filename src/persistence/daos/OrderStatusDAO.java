package persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.OrderStatus;
import persistence.connection.DataBaseConnection;

public class OrderStatusDAO {

  private static final String insert = "INSERT INTO tp2.EstadoPedido(EstadoPedido) VALUES(?)";
  private static final String update = "UPDATE tp2.EstadoPedido set EstadoPedido = ? WHERE idEstadoPedido = ?";
  private static final String delete = "DELETE FROM tp2.EstadoPedido WHERE idEstadoPedido = ?";
  private static final String readall = "SELECT * FROM tp2.EstadoPedido";
  private static final Connection sqlConection = DataBaseConnection.getConexion();
  
  public boolean insert(OrderStatus estadoPedido)
  {
	  try{
		  PreparedStatement statement;
	      statement = sqlConection.prepareStatement(insert);
	      statement.setString(1, estadoPedido.getStatus());
	      statement.executeUpdate();
	      return true;
	  }catch(SQLException e){
		  System.out.println("Error en OrderStatusDAO.insert() "+e);
		  return false;
	  }
  }
  
  public boolean delete(OrderStatus estadoPedidoAeliminar)
  {
	  try{
		  PreparedStatement statement;
	      statement = sqlConection.prepareStatement(delete);
	      statement.setString(1, Short.toString(estadoPedidoAeliminar.getId()));
	      statement.executeUpdate();
	      return true;
	  }catch(SQLException e){
		  System.out.println("Error en OrderStatusDAO.delete() "+e);
		  return false;
	  }
  }
  
  public List<OrderStatus> readAll()
  {
      try{
    	  PreparedStatement statement;
          ResultSet resultSet;
          statement = sqlConection.prepareStatement(readall);
          resultSet = statement.executeQuery();
          
          List<OrderStatus> estados = new ArrayList<OrderStatus>();
          while(resultSet.next())
          {
              Short id = resultSet.getShort("idEstadoPedido");
              String estado = resultSet.getString("Estadopedido");
              estados.add(new OrderStatus(id, estado));
          }
          return estados;
      }catch(SQLException e){
		  System.out.println("Error en OrderStatusDAO.readAll() "+e);
		  return null;
	  }
  }

  public boolean update(OrderStatus newOrderStatus)
  {
      try{
    	  PreparedStatement statement;
          statement = sqlConection.prepareStatement(update);
          statement.setString(1, newOrderStatus.getStatus());
          statement.setShort(2, newOrderStatus.getId());
          statement.executeUpdate();
          return true;
      }catch(SQLException e){
		  System.out.println("Error en OrderStatusDAO.update() "+e);
		  return false;
	  }
  }

  public static OrderStatus readById(short id)
  {
      try{
    	  PreparedStatement statement;
          ResultSet resultSet;
          statement = sqlConection.prepareStatement("SELECT * FROM EstadoPedido WHERE idEstadoPedido=?");
          statement.setInt(1, id);
          resultSet = statement.executeQuery();
          if(resultSet.next()){
        	  Short idEstado = resultSet.getShort("idEstadoPedido");
              String estado = resultSet.getString("Estadopedido");
              return (new OrderStatus(idEstado,estado));
          }else{
        	  return null;
          }  
      }catch(SQLException e){
		  System.out.println("Error en OrderStatusDAO.readById() "+e);
		  return null;
	  }
  }
  
}
