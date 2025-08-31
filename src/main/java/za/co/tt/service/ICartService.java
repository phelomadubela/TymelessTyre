package za.co.tt.service;

import za.co.tt.domain.Cart;

public interface ICartService {

    Cart create(Cart cart);
    Cart read(Long cartId);
    Cart update(Cart cart);
    void delete(Long cartId);
}
