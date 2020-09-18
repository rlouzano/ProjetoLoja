package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {


    @GetMapping("/listar")
    public ModelAndView ListaCarrinho(){
        ModelAndView mv = new ModelAndView("/carrinho/carrinho");
        return mv;
    }


}
