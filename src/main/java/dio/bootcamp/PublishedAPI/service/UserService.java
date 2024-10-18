package dio.bootcamp.PublishedAPI.service;

import dio.bootcamp.PublishedAPI.models.User;
import org.springframework.stereotype.Service;


public interface UserService {

    public User findById(Long id);

    public User createUser(User user);
}
