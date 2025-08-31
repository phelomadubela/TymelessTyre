/*UserFactoryTest
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */

package za.co.tt.factoryTest;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.co.tt.domain.User;
import za.co.tt.domain.UserRole;
import za.co.tt.factory.UserFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserFactoryTest {

    static User user1 = UserFactory.createUser(
            1L, "John", "Doe", "doe@gmail.com",
            "password123", "0782984711", LocalDate.now(),
            UserRole.SELLER, true, null, null);

    static User user2 = UserFactory.createUser(
            2L, "Luyanda", "Mabasa", "mabasal@gmail.com",
            "psd2025", "0797192985", LocalDate.now(),
            UserRole.CUSTOMER, true, null, null);

    @Test
    @Order(1)
    public void createUserTestThatPasses() {
        assertNotNull(user1, "User1 should not be null");
        assertNotNull(user2, "User2 should not be null");
        System.out.println("User1: " + user1);
        System.out.println("User2: " + user2);
    }
}
