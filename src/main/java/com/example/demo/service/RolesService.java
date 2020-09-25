package com.example.demo.service;

import com.example.demo.model.Role;
import java.util.List;

public interface RolesService {

    public List<Role> findAll();

    public Role create(Role role);

    public boolean delete(Long id);

}
