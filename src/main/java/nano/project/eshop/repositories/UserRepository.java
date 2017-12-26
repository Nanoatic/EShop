package nano.project.eshop.repositories;

import nano.project.eshop.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    User findByConfirmationToken(String confirmationToken);

    List<User> findTop10ByOrderByIdDesc();


    List<User> findByBdate(Date bdate);
}