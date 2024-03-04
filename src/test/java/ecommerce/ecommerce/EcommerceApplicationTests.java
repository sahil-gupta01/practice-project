package ecommerce.ecommerce;

import ecommerce.ecommerce.models.Product;
import ecommerce.ecommerce.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class EcommerceApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void getSomeData(){
		Optional<Product> productOptional = productRepository.findByName("iphone");
		if (productOptional.isEmpty()) {
			return;
		}
		Product product = productOptional.get();
		System.out.println(product.getName() + "  " + product.getAmount() + "  " + product.getId());
	}

	@Test
	void getListData(){
		List<Product> products = productRepository.findTop5DistinctProductByName("iphone");
		if (products.isEmpty()) {
			return;
		}
		for(Product product:products){
			System.out.println(product.getId() + "  " + product.getName());
		}
	}
}
