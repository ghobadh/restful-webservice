package ca.gforcesoftware.restfulwebservice.service;

import ca.gforcesoftware.restfulwebservice.dto.UserDto;
import ca.gforcesoftware.restfulwebservice.entity.User;

import java.util.List;

/**
 * @author gavinhashemi on 2024-11-07
 */
public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

    void deleteUser(Long id);
}
