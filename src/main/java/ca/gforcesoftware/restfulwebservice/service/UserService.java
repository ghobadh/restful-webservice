package ca.gforcesoftware.restfulwebservice.service;

import ca.gforcesoftware.restfulwebservice.entity.User;

import java.util.List;

/**
 * @author gavinhashemi on 2024-11-07
 */
public interface UserService {
    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long id);
}
