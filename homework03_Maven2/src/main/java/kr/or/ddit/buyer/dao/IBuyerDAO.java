package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;


public interface IBuyerDAO {
	/**
	 * 거래처 목록 조회
	 * @return
	 */
	public List<BuyerVO>selectBuyerList();
	
	/**
	 * 거래처 신규등록
	 * @param buyer 등록할 거래처 정보를 가진 VO
	 * @return성공(>0)실패
	 */
	public int insertBuyer(BuyerVO buyer);
	
	/**
	 * 회원정보 수정
	 * @param member 수정할 정보를 가진 vo
	 * @return 성공(>0)실패
	 */
	public int updateBuyer(BuyerVO buyer);
	
	/**
	 * 거래처 정보 상세 조회
	 * @param buyer_id
	 * @return
	 */
	public BuyerVO selectBuyer(String buyer_id);
	
	/**
	 * 거래처 목록 조회
	 * @param pagingVO TODO
	 * @return 존재하지 않는다면 .. size()==0
	 */
	public List<BuyerVO>selectBuyerList(PagingInfoVO pagingVO);
	
	/**
	 * 페이징을 위한 전체 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public long selectTotalRecord(PagingInfoVO pagingVO);
}
