package entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {

  public Validar() {
    super();
  }

  public boolean esDNI(String string) {
    String regularEx = new String("[\\d$]{7,8}");
    Pattern p = Pattern.compile(regularEx);
    Matcher m = p.matcher(string);
    return m.matches();
  }

  public boolean esNombre_ApellidoCliente(String string) {
    String regularEx = new String("[a-zA-Z\\s]{3,45}");
    Pattern p = Pattern.compile(regularEx);
    Matcher m = p.matcher(string);
    return m.matches();
  }

  public static boolean esNombreProvincia_Localidad(String string) {
    String regularEx = new String("[a-zA-Z\\s\\.]{3,60}");
    Pattern p = Pattern.compile(regularEx);
    Matcher m = p.matcher(string);
    return m.matches();
  }

  public boolean esTelefono(String string) {
    String regularEx = new String("[\\d$]{6,13}");
    Pattern p = Pattern.compile(regularEx);
    Matcher m = p.matcher(string);
    return m.matches();
  }

  public boolean esAlturaDomicilio(String string) {
    String regularEx = new String("[\\d$]{1,5}");
    Pattern p = Pattern.compile(regularEx);
    Matcher m = p.matcher(string);
    return m.matches();
  }

  public boolean esCalle(String string) {
    String regularEx = new String("[a-zA-Z\\s\\.]{3,60}");
    Pattern p = Pattern.compile(regularEx);
    Matcher m = p.matcher(string);
    return m.matches();
  }

  public boolean esPiso_Dpto(String string) {
    String regularEx = new String("[\\d$]{1,2}");
    Pattern p = Pattern.compile(regularEx);
    Matcher m = p.matcher(string);
    return m.matches();
  }

  public boolean esCP(String string) {
    String regularEx = new String("[\\d$]{4,4}");
    Pattern p = Pattern.compile(regularEx);
    Matcher m = p.matcher(string);
    return m.matches();
  }

  public boolean esCodProducto(String string) {
    String regularEx = new String("[\\d$]{1,15}");
    Pattern p = Pattern.compile(regularEx);
    Matcher m = p.matcher(string);
    return m.matches();
  }

  public boolean esNombreProducto(String string) {
    String regularEx = new String("[a-zA-Z0-9\\s\\.]{3,50}");
    Pattern p = Pattern.compile(regularEx);
    Matcher m = p.matcher(string);
    return m.matches();
  }

  public boolean esPrecio(String string) {
    String regularEx = new String("(\\d+)(\\.?)(\\d+)");
    Pattern p = Pattern.compile(regularEx);
    Matcher m = p.matcher(string);
    return m.matches();
  }

  public static boolean esUnidadMedida(String string) {
    String regularEx = new String("[a-zA-Z\\s]{1,20}");
    Pattern p = Pattern.compile(regularEx);
    Matcher m = p.matcher(string);
    return m.matches();
  }

  public static boolean esCategoriaProducto(String string) {
    String regularEx = new String("[a-zA-Z\\s]{1,50}[0-9]{0,}");
    Pattern p = Pattern.compile(regularEx);
    Matcher m = p.matcher(string);
    return m.matches();
  }

}
