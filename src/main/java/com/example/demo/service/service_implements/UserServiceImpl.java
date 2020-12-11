package com.example.demo.service.service_implements;

import com.example.demo.model.*;
import com.example.demo.respository.RolesRepository;
import com.example.demo.respository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

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
    public User create(User user, Cliente cliente, String role) {
        user.setCliente(cliente);
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Role role1 = new Role();
        role1.setName(role);
        rolesRepository.save(role1);
        //Role userRole = this.rolesRepository.findByName(role);
        user.setRoles(new ArrayList<Role>(Arrays.asList(role1)));
        return this.repository.save(user);
    }
    @Override
    public User cadastro(User user, Cliente clientes) {
        user.setCliente(clientes);
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Role role1 = new Role();
        role1.setName("USER");
        rolesRepository.save(role1);
        //Role userRole = this.rolesRepository.findByName(role1.getName());
        user.setRoles(new ArrayList<Role>(Arrays.asList(role1)));
        return this.repository.save(user);
    }
    @Override
    public List<User> findAllWhereRoleEquals(Long role_id, Long user_id) {
        return this.repository.findAllWhereRoleEquals(role_id, user_id);
    }
    @Override
    public User createAdmin(User user, String role) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = this.rolesRepository.findByName(role);
        user.setRoles(new ArrayList<Role>(Arrays.asList(userRole)));
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
        if (user != null) {
            this.repository.delete(user);
            return true;
        }
        return false;
    }
    @Override
    public boolean update(Long id, User user) {
        User u = findById(id);
        if (!u.equals(null)) {
            u.setId(user.getId());
            u.setEmail(user.getEmail());
            u.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
            u.setActive(u.getActive());
            this.repository.save(u);
            return true;
        }
        return false;
    }
    @Override
    public boolean updatePassord(Long id, User user){
        User u = findById(id);
        if (!u.equals(null)) {
            u.setId(u.getId());
            u.setEmail(u.getEmail());
            u.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
            u.setActive(u.getActive());
            this.repository.save(u);
            return true;
        }
        return false;
    }
    @Override
    public boolean updateStatus(Long id, User user) {
        User u = findById(id);
        if (!u.equals(null)) {
            u.setId(u.getId());
            u.setEmail(u.getEmail());
            u.setPassword(this.bCryptPasswordEncoder.encode(u.getPassword()));
            u.setActive(user.getActive());
            this.repository.save(u);
            return true;
        }
        return false;
    }


    @Override
    public User editRole(Long id, String role) {
        User user = this.repository.getOne(id);
        Role userRole = this.rolesRepository.findByName(role);
        user.setRoles(new ArrayList<Role>(Arrays.asList(userRole)));
        return this.repository.save(user);
    }

    @Override
    public User show(Long id) {
        return findById(id);
    }

    private User findById(Long id) {
        return this.repository.getOne(id);
    }

    @Override
    public User cadastroAdmin(User user, Cliente cliente, Endereco endereco, String role) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = this.rolesRepository.findByName(role);
        user.setRoles(new ArrayList<Role>(Arrays.asList(userRole)));
        user.setCliente(cliente);
        return this.repository.save(user);
    }
}
