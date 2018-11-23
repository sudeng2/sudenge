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

public class BuyerInsertController implements ICommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String method = req.getMethod();
		String view = null;
		if("get".equalsIgnoreCase(method)) {
			view = doGet(req,resp);
		}else if("post".equalsIgnoreCase(method)) {
			view = doPost(req, resp);
		}else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		return view;
		
	}
	protected String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "buyer/buyerForm";
		return view;
	}
	protected String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		   BuyerVO buyer = new BuyerVO();
		   req.setAttribute("buyer",buyer);
//		   member.setMem_id(req.getParameter("mem_id"));
		   try {
			   BeanUtils.populate(buyer, req.getParameterMap());
		   } catch (IllegalAccessException | InvocationTargetException e) {
			   throw new CommonException(e);
		   }
		   String goPage = null; //이동하는 페이지
		   String message = null; //에러메시지 
		   Map<String, String> errors = new LinkedHashMap<>();
		   req.setAttribute("errors", errors);
		   boolean valid = validate(buyer, errors);
		   System.err.println(errors.size());
		   if (valid) {
		      //로직 객체와의 의존관계 형성
		      IBuyerService service = new BuyerServiceImpl();
		      ServiceResult result = service.registBuyer(buyer);
		      switch (result) {
		      case PKDUPLICATED:
		         goPage = "buyer/buyerForm";
		         message = "아이디가 중복되셨슴다";
		         break;
		      case FAILED:
		         goPage = "buyer/buyerForm";
		         message = "서버 오류로 실패, 잠시 뒤 다시 시도해주세욤";
		         break;
		      case OK:
		         goPage = "redirect:/buyer/buyerList.do";
		         break;
		      }
		      req.setAttribute("message", message);
		   } else {
		      goPage = "buyer/buyerForm";
		   }

		   return goPage;

	}
	private boolean validate(BuyerVO buyer, Map<String, String> errors) {
	      boolean valid = true;
	      //검증룰
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
