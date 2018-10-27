package model.admins;

import java.util.ArrayList;
import java.util.List;

import entities.ProductCategory;
import model.interfaces.ProductCategoryAdminInterface;
import persistence.daos.ProductCategoryDAO;

public class ProductCategoryAdmin implements ProductCategoryAdminInterface {

	private ProductCategoryDAO productCategoryDAO;

	public ProductCategoryAdmin() {
		productCategoryDAO = new ProductCategoryDAO();
	}

	@Override
	public boolean createProductCategory(ProductCategory productCategory) {
		ProductCategory alreadyExists=ProductCategoryDAO.readByName(productCategory.getName());
		if(alreadyExists==null)
		{
			productCategoryDAO.insert(productCategory);
			return true;
		}else
		{
			System.out.println("Ya existe la Categoria de Producto");
			return false;
		}
	}

	@Override
	public boolean editProductCategory(ProductCategory productCategory) {
		return productCategoryDAO.update(productCategory);
	}

	@Override
	public boolean deleteProductCategory(ProductCategory productCategory) {
		return productCategoryDAO.delete(productCategory);
	}

	@Override
	public List<ProductCategory> readAllProductCategory() {
		try {
			return productCategoryDAO.readAll();
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<ProductCategory>();
		}
	}

}
