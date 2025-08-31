/*AddressFactoryTest
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */

package za.co.tt.factoryTest;

import org.junit.jupiter.api.Test;
import za.co.tt.domain.Address;
import za.co.tt.domain.User;
import za.co.tt.factory.AddressFactory;
import za.co.tt.factory.UserFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import static za.co.tt.domain.UserRole.CUSTOMER;

public class AddressFactoryTest {

    User testUser = UserFactory.createUser(12L, "John", "Doe",
            "doe@gmail.com",
            "psd123", "0780298461", LocalDate.now(), CUSTOMER, true, null,
            null);


    @Test
    public void createAddressWithNullUserShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AddressFactory.createAddress(102L, "123 Hanover st", "Cape Town",
                    "Western Cape", 8000, "South Africa", true, null,
                    LocalDate.now(),
                    LocalDate.now());
        });
        System.out.println("Expected exception: " + exception.getMessage());
    }


    @Test
    public void createAddressWithValidUser() {
        Address address2 = AddressFactory.createAddress(1021L, "456 Micheal st", "Strand",
                "Western Cape", 7140, "South Africa", false, testUser,
                LocalDate.now(),
                LocalDate.now());

        assertNotNull(address2);
        System.out.println(address2);
    }

    @Test
    public void createAnotherAddressWithValidUser() {
        Address address3 = AddressFactory.createAddress(1022L, "ST Marks", "Cape Town",
                "Western Cape", 8002, "South Africa", true, testUser,
                LocalDate.now(),
                LocalDate.now());

        assertNotNull(address3);
        System.out.println(address3);
    }
}
