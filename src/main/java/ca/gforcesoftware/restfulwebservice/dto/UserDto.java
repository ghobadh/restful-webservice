package ca.gforcesoftware.restfulwebservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gavinhashemi on 2024-11-07
 */

@Schema(
        description = "UserDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;


    @Schema(
            description = "User First Name"
    )
    //User first name and last name should not be empty
    @NotEmpty(message = "User first name should not be null or empty")
    private String firstName;

    @Schema(
            description = "User Last Name"
    )
    @NotEmpty(message = "User last name should not be null or empty")
    private String lastName;

    @Schema(
            description = "User email"
    )
    // email address should be valid and not empty
    @NotEmpty(message = "User email address should not be null or empty")
    @Email(message =  " email address should be valid")
    private String email;
}
