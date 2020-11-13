package com.example.demo.controller;

import com.example.demo.model.Endereco;
import com.example.demo.model.User;
import com.example.demo.respository.EnderecoRepository;
import com.example.demo.respository.RolesRepository;
import com.example.demo.respository.UserRepository;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RolesRepository rolesRepository;

    private User usuario = new User();

    @Autowired
    private EnderecoRepository enderecoRepository;


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
            model.addAttribute("logado", this.clienteService.listaPorUm(usuario.getCliente().getId()));
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
