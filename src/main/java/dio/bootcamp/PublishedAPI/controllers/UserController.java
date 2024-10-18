package dio.bootcamp.PublishedAPI.controllers;

import dio.bootcamp.PublishedAPI.models.User;
import dio.bootcamp.PublishedAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User userFound = userService.findById(id);
        return ResponseEntity.ok(userFound);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User userToCreate){
        var createdUser = userService.createUser(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userToCreate.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdUser);
    }

}
