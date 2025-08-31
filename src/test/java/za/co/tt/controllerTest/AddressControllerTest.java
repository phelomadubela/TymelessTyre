/*
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */
package za.co.tt.controllerTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.co.tt.domain.Address;
import za.co.tt.factory.AddressFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressControllerTest {

    private static Address address = AddressFactory.createAddress(
            02L, "123 Main Street",
            "Cape Town", "Western Cape", 8000, "South Africa",
            true, null, LocalDate.now(), LocalDate.now()
    );

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/address";
    }

    @Test
    void a_create() {
        String url = getBaseUrl() + "/create";
        ResponseEntity<Address> postResponse = restTemplate.postForEntity(url, address, Address.class);

        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Address savedAddress = postResponse.getBody();
        System.out.println("Saved data: " + savedAddress);

        assertEquals(address.getStreet(), savedAddress.getStreet());
        address = savedAddress; // update static variable for next tests
    }

    @Test
    void b_read() {
        String url = getBaseUrl() + "/read/" + address.getAddressId();
        ResponseEntity<Address> response = restTemplate.getForEntity(url, Address.class);

        assertNotNull(response.getBody());
        assertEquals(address.getAddressId(), response.getBody().getAddressId());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void c_update() {
        Address updated = new Address.Builder().copy(address).setStreet("456 New Street").build();
        String url = getBaseUrl() + "/update";

        ResponseEntity<Address> response = restTemplate.postForEntity(url, updated, Address.class);
        assertNotNull(response.getBody());

        System.out.println("Updated: " + response.getBody());
        address = response.getBody();
    }

    @Disabled
    void d_delete() {
        String url = getBaseUrl() + "/delete/" + address.getAddressId();
        restTemplate.delete(url);
        System.out.println("Deleted address with ID: " + address.getAddressId());
    }

    @Test
    void e_getAll() {
        String url = getBaseUrl() + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);
        assertNotNull(response.getBody());

        System.out.println("All addresses: " + response.getBody());
    }
}
