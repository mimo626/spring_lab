package com.example.springlab.controller;

import com.example.springlab.domain.TeamDTO;
import com.example.springlab.domain.TeamMemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class MyTeamController1 {
    @ModelAttribute("myteam")
    public TeamDTO getTeam() {
        return new TeamDTO();
    }
    @GetMapping("/myTeam1")
    public String printMyTeam(@RequestParam(defaultValue = "none") String what,
                              @ModelAttribute("myteam") TeamDTO dto, Model model) {
        String teamName = "4팀";
        if(what.equals("team")) {
            dto.setTeamName(teamName);
        }
        else if(what.equals("member")) {
            ArrayList<TeamMemberVO> members = new ArrayList<>();
            members.add(new TeamMemberVO("김영관", "영관", "김치전"));
            members.add(new TeamMemberVO("이동규", "동규", "치킨"));
            members.add(new TeamMemberVO("강민주", "민주", "마라탕"));
            members.add(new TeamMemberVO("모수환", "수환", "카레"));
            dto.setTeamName(teamName);
            dto.setTeamMember(members);
        } else{
            what = null;
        }
        model.addAttribute("what", what);
        return "myTeamView";
    }
}
