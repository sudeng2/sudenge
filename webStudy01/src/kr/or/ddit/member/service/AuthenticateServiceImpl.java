package kr.or.ddit.member.service;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.dao.MemberDAOImpl_Simple;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements IAuthenticateService{
	IMemberDAO memberDAO = new MemberDAOImpl();
	public static enum ServiceResult{PKNOTFOUND, INVALIDPASSWORD}
	@Override
	public Object authenticate(MemberVO member) {
		Object result = null;
		MemberVO savedMember = memberDAO.selectMember(member.getMem_id());
		//값이 넘어왔는데
		if(savedMember!=null) {
			//그 아이디와 비번이 같으면  그결과로 savedMember;
			if(savedMember.getMem_pass().equals(member.getMem_pass())) {
				result = savedMember;
				//비밀번호가 틀림
			}else {
				result = ServiceResult.INVALIDPASSWORD;
				System.out.println(result);
			}
		}else {
			result = ServiceResult.PKNOTFOUND;
		}
		return result;
	}

}
