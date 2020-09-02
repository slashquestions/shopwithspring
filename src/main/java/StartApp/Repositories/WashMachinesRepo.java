package StartApp.Repositories;

import StartApp.Entities.WashMachine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WashMachinesRepo  extends CrudRepository<WashMachine,Integer> {
}
