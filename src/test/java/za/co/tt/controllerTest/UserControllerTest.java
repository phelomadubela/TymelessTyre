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
import za.co.tt.domain.User;
import za.co.tt.domain.UserRole;
import za.co.tt.factory.UserFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private static User user = UserFactory.createUser(
            null, "Joshua", "Brown", "josh.brown@gmail.com",
            "password123", "0812345678", LocalDate.now(),
            UserRole.CUSTOMER, true, null, null
    );

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/user";
    }

    @Test
    void a_create() {
        String url = getBaseUrl() + "/create";
        ResponseEntity<User> postResponse = restTemplate.postForEntity(url, user, User.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        User savedUser = postResponse.getBody();
        System.out.println("Saved data: " + savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        user = savedUser;
    }

    @Test
    void b_read() {
        String url = getBaseUrl() + "/read/" + user.getUserId();
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        assertNotNull(response.getBody());
        assertEquals(user.getUserId(), response.getBody().getUserId());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void c_update() {
        User updated = new User.Builder().copy(user).setFirstName("Jane").build();
        String url = getBaseUrl() + "/update";
        ResponseEntity<User> response = restTemplate.postForEntity(url, updated, User.class);
        assertNotNull(response.getBody());
        System.out.println("Updated: " + response.getBody());
        user = response.getBody();
    }

    @Disabled
    void d_delete() {
        String url = getBaseUrl() + "/delete/" + user.getUserId();
        restTemplate.delete(url);
        System.out.println("Deleted user with ID: " + user.getUserId());
    }

    @Test
    void e_getAll() {
        String url = getBaseUrl() + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);
        assertNotNull(response.getBody());
        System.out.println("All users: " + response.getBody());
    }
}
