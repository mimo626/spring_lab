package com.example.springlab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudyPathController {
    @RequestMapping(value = "/study/{num}/thymeleaf", method = RequestMethod.GET)
    public String move(@PathVariable String num) {
        String link;
        switch (num) {
            case "1": {
                link = "https://abbo.tistory.com/56";
                break;
            }
            case "2": {
                link = "https://abbo.tistory.com/57";
                break;
            }
            case "3": {
                link = "https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html";
                break;
            }
            case "4": {
                link = "https://www.baeldung.com/dates-in-thymeleaf";
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + num);
        }
        return "redirect:" + link;
    }
}
