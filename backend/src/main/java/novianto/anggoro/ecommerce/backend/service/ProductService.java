package novianto.anggoro.ecommerce.backend.service;

import novianto.anggoro.ecommerce.backend.entity.Product;
import novianto.anggoro.ecommerce.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addNewProduct(Product product){
        Product saveProduct = productRepository.save(product);
        return saveProduct;
    }

    public List<Product> getAllProduct(){
        return (List<Product>) productRepository.findAll();
    }

    public void deleteProductDetails(Integer productId){
        productRepository.deleteById(productId);
    }

    public Product getProductDetailsById(Integer productId){
        return productRepository.findById(productId).get();
    }
}
