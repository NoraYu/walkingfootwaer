package walking.footwear.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    @ManyToOne
    private User user;
    private String shipping;
    private boolean active;
    @ManyToMany(mappedBy = "orders")
    private Set<Product> products;

    public Order() {
        this.active=true;
       // this.products=new HashSet<>();
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        if(getProducts()==null){
            this.products=new HashSet<>();
        }
        this.products.add(product);
    }
}
