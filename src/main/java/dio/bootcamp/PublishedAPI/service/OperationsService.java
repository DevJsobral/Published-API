package dio.bootcamp.PublishedAPI.service;

import dio.bootcamp.PublishedAPI.models.User;

import java.math.BigDecimal;

public interface OperationsService {

    public User findByUsername(String username);
    public void withdraw(String username, String password, BigDecimal value);
    public void deposit(String username, String password, BigDecimal value);
    public void transfer(String username, String password, BigDecimal value, String receiverAccountNumber);

}
