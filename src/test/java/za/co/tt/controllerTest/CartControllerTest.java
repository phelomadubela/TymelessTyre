package za.co.tt.controllerTest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import za.co.tt.domain.Cart;

import static org.junit.jupiter.api.Assertions.assertNotNull;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseUrl = "http://localhost:8080/cart";


    @TestMethodOrder(MethodOrderer.MethodName.class)
    @SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    public class CartControllerTest {

        @Autowired
        private TestRestTemplate restTemplate;

        private final String baseUrl = "http://localhost:8080/cart";

        @Test
        void a_create() {
            String url = baseUrl + "/create";
            ResponseEntity<Cart> postResponse = restTemplate.postForEntity(url, cart, Cart.class);
            assertNotNull(postResponse);
            assertNotNull(postResponse.getBody());
            Cart savedCart = postResponse.getBody();
            assertEquals(cart.getcartId(), savedCart.getcartId());
            System.out.println("Saved cart: " + savedCart);
        }

        @Test
        void b_read() {
            String url = baseUrl + "/read/" + cart.getcartId();
            System.out.println("URL: " + url);
            ResponseEntity<Cart> response = restTemplate.getForEntity(url, Cart.class);
            assertEquals(cart.getcartId(), response.getBody().getcartId());
            System.out.println(response.getBody());
        }

        @Test
        void c_update() {
            Cart updated = new Cart.Builder().copy(cart).setcartId(130L).build();
            String url = baseUrl + "/update";
            System.out.println("URL: " + url);
            System.out.println("Post data: " + updated);
            ResponseEntity<Cart> response = restTemplate.postForEntity(url, updated, Cart.class);
            assertNotNull(response.getBody());
            assertEquals(130L, response.getBody().getcartId());
            System.out.println("Updated cart id: " + response.getBody());
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

        @Test
        void e_delete() {
            String url = baseUrl + "/delete/" + cart.getcartId();
            restTemplate.delete(url);
            ResponseEntity<Cart> response = restTemplate.getForEntity(baseUrl + "/read/" + cart.getcartId(), Cart.class);
            assertNull(response.getBody());
            System.out.println("Deleted cart ID: " + cart.getcartId());
        }

    }

/*
    @Test
    void b_read() {
        String url = baseUrl + "/read/" + cart.getcartId();
        System.out.println("URL: " + url);
        ResponseEntity<Cart> response = restTemplate.getForEntity(url, Cart.class);
        assertEquals(cart.getcartId(), response.getBody().getcartId());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Cart updated = new Cart.Builder().copy("Cart id").setcartId(130L).build();
        String url = baseUrl + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);
        ResponseEntity<Cart> response = restTemplate.postForEntity(url, updated, Cart.class);
        assertNotNull(response.getBody());
        assertEquals("130C", response.getBody().getcartId()
        );
        System.out.println("Updated cart id: " + response.getBody());
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

    @Test
    void e_delete() {
        String url = baseUrl + "/delete/" + cart.getcartId();
        restTemplate.delete(url);
        ResponseEntity<Cart> response = restTemplate.getForEntity(baseUrl + "/read/" + cart.getcartId(), Cart.class);
        assertNull(response.getBody());
        System.out.println("Deleted cart ID: " + cart.getcartId());
    }

 */




