package com.example.springlab.controller;

import com.example.springlab.domain.StudentDTO;
import mybatis.dao.StudentMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
@Controller
public class StudentController1 {
    @Autowired
    StudentMapper1 dao;

    @GetMapping("/test1")
    public ModelAndView listAll(){
        ModelAndView mav = new ModelAndView();
        List<StudentDTO> list = dao.listAll();
        mav.addObject("list",list);
        mav.setViewName("studentView1");
        return mav;
    }

    @GetMapping("/test2")
    public ModelAndView listOrderByScoreDesc(){
        ModelAndView mav = new ModelAndView();
        List<StudentDTO> list = dao.listOrderByScoreDesc();
        mav.addObject("list",list);
        mav.setViewName("studentView1");
        return mav;
    }

    @GetMapping("/test3")
    public ModelAndView listByScoreGreaterEqualThan70(){
        ModelAndView mav = new ModelAndView();
        List<StudentDTO> list = dao.listByScoreGreaterEqualThan70();
        mav.addObject("list",list);
        mav.setViewName("studentView1");
        return mav;
    }

    @GetMapping("/test4")
    public ModelAndView listByContainName(){
        ModelAndView mav = new ModelAndView();
        List<StudentDTO> list = dao.listByContainName();
        if(list.isEmpty()){
            mav.addObject("msg","조회되는 리스트가 없어요!");
        } else {
            mav.addObject("list",list);
        }
        mav.setViewName("studentView1");
        return mav;
    }

    @GetMapping("/test5")
    public ModelAndView getScore(String name){
        ModelAndView mav = new ModelAndView();
        if(name == null){
            mav.addObject("msg","조회되는 이름이 없어요!");
        }else {
            int score = dao.getScore(name);
            mav.addObject("score",score);
        }
        mav.setViewName("studentView1");
        return mav;
    }

    @GetMapping("/test6")
    public ModelAndView getScoreAvg(){
        ModelAndView mav = new ModelAndView();
        int avgScore = dao.getScoreAvg();
        mav.addObject("avgScore",avgScore);
        mav.setViewName("studentView1");
        return mav;
    }
}
