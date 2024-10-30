package dio.bootcamp.PublishedAPI.service.impl;

import dio.bootcamp.PublishedAPI.models.User;
import dio.bootcamp.PublishedAPI.repository.UserRepository;
import dio.bootcamp.PublishedAPI.service.OperationsService;
import dio.bootcamp.PublishedAPI.service.exceptions.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class OperationsServiceImpl implements OperationsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("There's no user with this username"));
    }

    @Transactional
    @Override
    public void withdraw(String username, String password, BigDecimal value) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("There's no user with this username"));
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect Password");
        }
        BigDecimal balance = user.getAccount().getBalance();
        if (balance.compareTo(value) >= 0) {
            user.getAccount().setBalance(balance.subtract(value));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Insufficient balance for this operation");
        }
    }

    @Transactional
    @Override
    public void deposit(String username, String password, BigDecimal value) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("There's no user with this username"));
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect Password");
        }
        BigDecimal balance = user.getAccount().getBalance();
        user.getAccount().setBalance(balance.add(value));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void transfer(String username, String password, BigDecimal value, String destinyAccountNumber) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("There's no user with this username"));
        User destinyAccount = userRepository.findByAccountNumber(destinyAccountNumber)
                .orElseThrow(() -> new NoSuchElementException("There's no account with that account number"));
        this.withdraw(username,password, value);
        if (user.getAccount().getNumber().equals(destinyAccountNumber)){
            throw new IllegalArgumentException("The destiny account must be different from your account number!");
        }
        this.deposit(destinyAccount.getUsername(), destinyAccount.getPassword(), value);
        }
    }

