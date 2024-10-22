package dio.bootcamp.PublishedAPI.service.impl;

import dio.bootcamp.PublishedAPI.dto.UserUpdateDTO;
import dio.bootcamp.PublishedAPI.models.User;
import dio.bootcamp.PublishedAPI.repository.UserRepository;
import dio.bootcamp.PublishedAPI.service.UserService;
import dio.bootcamp.PublishedAPI.service.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.Optional.ofNullable;

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
    public List<User> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        if (usersList.isEmpty()) {
            throw new NoSuchElementException("There's any users saved on our data.");
        } else {
            return usersList;
        }
    }

    @Override
    public User createUser(User userToCreate) {
        ofNullable(userToCreate).orElseThrow(() -> new BusinessException("User to create must not be null."));
        ofNullable(userToCreate.getAccount()).orElseThrow(() -> new BusinessException("User account must not be null."));
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("Account number already exists");
        }
        return userRepository.save(userToCreate);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserById(Long id, User userToUpdate) {
        Optional<User> searchUser = userRepository.findById(id);
        if (searchUser.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            User userFound = searchUser.get();
            userFound.setName(userToUpdate.getName());
            userFound.setFeatures(userToUpdate.getFeatures());
            userFound.setNews(userToUpdate.getNews());
            return userRepository.save(userFound);
        }
    }
}
