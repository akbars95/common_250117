package com.mtsmda.word.controller;

import com.mtsmda.word.service.LanguageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import static com.mtsmda.word.controller.PageURL.*;
/**
 * Created by dminzat on 2/5/2017.
 */
@Controller
public class StaticPageController {

    private static final Logger LOGGER = Logger.getLogger(StaticPageController.class);

    @Autowired
    private LanguageService languageService;

    @GetMapping({ROOT, StaticPageURL.INDEX_PAGE_URL})
    public String index(Model model) {
        model.addAttribute("recipient", "Hello");
        LOGGER.info("get index page");
        languageService.getAllLanguages().forEach(language -> {
            System.out.println(language.toString());
        });
        return StaticPageURL.INDEX_PAGE_IN;
    }

    @GetMapping({"/home"})
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.getModelMap().addAttribute("recipient", "Hello");
        modelAndView.setView(new InternalResourceView("/WEB-INF/templates/jsp/home.jsp"));
        LOGGER.info("get home page");
//        return "home";
        return modelAndView;
    }

}