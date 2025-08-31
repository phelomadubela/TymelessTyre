/*
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */
package za.co.tt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tt.domain.User;
import za.co.tt.repository.UserRepository;
import za.co.tt.service.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User read(Long userId) {
        return repository.findById(userId).orElse(null);
    }

    @Override
    public User update(User user) {
        if (user != null && repository.existsById(user.getUserId())) {
            return repository.save(user);
        }
        return null;
    }

    @Override
    public boolean delete(Long userId) {
        if (repository.existsById(userId)) {
            repository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
}
