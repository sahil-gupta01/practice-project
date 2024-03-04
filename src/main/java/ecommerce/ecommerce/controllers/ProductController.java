package ecommerce.ecommerce.controllers;


import ecommerce.ecommerce.dtos.ErrorResponseDto;
import ecommerce.ecommerce.dtos.ProductRequestDto;
import ecommerce.ecommerce.dtos.ProductWrapper;
import ecommerce.ecommerce.exceptions.InvalidProductIdException;
import ecommerce.ecommerce.models.Category;
import ecommerce.ecommerce.models.Product;
import ecommerce.ecommerce.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private IProductService productService;

    @Autowired
    public ProductController(@Qualifier("selfProductService") IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductWrapper> getProductById(@PathVariable("id") Long id) throws InvalidProductIdException {

        ResponseEntity<ProductWrapper> response;
        Product product = productService.getProductById(id);
        ProductWrapper wrapper = new ProductWrapper(product, "Successfully retrieved the data");
        response = new ResponseEntity<>(wrapper, HttpStatus.OK);

        return response;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductRequestDto requestDto){
        Product product = new Product();

        product.setName(requestDto.getTitle());
        product.setDescription(requestDto.getDescription());
        product.setImage(requestDto.getImage());
        product.setAmount(requestDto.getPrice());

        product.setCategory(new Category());
        product.getCategory().setName(requestDto.getCategory());

        Product finalProduct = productService.addProduct(product);
        return finalProduct;
    }

//    For FakeStore API

//    @PutMapping("/products/{id}")
//    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDto requestDto){
//        return productService.updateProduct(id, requestDto);
//    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDto requestDto) throws InvalidProductIdException {
        Product product = new Product();

        product.setId(id);
        product.setName(requestDto.getTitle());
        product.setDescription(requestDto.getDescription());
        product.setImage(requestDto.getImage());
        product.setAmount(requestDto.getPrice());

        product.setCategory(new Category());
        product.getCategory().setName(requestDto.getCategory());

        Product finalProduct = productService.updateProduct(id, requestDto);
        return finalProduct;
    }

    @DeleteMapping("/products/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id){
        return true;
    }

}
