package com.example.demo.service;

import com.example.demo.model.Produto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.respository.RolesRepository;
import com.example.demo.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository repository, RolesRepository rolesRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.rolesRepository = rolesRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> findAll() {
         return this.repository.findAll();
    }

    @Override
    public User create(User user) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = this.rolesRepository.findByName("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return this.repository.save(user);
    }
    @Override
    public List<User> findAllWhereRoleEquals(Long role_id, Long user_id) {
        return this.userService.findAllWhereRoleEquals(role_id, user_id);
    }

    @Override
    public User createAdmin(User user, String role) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = this.rolesRepository.findByName(role);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return this.repository.save(user);
    }

    @Override
    public User listaPorUm(Long id) {
        User user = repository.getOne(id);
        return user;

    }

    @Override
    public boolean delete(Long id) {
        User user = findById(id);
        if(user != null){
            this.repository.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, User user) {
        User u = findById(id);
        if(!u.equals(null)){
            u.setId(user.getId());
            u.setName(user.getName());
            u.setEmail(u.getEmail());
            u.setCpf(user.getCpf());
            u.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
            u.setActive(user.getActive());
            this.repository.save(u);
            return true;
        }
        return false;
    }


    @Override
    public User editRole(User user, Long id, String role) {
        User user1 = this.repository.getOne(id);
        Role userRole = this.rolesRepository.findByName(role);
        user1.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return this.repository.save(user1);
    }

    @Override
    public User show(Long id) {
        return findById(id);
    }

    private User findById(Long id){
        return this.repository.getOne(id);
    }

    @Override
    public User cadastroAdmin(User user, String role){
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = this.rolesRepository.findByName(role);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return this.repository.save(user);
    }
}
