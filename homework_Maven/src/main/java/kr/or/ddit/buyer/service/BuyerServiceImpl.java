package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.vo.BuyerVO;

public class BuyerServiceImpl implements IBuyerService{
	IBuyerDAO buyerDAO = new BuyerDAOImpl();
	@Override
	public List<BuyerVO> selectBuyerList() {
		List<BuyerVO> buyerlist = buyerDAO.selectBuyerList();
		return buyerlist;
	}

}
