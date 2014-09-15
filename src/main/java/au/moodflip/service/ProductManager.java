package au.moodflip.service;
import java.io.Serializable;
import java.util.List;

import au.moodflip.domain.Product;

public interface ProductManager extends Serializable{
	public void increasePrice(int percentage);
	public List<Product> getProducts();
	public void addProduct(Product product);
	public Product getProductById(long id);
	public void updateProduct(Product product);
	public void deleteProduct(long id);
}
