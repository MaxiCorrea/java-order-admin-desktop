package persistence.connection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DataBaseConnection {

  private static Connection connection;

  private static String url;
  private static String user;
  private static String pass;

  private DataBaseConnection() {
    throw new AssertionError();
  }

  public final synchronized static Connection getConexion() {
    if (connection == null) {
      try {
        connection = createConnection();
        System.out.println("Conectado! ;) ");
      } catch (Exception e) {
        System.out.println("No Conectado! :( ");
        e.printStackTrace();
      }
    }
    return connection;
  }

  private static Connection createConnection() throws Exception {
    Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownJob()));
    final String path = "properties/mysql-properties.xml";
    Properties prop = new Properties();
    FileInputStream fis = new FileInputStream(path);
    prop.loadFromXML(fis);
    url = prop.getProperty("url");
    user = prop.getProperty("user");
    pass = prop.getProperty("pass");
    return DriverManager.getConnection(url, user, pass);
  }

  private static class ShutdownJob implements Runnable {
    @Override
    public void run() {
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }
  }

  public static boolean createBackupLinux(String fileBackup) {
    String executeCmd =
        "mysqldump -u " + user + " -p" + pass + " " + "tp2" + " -r " + fileBackup;
    try {
      Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
      int processComplete;
      processComplete = runtimeProcess.waitFor();
      if (processComplete == 0) {
        System.out.println("Ok");
        return true;
      } else {
        System.out.println("Error");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean restoreBackupLinux(String fileBackup) {
    try {
      String[] executeCmd = new String[] {"/bin/sh", "-c",
          "mysql -u" + user + " -p" + pass + " " + "tp2" + " < " + fileBackup};
      Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
      int processComplete = runtimeProcess.waitFor();
      if (processComplete == 0) {
        System.out.println("Ok");
        return true;
      } else {
        System.out.println("Error");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return false;
  }

}
