package com.WebMovies.mapper;

import com.webMovies.model.PayVO;

public interface PayMapper {

    int insertPay(PayVO payVO);

    PayVO getDataForPay(String memberId);

}
