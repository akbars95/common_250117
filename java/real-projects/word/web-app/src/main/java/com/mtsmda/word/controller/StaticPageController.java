package com.mtsmda.word.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dminzat on 2/5/2017.
 */
@Controller
@RequestMapping("/")
public class StaticPageController {

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("recipient", "Hello");
        return "index";
    }

}