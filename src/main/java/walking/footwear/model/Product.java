package walking.footwear.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    private String name;
    private int instock;
    private double price;
    @JsonIgnore
    @ManyToMany
    private Set<Order> orders;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInstock() {
        return instock;
    }

    public void setInstock(int instock) {
        this.instock = instock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Order order) {
        if(this.orders==null){
            this.orders=new HashSet<>();
        }
        this.orders.add(order);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {

    }

    public Product(String name, int instock, double price, Category category) {
        this.name = name;
        this.instock = instock;
        this.price = price;
        this.category = category;
    }

    public Product(String name) {
        this.name = name;
    }
}
