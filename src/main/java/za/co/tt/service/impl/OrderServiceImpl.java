package za.co.tt.service.impl;

import org.springframework.stereotype.Service;
import za.co.tt.domain.Order;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){ this.orderRepository = orderRepository;}

    @Override
    public Order getById(Long id){ return orderRepository.findById(id).orElse(null);}

    @Override
    public Order createOrder(Order order){return orderRepository.save(order);}

    @Override
    public Order updateOrder(Long id, Order order){
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders(){return orderRepository.findAll();}

   @Override
    public void deletedOrder(Long id){orderRepository.deleteById(id);}
}
