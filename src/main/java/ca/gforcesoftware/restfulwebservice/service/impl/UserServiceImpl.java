package ca.gforcesoftware.restfulwebservice.service.impl;

import ca.gforcesoftware.restfulwebservice.entity.User;
import ca.gforcesoftware.restfulwebservice.repository.UserRepository;
import ca.gforcesoftware.restfulwebservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser =  userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User userToUpdate = userRepository.findById(user.getId()).get();
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
