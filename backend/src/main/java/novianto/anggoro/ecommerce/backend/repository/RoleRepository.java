package novianto.anggoro.ecommerce.backend.repository;

import novianto.anggoro.ecommerce.backend.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
}
