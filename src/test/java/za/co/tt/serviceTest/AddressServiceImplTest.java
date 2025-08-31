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
import za.co.tt.domain.Address;
import za.co.tt.service.impl.AddressServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static za.co.tt.serviceTest.UserServiceImplTest.user;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
class AddressServiceImplTest {

    @Autowired
    private AddressServiceImpl addressServiceImpl;

    private static final Address address = new Address.Builder()
            .setStreet("Richmond Street")
            .setCity("Cape Town")
            .setProvince("Strand")
            .setPostalCode(7141)
            .setCountry("South Africa")
            .setUser(user)
            .build();

    @Test
    void a_create() {
        System.out.print("Created: ");
        Address created = addressServiceImpl.create(address);
        address.setAddressId(created.getAddressId());
        assertNotNull(created, "Address should be created");
        System.out.println(created);
    }

    @Test
    void b_read() {
        System.out.println("Read: ");
        Address read = addressServiceImpl.read(address.getAddressId());
        assertEquals(read.getAddressId(), address.getAddressId(), "IDs should match");
        System.out.println(read);
    }

    @Test
    void c_update() {
        System.out.println("Updated: ");
        Address updated = addressServiceImpl.update(new Address.Builder().copy(address)
                .setStreet("12 Flick Street")
                .build());
        assertNotNull(updated, "Address should be updated");
        assertEquals("12 Flick Street", updated.getStreet(), "Street should be updated");
        System.out.println(updated);
    }

    @Test
    void d_delete() {
        System.out.println("Deleted: ");
        boolean success = addressServiceImpl.delete(address.getAddressId());
        assertTrue(success, "Address should be deleted");
        System.out.println(success);
    }

    @Test
    void e_findAll() {
        System.out.println("Get all: ");
        System.out.println(addressServiceImpl.getAll());
    }
}
