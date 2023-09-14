package com.spring.cswiki.controller;

import com.spring.cswiki.domain.Doc;
import com.spring.cswiki.service.DocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.inject.Inject;
import java.util.List;

@Controller
public class MainController {

    @Inject
    private DocService service;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/")
    public String main(Model model){
        List<Doc> sidebar;
        sidebar = service.sidebar();
        model.addAttribute("sidebar", sidebar);
        return "/main";
    }
}
