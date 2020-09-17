package StartApp.Entities;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NUMBER_ORDER")
    private String numberOrder;

    @NotNull
    @Column(name="Phone_Number")
    private String phoneNumber;


    @NotNull
    @Column(name="Full_Name")
    private String fullName;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "users_id")
    private User idClient;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> basketOfItems;

    @Column(name = "totalPrice")
    private int price;


    public int getPrice() {

        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//
//    public int getNumberOrder() {
//        return numberOrder;
//    }
//
//    public void setNumberOrder(int numberOrder) {
//        this.numberOrder = numberOrder;
//    }

        public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public User getIdClient() {
        return idClient;
    }

    public void setIdClient(User idClient) {
        this.idClient = idClient;
    }

    public List<OrderItem> getBasketOfItems() {
        return basketOfItems;
    }

    public void setBasketOfItems(List<OrderItem> basketOfItems) {
        this.basketOfItems = basketOfItems;
    }
}
