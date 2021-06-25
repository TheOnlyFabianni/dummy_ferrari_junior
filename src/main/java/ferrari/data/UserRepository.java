package ferrari.data;
import org.springframework.data.repository.CrudRepository;
import ferrari.User;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
  
}
