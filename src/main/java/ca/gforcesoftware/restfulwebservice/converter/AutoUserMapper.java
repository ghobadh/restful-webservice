package ca.gforcesoftware.restfulwebservice.converter;

import ca.gforcesoftware.restfulwebservice.dto.UserDto;
import ca.gforcesoftware.restfulwebservice.entity.User;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.core.convert.converter.Converter;

/**
 * @author gavinhashemi on 2024-11-08
 */
@Mapper//(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AutoUserMapper extends Converter<User , UserDto> {
    // I need to use this line for implementing the interface from the Mapper factory
    AutoUserMapper INSTANCE = Mappers.getMapper(AutoUserMapper.class);

    // if the fields are different name they have , I need to use @Mapping like this
    //@Mapping(source = "email", target= "emailAddress")
       UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
