package nano.project.eshop.services;

import nano.project.eshop.models.OrderLine;
import nano.project.eshop.repositories.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderLineService")
public class OrderLineService {
    private OrderLineRepository orderLineRepository;

    @Autowired
    public OrderLineService(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    public OrderLine saveOrderLine(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }
}
