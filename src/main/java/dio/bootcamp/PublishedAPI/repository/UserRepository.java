package dio.bootcamp.PublishedAPI.repository;

import dio.bootcamp.PublishedAPI.models.Account;
import dio.bootcamp.PublishedAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public boolean existsByAccountNumber(String accountNumber);
}
