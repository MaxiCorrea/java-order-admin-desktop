package model.interfaces;

import java.util.List;

import entities.Product;

public interface ProductAdminInterface {

  boolean createProduct(Product product);
  
  boolean editProduct(Product product);
  
  boolean deleteProduct(Product product);
  
  List<Product> readAllProduct();
  
  Product readByID(Long code);
  
}
