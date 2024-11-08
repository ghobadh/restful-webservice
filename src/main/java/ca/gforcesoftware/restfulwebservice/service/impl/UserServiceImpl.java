package ca.gforcesoftware.restfulwebservice.service.impl;

import ca.gforcesoftware.restfulwebservice.converter.MapToUserDto;
import ca.gforcesoftware.restfulwebservice.converter.MapToUser;
import ca.gforcesoftware.restfulwebservice.dto.UserDto;
import ca.gforcesoftware.restfulwebservice.entity.User;
import ca.gforcesoftware.restfulwebservice.repository.UserRepository;
import ca.gforcesoftware.restfulwebservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author gavinhashemi on 2024-11-07
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapToUserDto mapToUserDto = new MapToUserDto();
    private final MapToUser mapToUser = new MapToUser();
    // I don't need this as I can use lombok AllArgConstructor
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }


    @Override
    public UserDto createUser(UserDto user) {

        User savedUser = userRepository.save(mapToUser.convert(user));
        return mapToUserDto.convert(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser =  userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return mapToUserDto.convert(optionalUser.get());
        } else {
            return null;
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> userDtos.add(mapToUserDto.convert(user)));
        return userDtos;
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User userToUpdate = userRepository.findById(user.getId()).get();
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        UserDto userDto = mapToUserDto.convert(userRepository.save(userToUpdate));
        return userDto;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
