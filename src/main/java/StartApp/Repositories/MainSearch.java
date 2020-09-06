package StartApp.Repositories;

import StartApp.Entities.DefaultClassForMachine;
import StartApp.Entities.Dishwasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;



@Repository
public interface MainSearch extends CrudRepository<DefaultClassForMachine,Long> {

    //тут ругается что нужен не интерфейс а реальный объект!!!!!!!!!!
    //переделать систему


//    @Query(value = "select * from refrigerators as ref inner join washmachines as wash on wash.id=ref.id inner join dishwashers as dish on dish.id=ref.id where id= :id",nativeQuery = true)
    @Query(value = "select id,type,maker,counter,price from products where id=41",nativeQuery = true)
    Collection<DefaultClassForMachine> findById(@Param("id") String id);


}
