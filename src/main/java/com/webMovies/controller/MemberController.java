package com.webMovies.controller;

import com.webMovies.model.LoginRequestVO;
import com.webMovies.model.LoginResponseVO;
import com.webMovies.model.MemberVO;
import com.webMovies.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("memberVO", new MemberVO());
		return "login";
	}

	@PostMapping("/login")
	@ResponseBody
	public LoginResponseVO login(@RequestBody LoginRequestVO loginRequestVO, HttpSession session) {
		MemberVO memberlogin = memberService.login(loginRequestVO);
		session.setAttribute("login", memberlogin);

		LoginResponseVO loginResponseVO = new LoginResponseVO();
		if(memberlogin == null) {
			loginResponseVO.setStatus("400");
			loginResponseVO.setMessage("아이디와 비밀번호를 확인해 주세요.");
			return loginResponseVO;
		}

		loginResponseVO.setStatus("200");
		loginResponseVO.setMessage("로그인에 성공하셨습니다.");

		return loginResponseVO;
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();

		return "redirect:/";
	}

	@ResponseBody
	@RequestMapping("/idCheck")
	public boolean idCheck(String id) {
		return memberService.getId(id);
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("memberVO", new MemberVO());
		return "register";
	}

	@PostMapping("/register")
	public String register(Model model, MemberVO memberVO) {
		int isSuccess = memberService.registerMember(memberVO);

		model.addAttribute("isSuccess", isSuccess);
		model.addAttribute("register",memberVO);
		
		return "login";

	}
}














