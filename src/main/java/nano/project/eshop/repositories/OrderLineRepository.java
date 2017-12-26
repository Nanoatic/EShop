package nano.project.eshop.repositories;

import nano.project.eshop.models.OrderLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("orderLineRespository")
public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {

}
