package com.WebMovies.service;

import com.webMovies.mapper.MemberMapper;
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

	public boolean getId(String id) {
		String getId = memberMapper.getId(id);
		if(StringUtils.hasLength(getId)) {
			return true;
		}
		return false;
	}

	public MemberVO login(MemberVO memberVO) {
		return memberMapper.getMemberInfoForLogin(memberVO);
	}

}
