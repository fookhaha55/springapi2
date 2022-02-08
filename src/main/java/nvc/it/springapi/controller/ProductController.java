package nvc.it.springapi.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nvc.it.springapi.model.Product;
import nvc.it.springapi.model.Review;
import nvc.it.springapi.service.ProductSevice;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductSevice productSevice;

    @GetMapping("")
    public ResponseEntity<Object> getAllProduct(){
        List<Product> products = productSevice.getProducts();
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Search Success!");
        map.put("data", products);
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Search By Id Success!");
        map.put("data", productSevice.findById(id));
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getProductByName(@PathVariable String name){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Search By Name Success!");
        map.put("data", productSevice.findProductByName(name));
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addProduct(@RequestBody Product product){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Add Product Success!");
        map.put("data", productSevice.addProduct(product));
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable String id, @RequestBody Product product){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Update Product Success!");
        map.put("data", productSevice.updateProduct(id, product));
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> addreview(@PathVariable String id, @RequestBody Review review){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Add review Success!");
        map.put("data", productSevice.addReview(id, review));
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String id){
        HashMap<String, Object> map = new HashMap<>();
       if(!productSevice.deleteProduct(id)){
           map.put("msg", "Error delete");
           return new ResponseEntity<Object>(map, HttpStatus.OK);
       }else{
        map.put("msg", "Delete success");
        return new ResponseEntity<Object>(map, HttpStatus.OK);
       }
    }
}


