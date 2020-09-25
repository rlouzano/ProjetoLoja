package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Integer id, Produto produto, Model model){
        Produto prod = produtoService.listaPorUm(id);
        model.addAttribute("prod", prod);
        return "adm/visualizar";
    }

}
