/*AddressService
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */

package za.co.tt.service;


import za.co.tt.domain.Address;
import java.util.List;

public interface IAddressService {
    Address create(Address address);
    Address read(Long addressId);
    Address update(Address address);
    boolean delete(Long addressId);
    List<Address> getAll();
}
