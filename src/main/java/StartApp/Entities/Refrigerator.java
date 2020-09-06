package StartApp.Entities;

import com.google.common.base.Objects;

import javax.persistence.*;


@Entity
@Table(name = "Refrigerators")
@DiscriminatorValue("refrigerators")
public class Refrigerator extends  DefaultClassForMachine {


    public Refrigerator createClone(Refrigerator refrigerator){
        Refrigerator newRefrigerator = new Refrigerator();
        newRefrigerator.setId(refrigerator.getId());
        newRefrigerator.setType(refrigerator.getType());
        newRefrigerator.setMaker(refrigerator.getMaker());
        newRefrigerator.setCounter(refrigerator.getCounter());
        newRefrigerator.setPrice(refrigerator.getPrice());
        return newRefrigerator;
    }



}
