package au.moodflip.service;
import java.util.List;

import au.moodflip.domain.Product;

public class SimpleProductManager implements ProductManager{
	private List<Product> products;
	
	public List<Product> getProducts(){
		return products;
	}
	
	public void increasePrice(int percentage){
		if (products != null){
			double markup = 1 + ((double)percentage / 100);
			for (Product product : products){
				product.setPrice(product.getPrice() * markup);
			}
		}
	}
	
	public void setProducts(List<Product> products){
		this.products = products;
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product getProductById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(long id) {
		// TODO Auto-generated method stub
		
	}
}
