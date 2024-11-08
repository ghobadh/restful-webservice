package ca.gforcesoftware.restfulwebservice.service.impl;

import ca.gforcesoftware.restfulwebservice.converter.MapToUserDto;
import ca.gforcesoftware.restfulwebservice.converter.MapToUser;
import ca.gforcesoftware.restfulwebservice.dto.UserDto;
import ca.gforcesoftware.restfulwebservice.entity.User;
import ca.gforcesoftware.restfulwebservice.repository.UserRepository;
import ca.gforcesoftware.restfulwebservice.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    //I defined the bean in RestfulWebserviceApplication and in here I am using @AllArgConstructor
    // so I don't need to do
    // anything else to define the modelMapper
    private ModelMapper modelMapper;

    private final UserRepository userRepository;
    private final MapToUserDto mapToUserDto = new MapToUserDto();
    private final MapToUser mapToUser = new MapToUser();
    // I don't need this as I can use lombok AllArgConstructor
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }


    @Override
    public UserDto createUser(UserDto userDto) {
        //Convert UserDto into User JPA Entity
        //User savedUser = userRepository.save(mapToUser.convert(userDto));
        User savedUser = userRepository.save(modelMapper.map(userDto, User.class));
        //return mapToUserDto.convert(savedUser);
        return modelMapper.map(savedUser, UserDto.class);

    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser =  userRepository.findById(id);
        if (optionalUser.isPresent()) {
            //return mapToUserDto.convert(optionalUser.get());
            return modelMapper.map(optionalUser.get(), UserDto.class);
        } else {
            return null;
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        //users.forEach(user -> userDtos.add(mapToUserDto.convert(user)));
        users.forEach(user -> userDtos.add(modelMapper.map(user, UserDto.class)));
        return userDtos;
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User userToUpdate = userRepository.findById(user.getId()).get();
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        //UserDto userDto = mapToUserDto.convert(userRepository.save(userToUpdate));

        return modelMapper.map(userRepository.save(userToUpdate), UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
