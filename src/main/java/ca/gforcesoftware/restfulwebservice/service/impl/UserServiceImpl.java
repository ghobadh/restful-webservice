package ca.gforcesoftware.restfulwebservice.service.impl;

import ca.gforcesoftware.restfulwebservice.entity.User;
import ca.gforcesoftware.restfulwebservice.repository.UserRepository;
import ca.gforcesoftware.restfulwebservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author gavinhashemi on 2024-11-07
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    // I don't need this as I can use lombok AllArgConstructor
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }


    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }
}
