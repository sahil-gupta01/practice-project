package ecommerce.ecommerce.repositories;

import ecommerce.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<Product> findById(Long id);
    List<Product> findByNameAndDescriptionAndAmountGreaterThan(String name, String description, int amount);

    List<Product> findTop5DistinctProductByName(String name);

    Product save(Product product);

}
