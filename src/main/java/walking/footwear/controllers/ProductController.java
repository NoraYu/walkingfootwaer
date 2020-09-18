package walking.footwear.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import walking.footwear.exception.NotFoundException;
import walking.footwear.model.Product;
import walking.footwear.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value = {"/allitems"},produces = "application/json")
    public ResponseEntity<List<Product>> allitems(){

        return new ResponseEntity<>(productService.all(),HttpStatus.OK);
    }

    @PostMapping(value = {"/newitem"},produces = "application/json")
    public ResponseEntity<Product> newitem(@RequestBody Product product){
    try{
        productService.newitem(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);}
        catch (NullPointerException  e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
    }

    @PutMapping(value = {"/item/{id}/c/{cid}"},produces = "application/json")
    public ResponseEntity<Product> setCategory(@PathVariable(name = "id")long id, @PathVariable(name = "cid") long cid){
        try{


        return new ResponseEntity<>(productService.setCategory(cid,id),HttpStatus.OK);
        }
        catch (NullPointerException  e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    @GetMapping(value = {"/item/{id}"},produces = "application/json")
    public ResponseEntity<Product> showItem(@PathVariable long id) throws NotFoundException {
        HttpHeaders header = new HttpHeaders();
        Product p=null;
//        try{

            p=productService.finditembyid(id);
//            header.add("message", "found");
//        }
//        catch(Exception ex){
//            header.add("message", ex.getMessage());
//        }
        return new ResponseEntity<>(p,header,HttpStatus.OK);
    }
}
