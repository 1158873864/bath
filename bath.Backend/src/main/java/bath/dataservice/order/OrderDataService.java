package bath.dataservice.order;

import bath.entity.order.Order;
import bath.exception.NotExistException;

public interface OrderDataService {
    Order findById(String id) throws NotExistException;
    void addOrder(Order order);
    void updateOrder(Order order);
}
