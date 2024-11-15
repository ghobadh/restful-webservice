package ca.gforcesoftware.restfulwebservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gavinhashemi on 2024-11-07
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    //User first name and last name should not be empty
    @NotEmpty(message = "User first name should not be null or empty")
    private String firstName;

    @NotEmpty(message = "User last name should not be null or empty")
    private String lastName;

    // email address should be valid and not empty
    @NotEmpty(message = "User email address should not be null or empty")
    @Email(message =  " email address should be valid")
    private String email;
}
