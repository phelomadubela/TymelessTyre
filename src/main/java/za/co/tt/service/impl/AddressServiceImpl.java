/*
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */
package za.co.tt.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tt.domain.Address;
import za.co.tt.repository.AddressRepository;
import za.co.tt.service.IAddressService;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository repository;

    @Autowired
    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address create(Address address) {
        return repository.save(address);
    }

    @Override
    public Address read(Long Id) {
        return repository.findById(Id).orElse(null);
    }

    @Override
    public Address update(Address address) {
        if (repository.existsByAddressId(address.getAddressId())) {
            return repository.save(address);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Address> getAll() {
        return repository.findAll();
    }
}
