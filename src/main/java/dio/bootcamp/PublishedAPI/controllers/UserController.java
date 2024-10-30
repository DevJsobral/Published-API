package dio.bootcamp.PublishedAPI.controllers;

import dio.bootcamp.PublishedAPI.dto.UserUpdateDTO;
import dio.bootcamp.PublishedAPI.models.User;
import dio.bootcamp.PublishedAPI.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get by ID", description = "Retrieve single user by it's ID value")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User userFound = userService.findById(id);
        return ResponseEntity.ok(userFound);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all Users", description = "Retrieve a List of all the active users.")
    public ResponseEntity<List<User>> getAllById(){
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "422", description = "UNPROCESSABLE ENTITY")})
    @Operation(summary = "Create User", description = "Fill the required fields with the user information to create it, your card number will be automatically generated.")
    public ResponseEntity<User> createUser(@RequestBody User userToCreate){
        var createdUser = userService.createUser(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userToCreate.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdUser);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "No Content")
    @Operation(summary = "Delete ID", description = "Delete a single user by it's ID value")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "422", description = "UNPROCESSABLE ENTITY")})
    @Operation(summary = "Update by ID", description = "Fill the Updatable fields of a single user by it's ID value")
    public ResponseEntity<User> updateById(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        var updatedUser = userService.updateUserById(id, userUpdateDTO.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userUpdateDTO.toModel().getId())
                .toUri();
        return ResponseEntity.created(location).body(updatedUser);
    }

}
