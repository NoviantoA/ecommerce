package novianto.anggoro.ecommerce.backend.controller;

import novianto.anggoro.ecommerce.backend.entity.User;
import novianto.anggoro.ecommerce.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUsers();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user){
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "URL ini hanya bisa diakses oleh admin";
    }

    @GetMapping({"/forUser"})
    // multiple
    // @PreAuthorize("hasAnyRole('Admin', 'User')")
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "URL ini hanya bisa diakses oleh user";
    }
}
