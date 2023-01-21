package com.webMovies.model;

import lombok.Data;

@Data
public class ReservationVO {
        // 예약번호
        private int reserveId;

        // 영화제목
        private String title;

        // 상영시간
        private String playtime;

        // 연령제한
        private String age;

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
}
