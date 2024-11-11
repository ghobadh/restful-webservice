package ca.gforcesoftware.restfulwebservice.service.impl;

import ca.gforcesoftware.restfulwebservice.converter.AutoUserMapper;
import ca.gforcesoftware.restfulwebservice.converter.MapToUserDto;
import ca.gforcesoftware.restfulwebservice.converter.MapToUser;
import ca.gforcesoftware.restfulwebservice.dto.UserDto;
import ca.gforcesoftware.restfulwebservice.entity.User;
import ca.gforcesoftware.restfulwebservice.exception.UserNotFoundException;
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
    //This is added for Converting the User to USerDto
    //I defined the bean in RestfulWebserviceApplication and in here I am using @AllArgConstructor
    // so I don't need to do
    // anything else to define the modelMapper
   // private ModelMapper modelMapper;

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
        //User savedUser = userRepository.save(mapToUser.convert(userDto)); <-- Old style
        //User savedUser = userRepository.save(modelMapper.map(userDto, User.class));// <-- ModelMapper
      User savedUser = userRepository.save(AutoUserMapper.INSTANCE.mapToUser(userDto)); //<-- MapStruct
        //return mapToUserDto.convert(savedUser); <-- old style
        //return modelMapper.map(savedUser, UserDto.class); <-- ModelMapper
        return AutoUserMapper.INSTANCE.mapToUserDto(savedUser);


    }

    @Override
    public UserDto getUserById(Long id) {
        // for handling "not found id", I need to add orElseThrow and remove the Optional of the field
        User optionalUser =  userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User","id",id)
        );
            //return mapToUserDto.convert(optionalUser.get()); <-- old style
             //return modelMapper.map(optionalUser.get(), UserDto.class);// <-- ModelMapper
            return AutoUserMapper.INSTANCE.mapToUserDto(optionalUser); // <-- MapStruct
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        //users.forEach(user -> userDtos.add(mapToUserDto.convert(user))); <-- old style
        //users.forEach(user -> userDtos.add(modelMapper.map(user, UserDto.class)));// <-- ModelMapper
        users.forEach(user -> userDtos.add(AutoUserMapper.INSTANCE.mapToUserDto(user)));  //<-- MapStruct
        return userDtos;
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User userToUpdate = userRepository.findById(user.getId()).orElseThrow(
                () -> new UserNotFoundException("User","id",user.getId())
        );
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        //UserDto userDto = mapToUserDto.convert(userRepository.save(userToUpdate)); <-- old style
        //return modelMapper.map(userRepository.save(userToUpdate), UserDto.class); //<-- ModelMapper
        return AutoUserMapper.INSTANCE.mapToUserDto(userRepository.save(userToUpdate));///<-- Mapstruct
    }

    @Override
    public void deleteUser(Long id) {
        User userToUpdate = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User","id",id)
        );
        userRepository.deleteById(id);
    }
}
