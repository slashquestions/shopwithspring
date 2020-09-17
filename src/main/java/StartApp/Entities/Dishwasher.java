package StartApp.Entities;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "Dishwasher")
public class Dishwasher extends  DefaultClassForMachine {


    public Dishwasher createClone(Dishwasher dishwasher){
        Dishwasher newDishwasher = new Dishwasher();
        newDishwasher.setId(dishwasher.getId());
        newDishwasher.setType(dishwasher.getType());
        newDishwasher.setMaker(dishwasher.getMaker());
        newDishwasher.setCounter(dishwasher.getCounter());
        newDishwasher.setPrice(dishwasher.getPrice());
        return newDishwasher;
    }




}
