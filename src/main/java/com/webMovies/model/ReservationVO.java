package com.webMovies.model;

import lombok.Data;

import java.util.List;

@Data
public class ReservationVO {
        // 예약번호
        private int reserveId;

        // 영화제목
        private String title;

        // 연령제한
        private String movieAge;

        // 시작시간
        private String movieStart;

        // 예약시간
        private String reserveTime;

        // 예매표수
        private Integer reserveTicket;

        // 선택좌석
        private String selectedSeat;


        // 선택한영화관
        private String selectedTheater;

        // Member의 아이디(외래키)
        private int memberId;


        //PayVO
        private String payDate;
        private String payMoney;

}
