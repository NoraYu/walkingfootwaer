package walking.footwear.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import walking.footwear.dao.CategoryRepository;
import walking.footwear.dao.ProduceRepository;
import walking.footwear.exception.NotFoundException;
import walking.footwear.model.Category;
import walking.footwear.model.Product;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProduceRepository produceRepository;

    @Mock
    private CategoryRepository categoryRepository;



    @Test
    public void showAllShouldWork(){
        when(produceRepository.findAll()).thenReturn(Arrays.asList(new Product("1"),
                new Product("2"),new Product("3")));
        assertEquals(3,productService.all().size());
    }

    @ParameterizedTest
    @ValueSource (longs  = {1l, 2l, 3l, 5l})
    public void findByIdShouldWork(long id) throws NotFoundException {
        Product product=spy(Product.class);
        product.setProductId(id);
        when(produceRepository.findById(id)).thenReturn(Optional.of(product));
        assertTrue(productService.finditembyid(id)==product);
    }

    @ParameterizedTest
    @ValueSource (longs  = {1l, 2l, 3l, 5l})
    public void findByIdNotFoundShouldWork(long id) throws NotFoundException {
        when(produceRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,()->productService.finditembyid(id));
    }


    @ParameterizedTest
    @CsvSource ({"1,9", "2,4", "3,7", "5,8"})
    public void setCategoryShouldWork(String id,String cid)  {
        Product product=spy(Product.class);
        product.setProductId(Long.parseLong(id));
        Category category=spy(Category.class);
        category.setCategoryID(Long.parseLong(cid));

        when(produceRepository.findById(Long.parseLong(id))).thenReturn(Optional.of(product));
        when(categoryRepository.findById(Long.parseLong(cid))).thenReturn(Optional.of(category));
        when(produceRepository.save(product)).thenReturn(product);

        assertEquals(Long.parseLong(cid),productService.setCategory(Long.parseLong(cid),Long.parseLong(id)).getCategory().getCategoryID());
    }




}