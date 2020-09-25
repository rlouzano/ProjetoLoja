package com.example.demo.service;


import com.example.demo.model.Role;
import com.example.demo.respository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService{

    @Autowired
    private RolesRepository repository;

    public RolesServiceImpl(RolesRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Role> findAll() {
        List<Role> listRole = repository.findAll();
        return listRole;
    }

    @Override
    public Role create(Role role) {
        role.setName(role.getName().toUpperCase()); // para que fique sempre com letras maiusculas (toUpperCase)
        Role roleCreated = this.repository.save(role);
        return roleCreated;
    }

    @Override
    public boolean delete(Long id) {
        Role role = findById(id);
        if(!role.equals(null)){
            this.repository.delete(role);
            return true;
        }
        return false;
    }

    private Role findById(Long id){
        return this.repository.getOne(id);

    }
}
