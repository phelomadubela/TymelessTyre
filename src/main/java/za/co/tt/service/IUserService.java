/*UserService
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */

package za.co.tt.service;

import za.co.tt.domain.User;
import java.util.List;

public interface IUserService {
    User create(User user);
    User read(Long userId);
    User update(User user);
    boolean delete(Long userId);
    List<User> getAll();
}