package bath.bl.order;

import bath.dataservice.order.OrderDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderBlServiceImpl {
    private final OrderDataService orderDataService;
    @Autowired
    public OrderBlServiceImpl(OrderDataService orderDataService){
        this.orderDataService=orderDataService;
    }
}
