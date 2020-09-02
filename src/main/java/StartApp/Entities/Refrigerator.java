package StartApp.Entities;

import com.google.common.base.Objects;

import javax.persistence.*;


@Entity
@Table(name = "Refrigerators")
public class Refrigerator implements DefaultInterfaceForMachine {
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

    @Override
    public String toString() {
        return "Refrigerator{" +
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
        Refrigerator that = (Refrigerator) o;
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

    public Refrigerator createClone(Refrigerator refrigerator){
        Refrigerator newRefrigerator = new Refrigerator();
        newRefrigerator.setId(refrigerator.getId());
        newRefrigerator.setType(refrigerator.getType());
        newRefrigerator.setMaker(refrigerator.getMaker());
        newRefrigerator.setCounter(refrigerator.getCounter());
        newRefrigerator.setPrice(refrigerator.getPrice());
        return newRefrigerator;
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
