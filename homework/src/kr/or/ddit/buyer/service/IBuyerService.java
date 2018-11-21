package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;

public interface IBuyerService {
	
	/**
	 * 거래처 목록 조회
	 * @return
	 */
	public List<BuyerVO>selectBuyerList();

}
