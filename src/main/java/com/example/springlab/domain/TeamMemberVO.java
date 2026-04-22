package com.example.springlab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class TeamMemberVO {
    private String name;
    private String nickName;
    private String food;

    @Override
    public String toString() {
        return name+ ": 별명은 " + nickName + "이고 좋아하는 음식은 " + food + "입니다.";
    }
}
