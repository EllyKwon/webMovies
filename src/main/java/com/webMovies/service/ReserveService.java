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
		return reservationMapper.insertReserve(reservationVO);
	}

	public List<ReservationVO> getReserveList(ReservationVO reservationVO) {
		return reservationMapper.getReserveList(reservationVO);
	}

	public List<ReservationVO> selectMemberReservation(ReservationVO reservationVO) {
		return reservationMapper.selectMemberReservation(reservationVO);
	}

}
