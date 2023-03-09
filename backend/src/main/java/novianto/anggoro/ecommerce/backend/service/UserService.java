package novianto.anggoro.ecommerce.backend.service;

import novianto.anggoro.ecommerce.backend.entity.Role;
import novianto.anggoro.ecommerce.backend.entity.User;
import novianto.anggoro.ecommerce.backend.repository.RoleRepository;
import novianto.anggoro.ecommerce.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user){
        Role role = roleRepository.findById("User").get();

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userRepository.save(user);
    }

    public void initRolesAndUsers(){
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role untuk user baru");
        roleRepository.save(userRole);

        User adminUser = new User();
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        // insert data to user_role
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);


//        User user = new User();
//        user.setUserFirstName("novianto");
//        user.setUserLastName("anggoro");
//        user.setUserName("novianto123");
//        user.setUserPassword(getEncodedPassword("novianto@pass"));
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        // insert data to user_role
//        user.setRole(userRoles);
//        userRepository.save(user);


    }

    public String getEncodedPassword(String password){
        return  passwordEncoder.encode(password);
    }
}
