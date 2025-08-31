/*UserFactory
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */
package za.co.tt.factory;

import za.co.tt.domain.Address;
import za.co.tt.domain.Payment;
import za.co.tt.domain.User;
import za.co.tt.domain.UserRole;
import za.co.tt.util.Helper;

import java.time.LocalDate;
import java.util.List;

public class UserFactory {

    public static User createUser(Long userId,
                                  String firstName,
                                  String lastName,
                                  String email,
                                  String password,
                                  String phone,
                                  LocalDate createdAt,
                                  UserRole role,
                                  Boolean isActive,
                                  List<Address> addresses,
                                  List<Payment> payments) {

        if (Helper.isNullOrEmpty(firstName) ||
                Helper.isNullOrEmpty(lastName) ||
                !Helper.isValidEmail(email) ||
                Helper.isNullOrEmpty(password) ||
                !Helper.isValidMobile(phone) ||
                createdAt == null ||
                role == null ||
                isActive == null) {
            return null;
        }

        if (addresses != null) {
            for (Address address : addresses) {
                address.setUser(null);
            }
        }

        return new User.Builder()
                .setUserId(userId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setPhone(phone)
                .setCreatedAt(createdAt)
                .setRole(role)
                .setIsActive(isActive)
                .setAddresses(addresses)
                .setPayments(payments)
                .build();
    }
}
