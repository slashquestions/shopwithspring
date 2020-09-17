package StartApp.Entities;


import javax.persistence.*;

@Entity
@Table(name= "OrderItem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name="counter")
    private int counter;

    @OneToOne
    private DefaultClassForMachine product;


    public OrderItem(int counter, DefaultClassForMachine product) {
        this.counter = counter;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public DefaultClassForMachine getProduct() {
        return product;
    }

    public void setProduct(DefaultClassForMachine product) {
        this.product = product;
    }
}
