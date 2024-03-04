package ecommerce.ecommerce.services;

import ecommerce.ecommerce.dtos.ProductRequestDto;
import ecommerce.ecommerce.exceptions.InvalidProductIdException;
import ecommerce.ecommerce.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    public Product getProductById(Long id) throws InvalidProductIdException;

    Product updateProduct(Long id, ProductRequestDto requestDto);

    List<Product> getAllProducts();
}
