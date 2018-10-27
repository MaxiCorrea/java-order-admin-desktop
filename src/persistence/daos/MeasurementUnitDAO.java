package persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.MeasurementUnit;
import persistence.connection.DataBaseConnection;

public class MeasurementUnitDAO {

  private static final String insert = "INSERT INTO tp2.UnidadDeMedida(TipoUnidadDeMedida) VALUES(?)";
  private static final String update = "UPDATE tp2.UnidadDeMedida set TipoUnidadDeMedida = ? WHERE idUnidadDeMedida = ?";
  private static final String delete = "DELETE FROM tp2.UnidadDeMedida WHERE idUnidadDeMedida = ?";
  private static final String readall = "SELECT * FROM tp2.UnidadDeMedida";
  private static final String readById ="SELECT * FROM tp2.UnidadDeMedida WHERE UnidadDeMedida.idUnidadDeMedida=?";
  private static final String readByName ="SELECT * FROM tp2.UnidadDeMedida WHERE UnidadDeMedida.TipoUnidadDeMedida=?";
  private static final Connection sqlConection = DataBaseConnection.getConexion();
  
  public boolean insert(MeasurementUnit UnidadDeMedida)
  {
     try
     {
    	 PreparedStatement statement;
         statement = sqlConection.prepareStatement(insert);
         statement.setString(1, UnidadDeMedida.getUnit());
         statement.executeUpdate();
         return true;
     }catch (SQLException e)
     {
    	 System.out.println("Error en MeasurementUnitDAO.insert() "+e);
    	 return false;
     }
  }
  
  public boolean delete(MeasurementUnit unidadDeMedidaAeliminar)
  {
	  try{
		  PreparedStatement statement;
	      statement = sqlConection.prepareStatement(delete);
	      statement.setString(1, Short.toString(unidadDeMedidaAeliminar.getId()));
	      statement.executeUpdate();
	      return true;
	  }catch (SQLException e)
	     {
	    	 System.out.println("Error en MeasurementUnitDAO.delete() "+e);
	    	 return false;
	     }
  }
  
  public List<MeasurementUnit> readAll()
  {
      try{
    	  PreparedStatement statement;
          ResultSet resultSet;
          statement = sqlConection.prepareStatement(readall);
          resultSet = statement.executeQuery();
          
          List<MeasurementUnit> unidadesMedida = new ArrayList<MeasurementUnit>();
          while(resultSet.next())
          {
              Short id = resultSet.getShort("idUnidadDeMedida");
              String nombre = resultSet.getString("TipoUnidadDeMedida");
              unidadesMedida.add(new MeasurementUnit(id, nombre));
          }
          return unidadesMedida;
      }catch (SQLException e){
    	  System.out.println("Error en MeasurementUnitDAO.readAll() "+e);
    	  return null;
      }
  }

  public boolean update(Short id, MeasurementUnit nuevoUnidadDeMedida)
  {
      try{
    	  PreparedStatement statement;
          statement = sqlConection.prepareStatement(update);
          statement.setString(1, nuevoUnidadDeMedida.getUnit());
          statement.setShort(2 ,id);
          statement.executeUpdate();
          return true;
      }catch (SQLException e){
    	  System.out.println("Error en MeasurementUnitDAO.update() "+e);
    	  return false;
      }
  }

  public static MeasurementUnit readById(Short id)
  {
      try{
    	  PreparedStatement statement;
          ResultSet resultSet;
          statement = sqlConection.prepareStatement(readById);
          statement.setInt(1, id);
          resultSet = statement.executeQuery();
          if(resultSet.next()){
        	  Short idUnidadMedida = resultSet.getShort("idUnidadDeMedida");
              String nombre = resultSet.getString("TipoUnidadDeMedida");
              return (new MeasurementUnit(idUnidadMedida, nombre));
          }else{
        	  return null;
          }
      }catch (SQLException e){
    	  System.out.println("Error en MeasurementUnitDAO.readById() "+e);
    	  return null;
      }
  }
  public static MeasurementUnit readByName(String name)
  {
      try
      {
    	  PreparedStatement statement;
          ResultSet resultSet;
          statement = sqlConection.prepareStatement(readByName);
          statement.setString(1, name);
          resultSet = statement.executeQuery();
          if(resultSet.next()){
        	  Short idUnidadMedida = resultSet.getShort("idUnidadDeMedida");
              String nombre = resultSet.getString("TipoUnidadDeMedida");
              return (new MeasurementUnit(idUnidadMedida, nombre));
          }else{
        	  return null;
          }

      } catch(SQLException e)
      {
    	  System.out.println("Error en MeasurementUnitDAO.readByName() "+e);
    	  return null;
      }
  }
  
}
