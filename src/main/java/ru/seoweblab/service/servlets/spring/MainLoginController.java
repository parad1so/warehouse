package ru.seoweblab.service.servlets.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainLoginController {


    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, ModelMap map) {
        if(request.getParameterMap().containsKey("error"))
        {
            map.addAttribute("error", true);

        }
        return "login";
    }

}
