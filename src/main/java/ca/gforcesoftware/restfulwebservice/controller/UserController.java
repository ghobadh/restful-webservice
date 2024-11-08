package ca.gforcesoftware.restfulwebservice.controller;

import ca.gforcesoftware.restfulwebservice.entity.User;
import ca.gforcesoftware.restfulwebservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gavinhashemi on 2024-11-07
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/users/")
public class UserController {

    private final UserService userService;

    //build create User REST API
    @PostMapping("create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return  new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }

    //build get user by id REST API
    //http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        User findUser = userService.getUserById(id);
        return  new ResponseEntity<>(findUser,HttpStatus.OK);
    }

    //http://localhost:8080/api/users/
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.getAllUsers();
        return  new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,
                                           @PathVariable Long id) {
        user.setId(id);
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("the user with userId " + id + " is deleted now." );
    }


}
