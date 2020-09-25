package com.example.demo.service;


import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User create(User user);

    public boolean delete(Long id);

    public boolean update(Long id, User user);

    public User show(Long id);
}
