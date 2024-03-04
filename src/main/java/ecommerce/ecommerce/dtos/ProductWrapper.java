package ecommerce.ecommerce.dtos;

import ecommerce.ecommerce.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductWrapper {
    private Product product;
    private String message;
}
