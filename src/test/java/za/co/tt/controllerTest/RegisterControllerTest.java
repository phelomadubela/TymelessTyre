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
import za.co.tt.domain.Register;
import za.co.tt.factory.RegisterFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegisterControllerTest {

    private static Register register = RegisterFactory.createRegister(30474L, "Alice", "Smith",
            "alicesmith@gmail.com", "ZEroden456", true, true, LocalDate.now()
    );

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseUrl = "http://localhost:8080/register";

    @Test
    void a_create() {
        String url = baseUrl + "/create";
        ResponseEntity<Register> postResponse = restTemplate.postForEntity(url, register, Register.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Register savedRegister = postResponse.getBody();
        System.out.println("Saved data: " + savedRegister);
        assertEquals(register.getUserId(), savedRegister.getUserId());
    }

    @Test
    void b_read() {
        String url = baseUrl + "/read/" + register.getUserId();
        System.out.println("URL: " + url);
        ResponseEntity<Register> response = restTemplate.getForEntity(url, Register.class);
        assertEquals(register.getUserId(), response.getBody().getUserId());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Register updated = new Register.Builder().copy(register).setFirstName("Johnny").build();
        String url = baseUrl + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);
        ResponseEntity<Register> response = restTemplate.postForEntity(url, updated, Register.class);
        assertNotNull(response.getBody());
    }

    @Test
    void e_delete() {
        String url = baseUrl + "/delete/" + register.getUserId();
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
