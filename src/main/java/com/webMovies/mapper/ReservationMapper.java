package com.webMovies.mapper;

import com.webMovies.model.ReservationVO;

import java.util.List;

public interface ReservationMapper {

    List<ReservationVO> selectMemberReservation(int memberId);

    int selectMaxReserveId();

    int insertReserve(ReservationVO reservationVO);

    List<ReservationVO> getReserveList(ReservationVO reservationVO);

    ReservationVO getReserve(ReservationVO reservationVO);

}
