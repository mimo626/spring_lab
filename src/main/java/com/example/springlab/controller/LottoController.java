package com.example.springlab.controller;

import com.example.springlab.domain.LottoVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LottoController {
    @GetMapping("/lotto")
    public String lotto(@RequestParam int num,
                        HttpServletRequest request,
                        Model model) {
        int random = (int) (Math.random() * 6) + 1;

        if(random == num) {
            LottoVO lottoVO = new LottoVO("추카추카!!!", "/images/sun.png");
            model.addAttribute("lottoVO", lottoVO);

            return "lottoView";
        } else{
            LottoVO lottoVO = new LottoVO("아쉽게도 다음 기회를...", "/images/rain.png");
            model.addAttribute("lottoVO", lottoVO);
            model.addAttribute("refinfo", request.getHeader("referer"));

            return "lottoView";
        }
    }
}
