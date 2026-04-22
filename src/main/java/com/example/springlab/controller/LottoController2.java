package com.example.springlab.controller;

import com.example.springlab.domain.LottoVO;
import com.example.springlab.domain.LottoVO2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LottoController2 {
    @GetMapping("/lotto2")
    public String lotto(@RequestParam int num,
                        HttpServletRequest request,
                        Model model, HttpSession s) {


        if(s.getAttribute("count") == null) {
            s.setAttribute("count", 0);
        }
        int count = (int)s.getAttribute("count");

        LottoVO2 lottoVO2;

        if(count == 3){
            lottoVO2  = new LottoVO2("로또 응모는 낙첨된 경우에 한하여 3번까지만 가능합니다.<br> 브라우저를 재기동한 후에 응모해 주세요.", "/images/snow.png");
        } else {
            int random = (int) (Math.random() * 6) + 1;

            s.setAttribute("count", count + 1);

            if (random == num) {
                lottoVO2  = new LottoVO2("추카추카!!!", "/images/sun.png");
            } else {
                model.addAttribute("refinfo", request.getHeader("referer"));

                lottoVO2 = new LottoVO2("아쉽게도 다음 기회를...", "/images/rain.png");
            }
        }
        model.addAttribute("lottoVO2", lottoVO2);
        return "lottoView2";
    }
}
