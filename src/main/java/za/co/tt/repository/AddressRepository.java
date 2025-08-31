/*AddressRepository
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */

package za.co.tt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.tt.domain.Address;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByStreetAndCity(String street, String city);
    Address findByPostalCode(int postalCode);
    Address findByStreetAndPostalCode(String street, int postalCode);
    Address findByCityAndPostalCode(String city, int postalCode);
    List<Address> findAllByCity(String city);
    boolean existsByAddressId(Long addressId);
}
