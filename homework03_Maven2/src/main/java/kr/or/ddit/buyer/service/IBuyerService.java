package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IBuyerService {
	
	/**
	 * 거래처 목록 조회
	 * @return
	 */
	public List<BuyerVO>selectBuyerList();
	
	
	/**
	 *  거래처 신규 등록
	 * @param buyer
	 * @return
	 */
	public ServiceResult registBuyer(BuyerVO buyer);
	
	
	/**
	 * 회원 정보 상세 조회
	 * @param buyer_id 조회할 거래처의 아이디
	 * @return 존재하지 않는다면, CommonException 발생
	 */
	public BuyerVO retrieveBuyer(String buyer_id);
	
	
	public ServiceResult registBuyerber(BuyerVO buyer);
	
	/**
	 * 거래처 목록 조회
	 * @param pagingVO TODO
	 * @return 존재하지 않을때, size()==0
	 */
	public List<BuyerVO> retrieveBuyerList(PagingInfoVO pagingVO);
	
	/**
	 * 회원 정보 수정
	 * @param buyer
	 * @return CommonException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult modifyBuyer(BuyerVO buyer);
	
	/**
	 * 페이징 페이지
	 * @param pagingVO
	 * @return
	 */
	public long retrieveBuyerCount(PagingInfoVO pagingVO);

}
