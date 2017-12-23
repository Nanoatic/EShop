package nano.project.eshop.repositories;

import nano.project.eshop.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("orderRepository")
public interface OrderRepository extends CrudRepository<Order,Long> {
}
