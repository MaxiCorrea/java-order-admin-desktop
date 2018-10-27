package model.admins;

import java.util.List;
import entities.Product;
import model.interfaces.ProductAdminInterface;
import persistence.daos.ProductDAO;

public class ProductAdmin implements ProductAdminInterface {

	private ProductDAO productDAO;

	public ProductAdmin() {
		productDAO = new ProductDAO();
	}

	@Override
	public boolean createProduct(Product product) {
		boolean alreadyExists=ProductDAO.find(product);
		if(alreadyExists){
			System.out.println("El producto ya existe en la BD.");
			return false;
		}else{
			return productDAO.insert(product);
		}
	}

	@Override
	public boolean editProduct(Product product) {
		return productDAO.update(product);
	}

	@Override
	public boolean deleteProduct(Product product) {
		return productDAO.delete(product);
	}

	@Override
	public List<Product> readAllProduct() {
		return productDAO.readAll();
	}

	@Override
	public Product readByID(Long code) {
		return ProductDAO.readByCode(code);
	}

}
