package dio.bootcamp.PublishedAPI.service;

import dio.bootcamp.PublishedAPI.dto.UserUpdateDTO;
import dio.bootcamp.PublishedAPI.models.User;
import org.springframework.stereotype.Service;
import java.util.List;


public interface UserService {

    public User findById(Long id);

    public List<User> getAllUsers();

    public User createUser(User user);

    public void deleteById(Long id);

    public User updateUserById(Long id, User userToUpdate);
}
