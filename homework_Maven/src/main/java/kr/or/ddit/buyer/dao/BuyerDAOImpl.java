package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.ibatis.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.BuyerVO;

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
}
