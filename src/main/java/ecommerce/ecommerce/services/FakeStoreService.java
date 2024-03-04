package ecommerce.ecommerce.services;

import ecommerce.ecommerce.dtos.ProductRequestDto;
import ecommerce.ecommerce.dtos.ProductResponseDto;
import ecommerce.ecommerce.exceptions.InvalidProductIdException;
import ecommerce.ecommerce.models.Category;
import ecommerce.ecommerce.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreService implements IProductService{
    @Autowired
    RestTemplate restTemplate;
    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        if(id > 20){
            throw new InvalidProductIdException();
        }
        ProductResponseDto response = restTemplate.getForObject
                                            ("https://fakestoreapi.com/products/" + id, ProductResponseDto.class);
        return getProductFromResponse(response);

    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto requestDto) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestDto, ProductRequestDto.class);
        HttpMessageConverterExtractor<ProductResponseDto> responseExtractor =
                new HttpMessageConverterExtractor(ProductResponseDto.class, restTemplate.getMessageConverters());
        ProductResponseDto responseDto = restTemplate.execute("https://fakestoreapi.com/products/" + id,
                                                            HttpMethod.PUT, requestCallback, responseExtractor);
        return getProductFromResponse(responseDto);
    }

    @Override
    public List<Product> getAllProducts() {
        ProductResponseDto[] responseDtoList = restTemplate.getForObject
                                        ("https://fakestoreapi.com/products", ProductResponseDto[].class);
        List<Product> products = new ArrayList<>();
        for(ProductResponseDto responseDto: responseDtoList){
            products.add(getProductFromResponse(responseDto));
        }
        return products;
    }

    private Product getProductFromResponse(ProductResponseDto response) {
        Product product = new Product();
        product.setAmount(response.getPrice());
        product.setName(response.getTitle());
        product.setId(response.getId());
        product.setImage(response.getImage());
        product.setDescription(response.getDescription());

        Category category = new Category();
        category.setName(response.getCategory());

        product.setCategory(category);

        return product;
    }
}
