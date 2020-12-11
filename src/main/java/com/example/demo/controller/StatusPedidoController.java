package com.example.demo.controller;

import com.example.demo.model.Carrinho;
import com.example.demo.model.User;
import com.example.demo.model.Venda;
import com.example.demo.respository.UserRepository;
import com.example.demo.respository.VendaRepository;
import com.example.demo.respository.querys.CarrinhoCustomRepository;
import com.example.demo.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private CarrinhoCustomRepository carrinhoCustomRepository;

    private User usuario = new User();

    @GetMapping("/detalhes/{codigo_pedido}")
    public ModelAndView detalhesPedido(@PathVariable("codigo_pedido") Integer codigo_pedido, Model model){
        somaQtdCarrinho(model);
        ModelAndView mv = new ModelAndView("pedido/listar");
        List<Venda> vendas = this.vendaRepository.findCodigo(codigo_pedido);
        mv.addObject("venda", vendas);
        mv.addObject("endereco", vendas.iterator().next().getLogradouro() + " " + vendas.iterator().next().getNumero());
        mv.addObject("endereco1", vendas.iterator().next().getLocalidade() + " " + vendas.iterator().next().getUf());
        mv.addObject("endereco2", vendas.iterator().next().getComplemento());
        mv.addObject("endereco3", vendas.iterator().next().getBairro() + " " + vendas.iterator().next().getCep());
        mv.addObject("bandeira", vendas.iterator().next().getBandeira());
        double valor =0;
        double total =0;
        for (Venda v:vendas) {
            valor = valor += v.getValor();
            total = (total += v.getTotal())+v.getFrete();
        }
        mv.addObject("valor", valor);
        mv.addObject("frete", vendas.iterator().next().getFrete());
        mv.addObject("total", total);

        return mv;
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
