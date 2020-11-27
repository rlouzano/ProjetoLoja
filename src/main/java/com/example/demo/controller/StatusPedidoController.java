package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.Venda;
import com.example.demo.respository.UserRepository;
import com.example.demo.respository.VendaRepository;
import com.example.demo.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/status")
public class StatusPedidoController {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private UserRepository userRepository;

    private User usuario = new User();

    @GetMapping("/detalhes/{codigo_pedido}")
    public ModelAndView detalhesPedido(@PathVariable("codigo_pedido") Integer codigo_pedido){
        ModelAndView mv = new ModelAndView("pedido/listar");
        List<Venda> vendas = this.vendaRepository.findCodigo(codigo_pedido);
        mv.addObject("venda", vendas);
        return mv;
    }

    private void buscarUsuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String email = autenticado.getName();
            this.usuario = userRepository.buscaClienteEmail(email).get(0);
        }
    }

}
