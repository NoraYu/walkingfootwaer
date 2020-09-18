package walking.footwear.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import walking.footwear.model.*;
import walking.footwear.service.OrderService;
import walking.footwear.service.UserService;
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProduceRepository produceRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role(ERole.ADMIN));
        roleRepository.save(new Role(ERole.EMPLOYEE));
        roleRepository.save(new Role(ERole.CUSTOMER));
        roleRepository.save(new Role(ERole.MANAGER));
        System.out.println("role saved");

        User customer1=new User("customer1");
        User customer2=new User("customer2");
        User admin=new User("admin","admin@gmail.com","admin");
        userService.saveCustomer(customer1);
        userService.saveCustomer(customer2);
        userService.saveAdmin(admin);

        Category bag=new Category();
        bag.setCategoryName("Bag");
        Category shoes=new Category();
        shoes.setCategoryName("Shoes");
        categoryRepository.save(bag);
        categoryRepository.save(shoes);
        Product handbag=new Product();
        handbag.setCategory(bag);
        handbag.setInstock(9);
        handbag.setPrice(229.0);
        handbag.setName("Handbag");
        Product boots=new Product();
        boots.setCategory(shoes);
        boots.setInstock(4);
        boots.setPrice(99.99);
        boots.setName("Boots");
        produceRepository.save(boots); produceRepository.save(handbag);
        Order order1=new Order();
        order1.setUser(customer1);
        orderRepository.save(order1);
        orderService.addToCart(boots,order1,2);

    }
}
