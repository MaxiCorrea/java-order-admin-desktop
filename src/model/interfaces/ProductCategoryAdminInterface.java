package model.interfaces;

import java.util.List;

import entities.ProductCategory;

public interface ProductCategoryAdminInterface {

  boolean createProductCategory(ProductCategory productCategory);

  boolean editProductCategory(ProductCategory productCategory);

  boolean deleteProductCategory(ProductCategory productCategory);

  List<ProductCategory> readAllProductCategory();
  
}
