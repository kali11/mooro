package com.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("MsTime", System.currentTimeMillis());
        return "index";
    }
}
