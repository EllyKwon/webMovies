package com.webMovies.controller;

import com.webMovies.model.*;
import com.webMovies.service.MemberService;
import com.webMovies.service.PayService;
import com.webMovies.service.ReserveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping
public class ReservationController {

	private final ReserveService reserveService;
	private final PayService payService;


	@PostMapping("/reserve")
	public String reserve(InfoVO infoVO) {
		return "reserve";
	}
	
	
	@PostMapping("/seat")
	public String seat(Model model, ReservationVO reservationVO) {
		model.addAttribute("reserve", reservationVO);
		return "seat";
	}
	

	@PostMapping("/moveKakao")
	public String moveKakao(Model model, ReservationVO reservationVO, PayVO payVO, HttpSession session) {
		MemberVO login =(MemberVO)session.getAttribute("login");
		reservationVO.setMemberId(login.getMemberId());
		int resultCount = reserveService.insertReserve(reservationVO);
		model.addAttribute("reserve", reservationVO);
		model.addAttribute("pay", payVO);
		if(resultCount == 0) {
			return "redirect:/";
		}
		return "kakao";
	}
	
	@PostMapping("/payKakao")
	public String payKakao(Model model, PayVO payVO) {
		int resultCount = payService.insertPay(payVO);
		if(resultCount == 0) {
			log.error("payKakao error");
			return "redirect:/";
		}
		model.addAttribute("type", "reserve");
		model.addAttribute("isSuccess", true);
		
		return "process";
	}
	
	@RequestMapping ("/mypage")
	public String mypage(Model model, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute("login");
		if(memberVO == null){
			model.addAttribute("type", "reLogin");
			return "process";
		}

		List<ReservationVO> list = reserveService.selectMemberReservation(memberVO.getMemberId());

		model.addAttribute("list", list);
		model.addAttribute("memberVO", memberVO);

		return "mypage";
	}
}







