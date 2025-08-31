/*UserRepository
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */
package za.co.tt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.tt.domain.User;
import za.co.tt.domain.UserRole;
import za.co.tt.service.IService;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findByPhone(String phone);
    List<User> findByIsActive(Boolean isActive);
    List<User> findAllByRole(UserRole role);
}