package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.respository.RoleCustomRepository;
import com.example.demo.respository.RolesRepository;
import com.example.demo.respository.UserRepository;
import com.example.demo.respository.UsuarioCustomRepository;
import com.example.demo.service.RolesService;
import com.example.demo.service.UserService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

    @Autowired
    private UserService userService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsuarioCustomRepository usuarioCustomRepository;

    public PrincipalController(UserService userService, RolesService rolesService) {
        this.userService = userService;
        this.rolesService = rolesService;
    }

    private User usuario = new User();

    @GetMapping("/menu")
    public String menu(Model model) {
        buscarUsuarioLogado();
        String email = usuario.getEmail();
        if (email == null) {
            long id = 1;
            System.out.println("teste");
            User user = this.userService.listaPorUm(id);
            model.addAttribute("role", user);
            model.addAttribute("usuario", usuario);
            return "index";
        } else {
            long id = 1;
            User user = this.userService.listaPorUm(usuario.getId());
            System.out.println("Entrou");
            model.addAttribute("role", user);
            model.addAttribute("usuario", usuario);
            return "indexAdmin";
        }
    }


    private void buscarUsuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String email = autenticado.getName();
            usuario = userRepository.buscaClienteEmail(email).get(0);
        }
    }
}
