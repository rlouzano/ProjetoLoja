package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.respository.RolesRepository;
import com.example.demo.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    private User usuario = new User();

    @GetMapping("/menu")
    public String menu(Model model) {
        buscarUsuarioLogado();
        String email = usuario.getEmail();
        if (email == null) {
            return "index";
        }else {
            System.out.println(usuario.getRoles().iterator().next().getName());
            model.addAttribute("role", usuario.getRoles().iterator().next().getName());
            model.addAttribute("usuario", usuario);
            return "indexAdmin";
        }
    }

    private void buscarUsuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String email = autenticado.getName();
            this.usuario = userRepository.buscaClienteEmail(email).get(0);
        }
    }
}
