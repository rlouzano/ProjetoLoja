package com.example.demo.service;

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
            u.setLastname(user.getLastname());
            u.setEmail(user.getEmail());
            u.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
            u.setActive(user.getActive());
            this.repository.save(u);
            return true;
        }
        return false;
    }

    @Override
    public User show(Long id) {
        return findById(id);
    }

    private User findById(Long id){
        return this.repository.getOne(id);
    }
}