/*
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */

package za.co.tt.serviceTest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.tt.domain.User;
import za.co.tt.domain.UserRole;
import za.co.tt.service.impl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    public static User user = new User.Builder()
            .setFirstName("Jonh")
            .setLastName("Van Wyk")
            .setEmail("john.vanwyk@gmail.com")
            .setPassword("password123")
            .setPhone("0812345678")
            .setRole(UserRole.CUSTOMER)
            .setIsActive(true)
            .build();

    @Test
    void a_create() {
        System.out.print("Created: ");
        User created = userServiceImpl.create(user);
        assertNotNull(created, "User should be created");
        System.out.println(created);
    }

    @Test
    void b_read() {
        System.out.println("Read: ");
        User read = userServiceImpl.read(user.getUserId());
        assertEquals(read.getUserId(), user.getUserId(), "IDs should match");
        System.out.println(read);
    }

    @Test
    void c_update() {
        System.out.println("Updated: ");
        User updated = userServiceImpl.update(new User.Builder().copy(user)
                .setFirstName("Jane")
                .build());
        assertNotNull(updated, "User should be updated");
        assertEquals("Jane", updated.getFirstName(), "First name should be updated");
        System.out.println(updated);
    }

    @Test
    void d_delete() {
        System.out.println("Deleted: ");
        boolean success = userServiceImpl.delete(user.getUserId());
        assertTrue(success, "User should be deleted");
        System.out.println(success);
    }

    @Test
    void e_getAll() {
        System.out.println("Get all: ");
        System.out.println(userServiceImpl.getAll());
    }
}
