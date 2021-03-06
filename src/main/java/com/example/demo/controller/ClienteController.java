package com.example.demo.controller;

import com.example.demo.model.Carrinho;
import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import com.example.demo.model.User;
import com.example.demo.respository.ClienteRepository;
import com.example.demo.respository.EnderecoRepository;
import com.example.demo.respository.UserRepository;
import com.example.demo.respository.querys.CarrinhoCustomRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.service.EnderecoService;
import com.example.demo.service.UserService;
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
import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CarrinhoCustomRepository carrinhoCustomRepository;

    private Cliente clientes = new Cliente();
    private Endereco enderecos = new Endereco();
    private User usuario = new User();

    @GetMapping("/new")
    public ModelAndView buscaCadastro(Cliente cliente) {
        ModelAndView mv = new ModelAndView("cliente/cadastro");
        return mv;
    }

    @PostMapping("/cadastro")
    public String cadastroCliente(@Valid @ModelAttribute Cliente cliente, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cliente/cadastro";
        }
        this.clientes = cliente;
        return "redirect:/cliente/endereco";
    }

    @GetMapping("/endereco")
    public ModelAndView buscaEndereco(Endereco endereco) {
        ModelAndView mv = new ModelAndView("cliente/cadastroEndereco");
        return mv;
    }

    @PostMapping("/endereco")
    public String cadastroEndereco(@Valid @ModelAttribute Endereco endereco, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cliente/cadastroEndereco";
        }
        this.enderecos = endereco;
        return "redirect:/cliente/usuario";
    }

    @GetMapping("/usuario")
    public ModelAndView buscaUsuario(User user) {
        ModelAndView mv = new ModelAndView("cliente/cadastroUsuario");
        return mv;
    }

    @PostMapping("usuario")
    public String cadastroUsuario(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cliente/cadastroUsuario";
        }
        this.enderecoService.cadastro(enderecos, clientes, user);
        this.userService.cadastro(user, clientes);
        return "redirect:/login";
    }

    @GetMapping("/listar")
    public ModelAndView listarEndereco(Endereco enderecos, Model model) {
        buscarUsuarioLogado();
        somaQtdCarrinho(model);
        ModelAndView mv = new ModelAndView("cliente/listar");
        Cliente cliente = clienteRepository.findId(usuario.getId());
        mv.addObject("role", usuario.getRoles().iterator().next().getName());
        mv.addObject("cliente", cliente);
        mv.addObject("endereco", cliente);
        mv.addObject("usuario", usuario);
        return mv;
    }

    @GetMapping("/edit_cliente/{id}")
    public ModelAndView editarCliente(@PathVariable("id") Long id, Model model) {
        buscarUsuarioLogado();
        somaQtdCarrinho(model);
        ModelAndView mv = new ModelAndView("cliente/editar");
        Cliente cliente = this.clienteService.listaPorUm(id);
        mv.addObject("role", usuario.getRoles().iterator().next().getName());
        mv.addObject("cliente", cliente);
        return mv;
    }

    @GetMapping("/edit_endereco/{id}")
    public ModelAndView listaEndereco(@PathVariable("id") Long id, Endereco endereco, Model model) {
        buscarUsuarioLogado();
        somaQtdCarrinho(model);
        List<Endereco> enderecos = enderecoRepository.findId(id);
        ModelAndView mv = new ModelAndView("cliente/listarEndereco");
        mv.addObject("role", usuario.getRoles().iterator().next().getName());
        mv.addObject("endereco", enderecos);
        return mv;
    }

    @GetMapping("/editar_endereco/{id}")
    public ModelAndView editEndereco(@PathVariable("id") Long id, Endereco endereco, Model model) {
        buscarUsuarioLogado();
        somaQtdCarrinho(model);
        Endereco endereco1 = enderecoService.listaPorUm(id);
        ModelAndView mv = new ModelAndView("cliente/editarEndereco");
        mv.addObject("role", usuario.getRoles().iterator().next().getName());
        mv.addObject("endereco", endereco1);
        return mv;
    }

    @PutMapping("/editar_endereco/{id}")
    public ModelAndView editarEndereco(@PathVariable("id") Long id, Endereco enderecos) {
        Endereco endereco =this.enderecoService.listaPorUm(id);
        ModelAndView mv = new ModelAndView("redirect:/cliente/edit_endereco/"+endereco.getCliente().getId());
        enderecoService.update(id, enderecos);
        return mv;
    }

    @DeleteMapping("/delete_endereco/{id}")
    public String deleteEndereco(@PathVariable("id") Long id, Endereco enderecos) {
        enderecoService.delete(id);
        return "redirect:/cliente/edit_endereco/" + usuario.getId();
    }

    @GetMapping("/edit_usuario/{id}")
    public ModelAndView editUsuario(@PathVariable("id") Long id, Model model) {
        buscarUsuarioLogado();
        somaQtdCarrinho(model);
        User usuario = this.userService.listaPorUm(id);
        ModelAndView mv = new ModelAndView("cliente/editarUsuario");
        mv.addObject("role", usuario.getRoles().iterator().next().getName());
        mv.addObject("user", usuario);
        return mv;
    }

    @PutMapping("/edit_usuario/{id}")
    public String editarUsuario(@Valid @PathVariable("id") Long id, User user, BindingResult bindingResult, Model model) {
        buscarUsuarioLogado();
        if (bindingResult.hasErrors()) {
            return "cliente/editarUsuario";
        }
        model.addAttribute("role", usuario.getRoles().iterator().next().getName());
        this.userService.updatePassord(id, user);
        if(usuario.getRoles().iterator().next().getName().equals("ADMIN")) {
            return "redirect:/administrador/listar/";
        }else{
            return "redirect:/cliente/listar/";
        }
    }

    @PutMapping("/edit_cliente/{id}")
    public String editar_Cliente(@PathVariable("id") Long id, Cliente cliente, BindingResult bindingResult, Model model) {
        buscarUsuarioLogado();
        if (bindingResult.hasErrors()) {
            return "cliente/editar";
        }
        clienteService.update(id, cliente);
        model.addAttribute("role", usuario.getRoles().iterator().next().getName());
        model.addAttribute("role", usuario.getRoles().iterator().next().getName());
        if(usuario.getRoles().iterator().next().getName().equals("ADMIN")) {
            return "redirect:/administrador/listar/";
        }else{
            return "redirect:/cliente/listar/";
        }

    }


    private void somaQtdCarrinho(Model model) {
        buscarUsuarioLogado();
        int total = 0;
        if (usuario.isEnabled()) {
            List<Carrinho> carrinhos = this.carrinhoCustomRepository.getCarrinhoId(usuario.getId());
            for (Carrinho c: carrinhos) {
                total = total += c.getQuantidade();
            }
            model.addAttribute("car", total);
        } else {
            model.addAttribute("car", null);
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
