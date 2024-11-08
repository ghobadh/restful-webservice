package ca.gforcesoftware.restfulwebservice.converter;

import ca.gforcesoftware.restfulwebservice.dto.UserDto;
import ca.gforcesoftware.restfulwebservice.entity.User;
import org.springframework.core.convert.converter.Converter;

/**
 * @author gavinhashemi on 2024-11-07
 */
public class MapToUserDto implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        UserDto userDto = new UserDto(
                source.getId(),
                source.getFirstName(),
                source.getLastName(),
                source.getEmail()
        );
        return userDto;
    }
}
