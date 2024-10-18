package dio.bootcamp.PublishedAPI.service.impl;

import dio.bootcamp.PublishedAPI.models.User;
import dio.bootcamp.PublishedAPI.repository.UserRepository;
import dio.bootcamp.PublishedAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User createUser(User userToCreate) {
        if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("Account number already exists");
        }
        return userRepository.save(userToCreate);
    }
}
