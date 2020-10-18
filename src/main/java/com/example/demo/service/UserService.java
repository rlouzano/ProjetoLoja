package com.example.demo.service;


import com.example.demo.model.Produto;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User create(User user);

    public boolean delete(Long id);

    public boolean update(Long id, User user);

    public boolean updateStatus(Long id, User user);

    public User show(Long id);

    public User createAdmin(User user, String role);

    public User listaPorUm(Long id);

    public User editRole(User user, Long id, String role);

    public List<User> findAllWhereRoleEquals(Long role_id, Long user_id);

    public User cadastroAdmin(User user, String role);
}
