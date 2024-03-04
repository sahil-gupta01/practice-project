package ecommerce.ecommerce.services;

import ecommerce.ecommerce.dtos.ProductRequestDto;
import ecommerce.ecommerce.exceptions.InvalidProductIdException;
import ecommerce.ecommerce.models.Category;
import ecommerce.ecommerce.models.Product;
import ecommerce.ecommerce.repositories.CategoryRepository;
import ecommerce.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("selfProductService")
public class SelfProductService implements IProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new InvalidProductIdException();
        }
        Product product = productOptional.get();
        return product;
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto requestDto) throws InvalidProductIdException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new InvalidProductIdException();
        }
        Product existProduct = productOptional.get();
        Product updatedProduct = new Product();

        updatedProduct.setId(existProduct.getId());

        updatedProduct.setAmount(
                requestDto.getPrice() > 0 ?
                        requestDto.getPrice() : existProduct.getAmount()
        );
        updatedProduct.setName(
                requestDto.getTitle() != null ?
                        requestDto.getTitle() : existProduct.getName()
        );
        updatedProduct.setDescription(
                requestDto.getDescription() != null ?
                        requestDto.getDescription() : existProduct.getDescription()
        );
        updatedProduct.setImage(
                requestDto.getImage() != null ?
                        requestDto.getImage() : existProduct.getImage()
        );
        Optional<Category> categoryOptional = categoryRepository.findByName(requestDto.getCategory());
        if (categoryOptional.isEmpty()) {
            Category categoryToSave = new Category();
            categoryToSave.setName(requestDto.getCategory());
            Category savedCategory = categoryRepository.save(categoryToSave);
            updatedProduct.setCategory(savedCategory);
        }
        else{
            updatedProduct.setCategory(categoryOptional.get());
        }
        Product savedProduct = productRepository.save(updatedProduct);
        return savedProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
        if (categoryOptional.isEmpty()) {
            Category categoryToSave = new Category();
            categoryToSave.setName(product.getCategory().getName());
            Category savedCategory = categoryRepository.save(categoryToSave);
            product.setCategory(savedCategory);
        }
        else{
            product.setCategory(categoryOptional.get());
        }
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }
}
