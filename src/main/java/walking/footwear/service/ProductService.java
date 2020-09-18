package walking.footwear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import walking.footwear.dao.CategoryRepository;
import walking.footwear.dao.ProduceRepository;
import walking.footwear.exception.NotFoundException;
import walking.footwear.model.Product;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProduceRepository produceRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> all(){
        return produceRepository.findAll();
    }


    public void newitem(Product product){
        produceRepository.save(product);
    }

    @Transactional
    public Product setCategory(long cid,long pid){
        Product product=produceRepository.findById(pid).get();
        product.setCategory(categoryRepository.findById(cid).get());
        return produceRepository.save(product);

    }

    public Product finditembyid(long id) throws NotFoundException {
        if(produceRepository.findById(id).isPresent()){
            return produceRepository.findById(id).get();
    }
        else {
            throw new NotFoundException("Product with id " + id + " doesn't exist");
        }

    }
}
