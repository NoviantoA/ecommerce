package novianto.anggoro.ecommerce.backend.repository;

import novianto.anggoro.ecommerce.backend.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
