package dio.bootcamp.PublishedAPI.repository;

import dio.bootcamp.PublishedAPI.models.Account;
import dio.bootcamp.PublishedAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public boolean existsByAccountNumber(String accountNumber);

    public Optional<User> findByAccountNumber(String accountNumber);

    public Optional<User> findByUsername(String username);
}
