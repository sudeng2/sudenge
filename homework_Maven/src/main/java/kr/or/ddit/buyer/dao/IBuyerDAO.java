package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;


public interface IBuyerDAO {
	/**
	 * 거래처 목록 조회
	 * @return
	 */
	public List<BuyerVO>selectBuyerList();
}
