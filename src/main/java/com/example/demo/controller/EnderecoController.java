package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import com.example.demo.model.User;
import com.example.demo.respository.ClienteRepository;
import com.example.demo.respository.EnderecoRepository;
import com.example.demo.respository.UserRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.service.EnderecoService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("endereco")
public class EnderecoController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ClienteRepository clienteRepository;

    private User usuario = new User();

    @GetMapping("/busca_endereco")
    public ModelAndView buscaEndereco(Endereco endereco){
        buscarUsuarioLogado();
        ModelAndView mv = new ModelAndView("endereco/cadastro");
        mv.addObject("role", usuario.getRoles().iterator().next().getName());
        return mv;
    }

    @PostMapping("/cadastro_endereco")
    public String CadEndereco(@Valid @ModelAttribute Endereco endereco, BindingResult bindingResult){
        buscarUsuarioLogado();
        if(bindingResult.hasErrors()){
            return "endereco/cadastro";
        }
        Cliente cliente = clienteRepository.findId(usuario.getId());
        enderecoService.create(endereco, cliente);
        return "redirect:/cliente/edit_endereco/" + usuario.getId();
    }


    private void buscarUsuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String email = autenticado.getName();
            this.usuario = userRepository.buscaClienteEmail(email).get(0);
        }
    }
}
