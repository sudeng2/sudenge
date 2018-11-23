package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;

public class BuyerUpdateController implements ICommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 서블릿 작성

		BuyerVO buyer = new BuyerVO();
		req.setAttribute("buyer", buyer);
		try {
			BeanUtils.populate(buyer, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new CommonException(e);
		}
		String goPage = null;
		String message = null;
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(buyer, errors);
		if (valid) {
			IBuyerService service = new BuyerServiceImpl();
			ServiceResult result = service.modifyBuyer(buyer);
			switch (result) {
			case FAILED:
				goPage = "buyer/buyerView";
				message = "서버가 실패했슴둥.";
				break;
			case OK:
//						goPage = "/member/memberView.do?who="+member.getMem_id();
				goPage = "redirect:/buyer/buyerList.do";
				break;
			}
			req.setAttribute("message", message);
		} else {
			goPage = "buyer/buyerView";
		}
		return goPage;
	}

	private boolean validate(BuyerVO buyer, Map<String, String> errors) {
		boolean valid = true;

		if (StringUtils.isBlank(buyer.getBuyer_id())) {
			valid = false;
			errors.put("buyer_id", "거래처아이디 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_name())) {
			valid = false;
			errors.put("buyer_name", "거래처이름 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_bank())) {
			valid = false;
			errors.put("buyer_bank", " 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_bankno())) {
			valid = false;
			errors.put("buyer_bankno", "누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_bankname())) {
			valid = false;
			errors.put("buyer_bankname", "누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_zip())) {
			valid = false;
			errors.put("buyer_zip", "누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_add1())) {
			valid = false;
			errors.put("buyer_add1", " 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_add2())) {
			valid = false;
			errors.put("buyer_add2", " 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_comtel())) {
			valid = false;
			errors.put("buyer_comtel", " 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_fax())) {
			valid = false;
			errors.put("buyer_fax", " 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_mail())) {
			valid = false;
			errors.put("buyer_mail", " 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_charger())) {
			valid = false;
			errors.put("buyer_charger", " 누락");
		}
		return valid;
	}

}
