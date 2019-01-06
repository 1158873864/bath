package bath.blservice.order;

import bath.exception.NotExistException;
import bath.response.order.OrderResponse;
import bath.response.order.OrderResponseItem;
import bath.response.order.WxOrderResponse;

import java.util.List;

public interface OrderBlService {
    /**
     * 用户提交订单
     * @param openid
     * @param orderId
     * @return
     */
    WxOrderResponse submitOrder(String openid, String orderId)throws NotExistException;

    /**
     * 用户创建订单
     * @param openid
     * @param orderItems
     * @return
     */
    OrderResponse addOrder(String openid, List<OrderResponseItem> orderItems)throws NotExistException;
}
