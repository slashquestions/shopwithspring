package StartApp.Entities;

import com.google.common.base.Objects;

import javax.persistence.*;

    @Entity
    @Table(name = "Washmachines")
    public class WashMachine extends DefaultClassForMachine {

        public WashMachine createClone(WashMachine washMachine){
            WashMachine newWashMachine = new WashMachine();
            newWashMachine.setId(washMachine.getId());
            newWashMachine.setType(washMachine.getType());
            newWashMachine.setMaker(washMachine.getMaker());
            newWashMachine.setCounter(washMachine.getCounter());
            newWashMachine.setPrice(washMachine.getPrice());
            return newWashMachine;
        }




}
