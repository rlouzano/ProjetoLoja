package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.respository.UserRepository;
import com.example.demo.respository.UsuarioCustomRepository;
import com.example.demo.service.ProdutoService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/administrador")
//@PreAuthorize("hasAuthority('ADMIN')")
public class AdministradorController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioCustomRepository usuarioCustomRepository;

    @Autowired
    private UserRepository userRepository;

    private User usuario = new User();

    private Role role = new Role();

    @Autowired
    private UserService userService;

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Integer id, Produto produto, Model model) {
        Produto prod = produtoService.listaPorUm(id);
        model.addAttribute("prod", prod);
        return "adm/visualizar";
    }

    @GetMapping("/user")
    public ModelAndView buscar(User user) {
        ModelAndView mv = new ModelAndView("users/createAdmin");
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView create(User user, String role) {
        ModelAndView mv = new ModelAndView("redirect:/administrador/user");
        userService.createAdmin(user, role);
        return mv;
    }

    @GetMapping("/listar")
    public ModelAndView listar(User user) {
        buscarUsuarioLogado();
        ModelAndView mv = new ModelAndView("users/indexAdmin");
        List<User> users = usuarioCustomRepository.getUsurioAtivo(usuario.getId());
        mv.addObject("list", users);
        return mv;
    }
    @PutMapping("/valida/{id}")
    public ModelAndView validaStatus(@PathVariable("id") Long id, User user){
        ModelAndView mv = new ModelAndView("redirect:/administrador/listar");
        this.userService.updateStatus(id, user);
        return mv;
    }

    @GetMapping("/inativo")
    public ModelAndView listarInativo(User user) {
        buscarUsuarioLogado();
        ModelAndView mv = new ModelAndView("users/indexAdmin");
        List<User> users = usuarioCustomRepository.getUsurioInativo(usuario.getId());
        mv.addObject("list", users);
        return mv;
    }

    private void buscarUsuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String email = autenticado.getName();
            usuario = userRepository.buscaClienteEmail(email).get(0);
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, User user, String role) {
        ModelAndView mv = new ModelAndView("users/editAdmin");
        User u = userService.listaPorUm(id);
        mv.addObject("user", u);
        return mv;
    }

    @PutMapping("/editar/{id}")
    public String EditarRole(@PathVariable("id") Long id, User user, String role) {
        this.userService.editRole(user, id, role);
        return "redirect:/administrador/listar";
    }



    @PutMapping("/edit/{id}")
    public String Editar(@PathVariable("id") Long id, @Valid @ModelAttribute User user, BindingResult bindingResult, String role) {
        if (bindingResult.hasErrors()) {
            return "users/editAdmin";
        }
        this.userService.update(id, user);
        return "redirect:/administrador/listar";
    }

    /**
     * METODO PARA CHAMAR O CADASTRO DE USUÁRIO COMO ADMINISTRADOR
     */
    @GetMapping("/criador")
    public String CriaUsuario(User user){
        return "users/createAdmin";
    }

    /**
     * METODO CADASTRO DE USUÁRIO COMO ADMINISTRADOR
     */
    @PostMapping("/cadastro")
    public String CadastroUsuario(@Valid @ModelAttribute User user, BindingResult bindingResult, String role){
        if (bindingResult.hasErrors()) {
            return "users/createAdmin";
        }
        userService.cadastroAdmin(user, role);
        return "redirect:/administrador/listar";
    }
}