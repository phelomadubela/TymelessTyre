package za.co.tt.service;

import za.co.tt.domain.Order;

public interface IOrderService {

    Order create(Order order);
    Order read(Long orderId);
    Order update(Order order);
    void delete(Long orderId);

}
