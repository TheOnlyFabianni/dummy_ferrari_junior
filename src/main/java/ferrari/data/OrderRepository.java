package ferrari.data;

import org.springframework.data.repository.CrudRepository;

import ferrari.Order;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {

}
