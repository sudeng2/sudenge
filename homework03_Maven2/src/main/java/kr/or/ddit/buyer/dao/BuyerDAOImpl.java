package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.ibatis.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BuyerDAOImpl implements IBuyerDAO{

	SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();

	@Override
	public List<BuyerVO> selectBuyerList() {
		try {
			List <BuyerVO> buyer =  sqlMapClient.queryForList("Buyer.selectBuyerList");
			return buyer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int insertBuyer(BuyerVO buyer) {
		try {
			return  sqlMapClient.update("Buyer.insertBuyer",buyer);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public BuyerVO selectBuyer(String buyer_id) {
		try {
			BuyerVO buyer = (BuyerVO)sqlMapClient.queryForObject("Buyer.selectBuyer", buyer_id);
			return buyer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(PagingInfoVO pagingVO) {
		try {
			List <BuyerVO> buyer =  sqlMapClient.queryForList("Buyer.selectBuyerList",pagingVO);
			return buyer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public long selectTotalRecord(PagingInfoVO pagingVO) {
		try {
			return(Long) sqlMapClient.queryForObject("Buyer.selectTotalRecord");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		try {
			return sqlMapClient.update("Buyer.updateBuyer",buyer);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
