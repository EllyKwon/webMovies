document.addEventListener('DOMContentLoaded', () => {
    let payMoney = document.getElementById("pay-money").value;
    let memberId = document.getElementById("member-id").value;
    let reserveId = document.getElementById("reserve-id").value;
    let IMP = window.IMP; // 생략가능
    IMP.init('imp82118087'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    let msg;

    IMP.request_pay({
        pg : 'kakaopay',
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(),
        name : 'megabox Clone 테스트 결제',
        amount : payMoney,
        buyer_postcode : '123-456',
    }, function(rsp) {
        if ( rsp.success ) {
            msg = '결제가 완료되었습니다.';
            msg += '\n고유ID : ' + rsp.imp_uid;
            msg += '\n상점 거래ID : ' + rsp.merchant_uid;
            msg += '\결제 금액 : ' + rsp.paid_amount;
            msg += '카드 승인번호 : ' + rsp.apply_num;

            alert(msg);

            //성공시 이동할 페이지
            location.href="/payKakao?memberId=" + memberId + "&payMoney=" + payMoney + "&reserveId=" + reserveId;
        } else {
            msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
            //실패시 이동할 페이지
            alert(msg);
            location.href="/";
        }
    });
});