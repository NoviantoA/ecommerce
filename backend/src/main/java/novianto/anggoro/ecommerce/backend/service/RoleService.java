package novianto.anggoro.ecommerce.backend.service;

import novianto.anggoro.ecommerce.backend.repository.RoleRepository;
import novianto.anggoro.ecommerce.backend.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createNewRole(Role role){
        return roleRepository.save(role);
    }
}
