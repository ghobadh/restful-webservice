package ca.gforcesoftware.restfulwebservice.converter;

import ca.gforcesoftware.restfulwebservice.dto.UserDto;
import ca.gforcesoftware.restfulwebservice.entity.User;
import org.springframework.core.convert.converter.Converter;

/**
 * @author gavinhashemi on 2024-11-07
 */
public class MapToUser implements Converter<UserDto,User> {

    @Override
    public User convert(UserDto source) {
        return new User(
                source.getId(),
                source.getFirstName(),
                source.getLastName(),
                source.getEmail()
        );

    }
}
