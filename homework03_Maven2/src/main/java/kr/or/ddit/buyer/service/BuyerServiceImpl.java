package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BuyerServiceImpl implements IBuyerService{
	IBuyerDAO buyerDAO = new BuyerDAOImpl();
	@Override
	public List<BuyerVO> selectBuyerList() {
		List<BuyerVO> buyerlist = buyerDAO.selectBuyerList();
		return buyerlist;
	}
	
	@Override
	public ServiceResult registBuyer(BuyerVO buyer) {
		ServiceResult result = null;
		if(buyerDAO.selectBuyer(buyer.getBuyer_id())==null) {
			int rowCnt = buyerDAO.insertBuyer(buyer);
			if(rowCnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public ServiceResult registBuyerber(BuyerVO buyer) {
		return null;
	}

	@Override
	public long retrieveBuyerCount(PagingInfoVO pagingVO) {
		return buyerDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<BuyerVO> retrieveBuyerList(PagingInfoVO pagingVO) {
		List<BuyerVO> buyerList = buyerDAO.selectBuyerList(pagingVO);
		return buyerList;
	}

	@Override
	public BuyerVO retrieveBuyer(String buyer_id) {
		BuyerVO who = buyerDAO.selectBuyer(buyer_id);
		if(who == null) {
			throw new CommonException(buyer_id+"에 해당하는 회원이 없음.");
		}
		return who;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		ServiceResult result = null;
		BuyerVO vo = buyerDAO.selectBuyer(buyer.getBuyer_id());
//		BuyerVO vo = retrieveBuyer(buyer.getBuyer_id());
		if(vo==null){
			throw new CommonException();
		}else {
				  int rowCnt = buyerDAO.updateBuyer(buyer);
				  if(rowCnt>0) {
					  result = ServiceResult.OK;
				  }else {
					  result = ServiceResult.FAILED;
				  }
		}
		return result;
	}

}
