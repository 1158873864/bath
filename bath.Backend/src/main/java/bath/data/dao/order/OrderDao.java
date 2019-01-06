package bath.data.dao.order;

import bath.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, String> {
}
