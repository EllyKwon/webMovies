package com.webMovies.service;

import com.webMovies.mapper.ReservationMapper;
import com.webMovies.model.ReservationVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReserveService {

	private final ReservationMapper reservationMapper;

	public int insertReserve(ReservationVO reservationVO) {
		int nextReserveId = reservationMapper.selectMaxReserveId();
		reservationVO.setReserveId(nextReserveId);
		return reservationMapper.insertReserve(reservationVO);
	}

	public List<ReservationVO> getReserveList(ReservationVO reservationVO) {
		return reservationMapper.getReserveList(reservationVO);
	}

	public List<ReservationVO> selectMemberReservation(int memberId) {
		return reservationMapper.selectMemberReservation(memberId);
	}

}
