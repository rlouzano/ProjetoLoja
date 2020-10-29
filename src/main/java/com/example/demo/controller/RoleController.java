package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RolesService rolesService;

    public RoleController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("")
    public String index(Model model){
        List<Role> listRole = rolesService.findAll();
        model.addAttribute("list", listRole);
        return "roles/index";
    }


    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("role", new Role());
        return "roles/cadastro";
    }

    @PostMapping("/")
    public String save(@Valid @ModelAttribute("role") Role role, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "roles/cadastro";
        }
        Role roleCreated = this.rolesService.create(role);
        return "redirect:/roles";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        this.rolesService.delete(id);
        return "redirect:/roles";
    }

}
