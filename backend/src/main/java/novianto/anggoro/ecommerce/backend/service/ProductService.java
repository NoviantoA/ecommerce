package novianto.anggoro.ecommerce.backend.service;

import novianto.anggoro.ecommerce.backend.entity.Product;
import novianto.anggoro.ecommerce.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addNewProduct(Product product){
        Product saveProduct = productRepository.save(product);
        return saveProduct;
    }
}
