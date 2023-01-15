package com.WebMovies.controller;

import com.webMovies.model.MemberVO;
import com.webMovies.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/main")
	public String Main() {
		return "main";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(Model model, MemberVO memberVO, HttpSession session) {
		MemberVO memberlogin = memberService.login(memberVO);

		if(memberlogin == null) {
			model.addAttribute("isSuccess", false);
			return "process";
		}

		model.addAttribute("isSuccess", true);
		model.addAttribute("name", memberVO.getName());
		model.addAttribute("type", "login");
		session.setAttribute("login", memberVO);

		return "process";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();

		return "redirect:/main";
	}

	@PostMapping("/register")
	public String register() {
		return "register";
	}

	@ResponseBody
	@RequestMapping("/idCheck")
	public String idCheck(String id) {
		boolean getId = memberService.getId(id);
		return getId + "";
	}

	@PostMapping("/register")
	public String register(Model model, MemberVO memberVO) {
		int isSuccess = memberService.registerMember(memberVO);
		model.addAttribute("isSuccess", isSuccess);
		
		return "process";

	}
}














