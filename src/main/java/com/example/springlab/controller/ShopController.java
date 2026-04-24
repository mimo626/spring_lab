package com.example.springlab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;


@Controller
@SessionAttributes("products")
public class ShopController {
    @RequestMapping("/product")
    public void showProduct(Model model) {
        model.addAttribute("page", "product");
    }
    @ModelAttribute("products")
    public int[] createProducts(){
        return new int[10];
    }
    @RequestMapping("/basket")
    public String showBasket(String pid, String type,@ModelAttribute("products") int[] products,
                             Model model) {
        if(type != null && type.equals("delete")){
            Arrays.fill(products, 0);
            model.addAttribute("message", "delete");
        }
        else{
            int index = Integer.parseInt(pid.substring(1));
            products[index-1]++;
            //model.addAttribute("products", products);
        }

        model.addAttribute("page", "basket");
        return "product";
    }
}
