package com.example.springlab.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamDTO {
    private String teamName;
    private List<TeamMemberVO> teamMember;
}
