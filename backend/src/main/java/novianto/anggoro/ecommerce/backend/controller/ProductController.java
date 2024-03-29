package novianto.anggoro.ecommerce.backend.controller;

import novianto.anggoro.ecommerce.backend.entity.ImageModel;
import novianto.anggoro.ecommerce.backend.entity.Product;
import novianto.anggoro.ecommerce.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = {"/addNewProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart("product") Product product, @RequestPart("imageFile")MultipartFile[] files){
//        return productService.addNewProduct(product);
        try {
            Set<ImageModel> images  = uploadImage(files);
            product.setProductImages(images);
            return productService.addNewProduct(product);
        } catch (Exception e){
//            System.out.println(e.getMessage());
//            return null;
            throw new RuntimeException();
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getProductDetailsById/{productId}"})
    public Product getProductDetailsById(@PathVariable("productId") Integer productId){
        return productService.getProductDetailsById(productId);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getAllProduct"})
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping({"/deleteProductDetails/{productId}"})
    public void deleteProductDetails(@PathVariable("productId") Integer productId){
        productService.deleteProductDetails(productId);
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();
        for (MultipartFile file : multipartFiles){
            // create object image model
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(), file.getContentType(), file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels;
    }
}
