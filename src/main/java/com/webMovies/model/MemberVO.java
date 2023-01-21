package com.webMovies.model;

import lombok.Data;

@Data
public class MemberVO {
        // 고유넘버
        private int memberId;
        // 회원아이디
        private String userId;
        // 비밀번호
        private String pwd;
        // 이름
        private String name;
        // 전화번호
        private String phoneNum;
        // 이메일
        private String email;
        // 회원상태
        private String userState;
}
