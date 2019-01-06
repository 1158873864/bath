package bath.data.order;

import bath.data.dao.order.OrderDao;
import bath.dataservice.order.OrderDataService;
import bath.entity.order.Order;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDataServiceImpl implements OrderDataService {
    private final OrderDao orderDao;
    @Autowired
    public OrderDataServiceImpl(OrderDao orderDao){
        this.orderDao=orderDao;
    }

    @Override
    public Order findById(String id) throws NotExistException {
        Optional<Order> optionalOrder = orderDao.findById(id);
        if(optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            throw new NotExistException("订单id", id);
        }
    }

    @Override
    public void addOrder(Order order) {
        orderDao.save(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDao.save(order);
    }
}
