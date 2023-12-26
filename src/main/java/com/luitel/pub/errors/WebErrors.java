package com.luitel.pub.errors;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class WebErrors implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response) {
        int status = response.getStatus();
        if ( status == HttpStatus.NOT_FOUND.value()) {
            return new ModelAndView("error/4xx");
        } else if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return new ModelAndView("error/5xx");
        }
        System.out.println(status);
        return new ModelAndView("error");
    }
}
