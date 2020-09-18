package walking.footwear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import walking.footwear.dao.OrderRepository;
import walking.footwear.dao.ProduceRepository;
import walking.footwear.model.Expenses;
import walking.footwear.model.Order;
import walking.footwear.model.Product;
import walking.footwear.model.User;

import javax.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    ProduceRepository produceRepository;

    @Transactional
    public boolean addToCart(Product product, Order order,int num){
        if(product.getInstock()>=num) {
            product.setInstock(product.getInstock()-num);
            product.setOrders(order);
            produceRepository.save(product);
            Expenses.getExpenses().addBalance(num*product.getPrice());
            return true;
        }
        return false;
    }



}
