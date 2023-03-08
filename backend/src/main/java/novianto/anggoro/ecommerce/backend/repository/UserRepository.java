package novianto.anggoro.ecommerce.backend.repository;

import novianto.anggoro.ecommerce.backend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
