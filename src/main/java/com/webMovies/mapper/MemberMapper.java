package com.webMovies.mapper;

import com.webMovies.model.LoginRequestVO;
import com.webMovies.model.MemberVO;

public interface MemberMapper {

    String getId(String userId);

    int register(MemberVO memberVO);

    MemberVO getMemberInfoForLogin(LoginRequestVO loginRequestVO);

    MemberVO getMemberInfo(MemberVO memberVO);

}
