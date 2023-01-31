package com.webMovies.service;

import com.webMovies.mapper.MemberMapper;
import com.webMovies.model.LoginRequestVO;
import com.webMovies.model.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;

	public int registerMember(MemberVO memberVO) {
		return memberMapper.register(memberVO);
	}

	public boolean getId(String id) throws Exception{
		String getId = memberMapper.getId(id);
		if(StringUtils.hasLength(getId)) {
			return true;
		}
		return false;
	}

	public MemberVO login(LoginRequestVO loginRequestVO) throws Exception {
		return memberMapper.getMemberInfoForLogin(loginRequestVO);
	}

}
