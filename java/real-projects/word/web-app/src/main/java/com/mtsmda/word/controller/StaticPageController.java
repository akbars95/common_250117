package com.mtsmda.word.controller;

import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(StaticPageController.class);

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("recipient", "Hello");
        LOGGER.info("get index page");
        return "index";
    }

}