package za.co.tt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tt.domain.Cart;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService{

    private final CartRepository repository;

    @Autowired
    public CartServiceImpl(CartRepository repository){this.repository = repository;}

    @Override
    public Cart create(Cart cart){
        return repository.save(cart);
    }

    @Override
    public Cart read(Long cartId){
        return repository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public Cart update(Cart cart){
        return repository.save(cart);
    }

    @Override
    public void delete(Long cartId){
        repository.deleteById(cartId);
    }
    @Override
    public List<Cart> findAll(){return repository.findAll();}

}
