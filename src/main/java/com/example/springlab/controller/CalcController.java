package com.example.springlab.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class CalcController {
    @RequestMapping("/calc")
    public ModelAndView calc( @RequestParam("num1")int number1,
                              @RequestParam("num2")int number2,
                              @RequestParam("operator") String operator,
                              HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("refinfo", request.getHeader("referer"));
        if(Objects.equals(operator, "/") && number2 == 0){
            mav.addObject("result", "나눗셈 연산시 두 번째 숫자는 0일 수 없습니다!!");
            mav.setViewName("errorResult");
            return mav;
        }
        int result = calculator(number1, number2, operator);
        mav.addObject("result", result);
        mav.setViewName("calcResult");
        return mav;
    }

    private int calculator(int number1, int number2, String operator) {
        int result = 0;
        switch(operator) {
            case "+": {
                result = number1 + number2;
                break;
            }
            case "-":{
                result = number1 - number2;
                break;
            }
            case "*": {
                result = number1 * number2;
                break;
            }
            case "/": {
                result = (int) number1 / number2;
                break;
            }
        }
        return result;
    }
}
