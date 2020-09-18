package walking.footwear.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import walking.footwear.model.Product;

import java.util.List;


public interface ProduceRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
}
