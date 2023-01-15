package com.WebMovies.model;

import lombok.Data;

@Data
public class PayVO {
        // 결제일
        private String payDate;

        // 결제금액
        private String payMoney;

        // Member테이블 외래키
        private int memberId;

        // Reservation테이블 외래키
        private int reserveId;
}
