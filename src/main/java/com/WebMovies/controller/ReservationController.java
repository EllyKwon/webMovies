package com.WebMovies.controller;

import com.webMovies.model.MemberVO;
import com.webMovies.model.PayVO;
import com.webMovies.model.ReservationVO;
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
@RequestMapping("/reservation")
public class ReservationController {

	private final ReserveService reserveService;
	private final PayService payService;

	@PostMapping("/moveReserve")
	public String moveReserve() {
		return "reserve";
	}
	
	
	@PostMapping("/moveSeat")
	public String moveSeat(Model model, ReservationVO reservationVO) {
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
			return "redirect:/moveMain.do";
		}
		return "kakao";
	}
	
	@PostMapping("/payKakao")
	public String payKakao(Model model, ReservationVO reservationVO, PayVO payVO, HttpSession session) {
		MemberVO login =(MemberVO)session.getAttribute("login");
		reservationVO.setMemberId(login.getMemberId());
		List<ReservationVO> list = reserveService.getReserveList(reservationVO);

		payVO.setMemberId(list.get(0).getMemberId());
		payVO.setReserveId(list.get(0).getReserveId());
		int resultCount = payService.insertPay(payVO);
		if(resultCount == 0) {
			log.error("payKakao error");
			return "redirect:/moveMain.do";
		}
		model.addAttribute("type", "reserve");
		model.addAttribute("isSuccess", true);
		
		return "process";
	}
	
	@PostMapping("/moveMypage")
	public String moveMain(Model model, ReservationVO reservationVO, HttpSession session) {
		MemberVO login =(MemberVO)session.getAttribute("login");

		reservationVO.setMemberId(login.getMemberId());
		List<ReservationVO> list = reserveService.selectMemberReservation(reservationVO);

		if(list == null) {
			log.error("moveMain error");
		}

		model.addAttribute("reserveList", list);

		return "myPage";
	}

}







