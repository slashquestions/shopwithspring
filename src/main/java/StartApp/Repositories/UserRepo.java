package StartApp.Repositories;

import StartApp.Entities.User;
import StartApp.StartApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findById(int id);
}
