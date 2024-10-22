package dio.bootcamp.PublishedAPI.service.impl;

import dio.bootcamp.PublishedAPI.dto.UserUpdateDTO;
import dio.bootcamp.PublishedAPI.models.User;
import dio.bootcamp.PublishedAPI.repository.UserRepository;
import dio.bootcamp.PublishedAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User createUser(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("Account number already exists");
        }
        return userRepository.save(userToCreate);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserById(Long id, UserUpdateDTO userUpdateDTO) {
        Optional<User> searchUser = userRepository.findById(id);
        if (searchUser.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            User userFound = searchUser.get();
            userFound.setName(userUpdateDTO.getName());
            userFound.setFeatures(userUpdateDTO.getFeatures());
            userFound.setNews(userUpdateDTO.getNews());
            return userRepository.save(userFound);
        }
    }
}
