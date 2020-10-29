package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.respository.ProdutoCustomRepository;
import com.example.demo.respository.RolesRepository;
import com.example.demo.respository.UserRepository;
import com.example.demo.respository.UsuarioCustomRepository;
import com.example.demo.service.*;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/administrador")
//@PreAuthorize("hasAuthority('ADMIN')")
public class AdministradorController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private UserService userService;


    private User usuario = new User();
    private Cliente cliente = new Cliente();
    private Endereco endereco = new Endereco();


    /**
     * METODO PARA CHAMAR O CADASTRO DE USUÁRIO COMO ADMINISTRADOR
     */
    @GetMapping("/cadastro_cliente")
    public String CriaCliente(Cliente cliente, Model model) {
        buscarUsuarioLogado();
        model.addAttribute("role", usuario.getRoles().iterator().next().getName());
        return "users/cadastro_cliente_adm";
    }

    @GetMapping("/cadastro_endereco")
    public String CriaEndereco(Endereco endereco, Model model) {
        buscarUsuarioLogado();
        model.addAttribute("role", usuario.getRoles().iterator().next().getName());
        return "users/cadastro_endereco_adm";
    }

    @GetMapping("/cadastro_usuario")
    public String CriaUsuario(User user, Model model) {
        buscarUsuarioLogado();
        model.addAttribute("role", usuario.getRoles().iterator().next().getName());
        return "users/cadastro_usuario_adm";
    }

    /**
     * METODO CADASTRO DE USUÁRIO COMO ADMINISTRADOR
     */
    @PostMapping("/cadastro_cliente")
    public String CadastroCliente(@Valid @ModelAttribute Cliente cliente, BindingResult bindingResultCli) {
        if (bindingResultCli.hasFieldErrors()) {
            return "users/cadastro_cliente_adm";
        }
        this.cliente = cliente;
        return "redirect:/administrador/cadastro_endereco";
    }

    @PostMapping("/cadastro_endereco")
    public String CadastroEndereco(@Valid @ModelAttribute Endereco endereco, BindingResult bindingResultCli) {
        if (bindingResultCli.hasFieldErrors()) {
            return "users/cadastro_endereco_adm";
        }
        this.endereco = endereco;
        return "redirect:/administrador/cadastro_usuario";
    }

    @PostMapping("/cadastro_usuario")
    public String CadastroUsuario(@Valid @ModelAttribute User user, BindingResult bindingResultCli, String role) {
        if (bindingResultCli.hasFieldErrors()) {
            return "users/cadastro_usuario_adm";
        }
        this.enderecoService.cadastro(this.endereco, this.cliente, user);
        this.userService.create(user,cliente, role);
        return "redirect:/administrador/listar";
    }

    @GetMapping("/listar")
    public ModelAndView listar(User user, Cliente cliente, Endereco endereco) {
        buscarUsuarioLogado();
        ModelAndView mv = new ModelAndView("users/indexAdmin");
        List<User> clientes = this.userRepository.findAtivo();
        mv.addObject("list", clientes);
        mv.addObject("role", usuario.getRoles().iterator().next().getName());
        return mv;
    }


    @PutMapping("/valida/{id}")
    public ModelAndView validaStatus(@PathVariable("id") Long id, User user) {
        ModelAndView mv = new ModelAndView("redirect:/administrador/listar");
        this.userService.updateStatus(id, user);
        return mv;
    }


    @GetMapping("/inativo")
    public ModelAndView listarInativo(User user) {
        buscarUsuarioLogado();
        ModelAndView mv = new ModelAndView("users/indexAdmin");
        List<User> users = this.userRepository.findInativo();
        mv.addObject("list", users);
        mv.addObject("role", usuario.getRoles().iterator().next().getName());
        return mv;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, User user, Endereco endereco, Cliente cliente, String role) {
        ModelAndView mv = new ModelAndView("users/editAdmin");
        User u = userService.listaPorUm(id);
        Cliente cli = clienteService.listaPorUm(id);
        Endereco end = enderecoService.listaPorUm(id);
        mv.addObject("user", u);
        mv.addObject("cliente", cli);
        mv.addObject("endereco", end);
        return mv;
    }

    @PutMapping("/edit/{id}")
    public String Editar(@PathVariable("id") Long id,
                         @Valid @ModelAttribute User user, BindingResult bindingResultU,
                         @Valid @ModelAttribute Cliente cliente,BindingResult bindingResultC,
                         @Valid @ModelAttribute Endereco endereco, BindingResult bindingResultE, String role) {
        if (bindingResultU.hasErrors() && bindingResultC.hasErrors() && bindingResultE.hasErrors()) {
            return "users/editAdmin";
        }
        this.clienteService.update(id,cliente);
        this.enderecoService.update(id, endereco);
        this.userService.update(id, user);
        return "redirect:/administrador/listar";
    }

    @PutMapping("/editar/{id}")
    public String EditarRole(@PathVariable("id") Long id, User user, String role) {
        this.userService.editRole(user, id, role);
        return "redirect:/administrador/listar";
    }

    private void buscarUsuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String email = autenticado.getName();
            usuario = userRepository.buscaClienteEmail(email).get(0);
        }
    }
}