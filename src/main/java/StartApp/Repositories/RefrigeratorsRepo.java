package StartApp.Repositories;

import StartApp.Entities.Refrigerator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefrigeratorsRepo extends CrudRepository<Refrigerator,Integer> {

}
