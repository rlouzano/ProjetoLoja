package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.respository.RolesRepository;
import com.example.demo.respository.UserRepository;
import com.example.demo.respository.querys.ProdutoCustomRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoCustomRepository produtoCustomRepository;

    private User usuario = new User();
    private Cliente cliente = new Cliente();
    private Endereco endereco = new Endereco();

    @GetMapping("/pesquisa")
    public ModelAndView pesquisaProdutoAdm(String info, Produto produto) {
        buscarUsuarioLogado();
        ModelAndView mv = new ModelAndView("adm/listar");
        List<Produto> produtos = produtoCustomRepository.getProdutoPorFiltros(info + "%");
        mv.addObject("produtos", produtos);
        mv.addObject("role", usuario.getRoles().iterator().next().getName());
        return mv;
    }

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
        try {
            this.enderecoService.cadastro(this.endereco, this.cliente, user);
            this.userService.create(user, cliente, role);
            return "redirect:/administrador/listar/";
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/listar/")
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
        ModelAndView mv = new ModelAndView("redirect:/administrador/listar/");
        this.userService.updateStatus(id, user);
        return mv;
    }

    @GetMapping("/detalhes/{id}")
    public String produtoDetalhes(@PathVariable("id") Integer id, @ModelAttribute("prod") Produto produto, Model model) {
        buscarUsuarioLogado();
        if (usuario.getId() == null) {
            model.addAttribute("prod", this.produtoService.listaPorUm(id));
            model.addAttribute("role", "null");
        } else {
            model.addAttribute("role", usuario.getRoles().iterator().next().getName());
            model.addAttribute("prod", produtoService.listaPorUm(id));
        }

        return "adm/detalhes";
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


    @PutMapping("/edit/{id}")
    public String Editar(@PathVariable("id") Long id,
                         @Valid @ModelAttribute User user, BindingResult bindingResultU,
                         @Valid @ModelAttribute Cliente cliente, BindingResult bindingResultC,
                         @Valid @ModelAttribute Endereco endereco, BindingResult bindingResultE, String role) {
        if (bindingResultU.hasErrors() && bindingResultC.hasErrors() && bindingResultE.hasErrors()) {
            return "users/editAdmin";
        }
        this.clienteService.update(id, cliente);
        this.enderecoService.update(id, endereco);
        this.userService.update(id, user);
        return "redirect:/administrador/listar/";
    }

    @Autowired
    private RolesRepository rolesRepository;

    @PutMapping("/editar/{id}")
    public String EditarRole(@PathVariable("id") Long id, String role) {
        User user = this.userRepository.getOne(id);
        Role role1 = this.rolesRepository.getOne(user.getRoles().iterator().next().getId());
        role1.setId(user.getRoles().iterator().next().getId());
        role1.setName(role);
        this.rolesRepository.save(role1);
        return "redirect:/administrador/listar/";
    }

    private void buscarUsuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String email = autenticado.getName();
            usuario = userRepository.buscaClienteEmail(email).get(0);
        }
    }
}