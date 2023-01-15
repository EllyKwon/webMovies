package com.WebMovies.mapper;

import com.webMovies.model.MemberVO;

public interface MemberMapper {

    String getId(String userId);

    int register(MemberVO memberVO);

    MemberVO getMemberInfoForLogin(MemberVO memberVO);

    MemberVO getMemberInfo(MemberVO memberVO);

}
