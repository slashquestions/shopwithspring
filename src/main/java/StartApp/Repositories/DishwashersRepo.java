package StartApp.Repositories;

import StartApp.Entities.Dishwasher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishwashersRepo extends CrudRepository<Dishwasher,Integer> {


}
