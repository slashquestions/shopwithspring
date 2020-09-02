package StartApp.Entities;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "Dishwasher")
public class Dishwasher implements DefaultInterfaceForMachine {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "maker")
    private String maker;
    @Column(name = "counter")
    private Integer counter;
    @Column(name = "price")
    private int price;

    public Dishwasher createClone(Dishwasher dishwasher){
        Dishwasher newDishwasher = new Dishwasher();
        newDishwasher.setId(dishwasher.getId());
        newDishwasher.setType(dishwasher.getType());
        newDishwasher.setMaker(dishwasher.getMaker());
        newDishwasher.setCounter(dishwasher.getCounter());
        newDishwasher.setPrice(dishwasher.getPrice());
        return newDishwasher;
    }

    @Override
    public String toString() {
        return "Dishwasher{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", maker='" + maker + '\'' +
                ", counter=" + counter +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dishwasher that = (Dishwasher) o;
        return id == that.id &&
                price == that.price &&
                Objects.equal(type, that.type) &&
                Objects.equal(maker, that.maker) &&
                Objects.equal(counter, that.counter);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, type, maker, counter, price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
