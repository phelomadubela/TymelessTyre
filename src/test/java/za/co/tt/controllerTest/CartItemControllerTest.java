/*Author: Bonke Bulana - 220539995*/
package za.co.tt.controllerTest;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.co.tt.domain.Cart;
import za.co.tt.domain.CartItem;
import za.co.tt.domain.Rim;
import za.co.tt.domain.Tyre;
import za.co.tt.factory.CartItemFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartItemControllerTest {

    private static CartItem cartItem = CartItemFactory.createCartItem(2001L, 10, 20,
            new Cart(), new Tyre(), new Rim()
    );

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseUrl = "http://localhost:8080/cartitem";

    @Test
    void a_create() {
        String url = baseUrl + "/create";
        ResponseEntity<CartItem> postResponse = restTemplate.postForEntity(url, cartItem, CartItem.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        CartItem savedCartItem = postResponse.getBody();
        System.out.println("Saved data: " + savedCartItem);
        assertEquals(cartItem.getCartItemId(), savedCartItem.getCartItemId());
    }

    @Test
    void b_read() {
        String url = baseUrl + "/read/" + cartItem.getCartItemId();
        System.out.println("URL: " + url);
        ResponseEntity<CartItem> response = restTemplate.getForEntity(url, CartItem.class);
        assertEquals(cartItem.getCartItemId(), response.getBody().getCartItemId());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        CartItem updated = new CartItem.Builder().copy(cartItem).setQuantity(5).build();
        String url = baseUrl + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);
        ResponseEntity<CartItem> response = restTemplate.postForEntity(url, updated, CartItem.class);
        assertNotNull(response.getBody());
    }

    @Test
    void e_delete() {
        String url = baseUrl + "/delete/" + cartItem.getCartItemId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
    }

    @Test
    void d_getAll() {
        String url = baseUrl + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show ALL: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}
