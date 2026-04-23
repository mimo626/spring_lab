package com.example.springlab.controller;

import com.example.springlab.domain.TeamDTO;
import com.example.springlab.domain.TeamMemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MyTeamController2 {
    @RequestMapping(value = "/myTeam2", produces = "application/json; charset=utf-8")
    public TeamDTO printMyTeam(String id) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamName("우주수석");
        if(id != null) {
            if (id.equals("member")) {
                ArrayList<TeamMemberVO> members = new ArrayList<>();
                members.add(new TeamMemberVO("김윤우", "유누", "곱창"));
                members.add(new TeamMemberVO("이현석", "이언덕", "김치찌개"));
                members.add(new TeamMemberVO("김민수", "간치순", "라멘"));
                members.add(new TeamMemberVO("강민주", "만두", "샤브샤브"));
                teamDTO.setTeamMember(members);
            }
        }
        return teamDTO;
    }
}
