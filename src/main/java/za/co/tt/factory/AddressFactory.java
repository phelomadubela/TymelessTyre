/*AddressFactory
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */
package za.co.tt.factory;

import za.co.tt.domain.Address;
import za.co.tt.domain.User;
import za.co.tt.util.Helper;

import java.time.LocalDate;

public class AddressFactory {

    public static Address createAddress(Long addressId, String street, String city, String province,
                                        int postalCode, String country, Boolean isDefault,
                                        User user, LocalDate createdAt, LocalDate updatedAt) {


        if (addressId == null || Helper.isNullOrEmpty(street) || Helper.isNullOrEmpty(city)
                || Helper.isNullOrEmpty(province) || !Helper.isValidPostalCode(postalCode)
                || Helper.isNullOrEmpty(country) || isDefault == null
                || user == null || createdAt == null || updatedAt == null) {
            return null;
        }

        return new Address.Builder()
                .setAddressId(addressId)
                .setStreet(street)
                .setCity(city)
                .setProvince(province)
                .setPostalCode(postalCode)
                .setCountry(country)
                .setIsDefault(isDefault)
                .setUser(user)
                .setCreatedAt(createdAt)
                .setUpdatedAt(updatedAt)
                .build();

    }
}
