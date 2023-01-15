package com.WebMovies.service;

import com.webMovies.mapper.PayMapper;
import com.webMovies.model.PayVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayService{

	private final PayMapper payMapper;

	public int insertPay(PayVO payVO) {
		return payMapper.insertPay(payVO);
	}

}
