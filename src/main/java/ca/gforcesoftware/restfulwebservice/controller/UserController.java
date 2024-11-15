package ca.gforcesoftware.restfulwebservice.controller;

import ca.gforcesoftware.restfulwebservice.dto.UserDto;
import ca.gforcesoftware.restfulwebservice.entity.User;
import ca.gforcesoftware.restfulwebservice.exception.ErrorDetails;
import ca.gforcesoftware.restfulwebservice.exception.UserNotFoundException;
import ca.gforcesoftware.restfulwebservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author gavinhashemi on 2024-11-07
 */
@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs for User Resource for Create User, Modify User, Delete User and Get All Users"
)
@RestController
@AllArgsConstructor
@RequestMapping("/api/users/")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Create USer REST API",
            description = "Create USer RESTful API is used to save the user in MySQL"

    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "HTTP Status 201 Created")
    } )
    //build create User REST API
    @PostMapping("create")
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
        return  new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get USer REST API",
            description = "Get User RESTful API is used to get the user from MySQL"

    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "20", description = "HTTP Status 200 get the user info")
    } )
    //build get user by id REST API
    //http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id) {
        UserDto findUser = userService.getUserById(id);
        return  new ResponseEntity<>(findUser,HttpStatus.OK);
    }


    @Operation(
            summary = "Get all USer REST API",
            description = "Get all User RESTful API is used to get the user from MySQL"

    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "20", description = "HTTP Status 200 get the all user info")
    } )
    //http://localhost:8080/api/users/
    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return  new ResponseEntity<>(users,HttpStatus.OK);
    }

    @Operation(
            summary = "Update User  data REST API",
            description = "Get all User RESTful API is used to get the user from MySQL"

    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "20", description = "HTTP Status 200 get the all user info")
    } )
    @PutMapping("update/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user,
                                           @PathVariable Long id) {
        user.setId(id);
        UserDto updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(
            summary = "Delete User  data REST API",
            description = "Delete User RESTful API is used to delete the user from MySQL"

    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "20", description = "HTTP Status 200 get the delete user info")
    } )

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("the user with userId " + id + " is deleted now." );
    }

    //The @ExceptionHandler is an
    //annotation to handle the specific exception and sending the custom responses to the client.
    // I commented in here and move the method to the globalExceptionHandler class
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourcNotFoundExcpetion(UserNotFoundException exception,
//                                                                       WebRequest webRequest) {
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                /* I put the getDescription as false, otherwise it will how the client information like this
//                "path": "uri=/api/users/delete/9;client=127.0.0.1",
//                 */
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
//    }


}
