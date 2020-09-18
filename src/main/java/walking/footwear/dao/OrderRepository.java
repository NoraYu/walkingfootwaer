package walking.footwear.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import walking.footwear.model.Order;
@RepositoryRestResource(collectionResourceRel = "order", path = "order")
public interface OrderRepository extends JpaRepository<Order, Long> {
}
