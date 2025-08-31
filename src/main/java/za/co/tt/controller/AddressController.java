/*
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */
package za.co.tt.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.tt.domain.Address;
import za.co.tt.service.impl.AddressServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressServiceImpl service;

    @Autowired
    public AddressController(AddressServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Address create(@RequestBody Address address) {
        return service.create(address);
    }

    @GetMapping("/read/{id}")
    public Address read(@PathVariable Long id) {
        return service.read(id);
    }

    @PostMapping("/update")
    public Address update(@RequestBody Address address) {
        return service.update(address);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("/getAll")
    public List<Address> getAll() {
        return service.getAll();
    }
}
