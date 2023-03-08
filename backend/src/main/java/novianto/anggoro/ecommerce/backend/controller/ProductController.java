package novianto.anggoro.ecommerce.backend.controller;

import novianto.anggoro.ecommerce.backend.entity.Product;
import novianto.anggoro.ecommerce.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addNewProduct")
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }
}
