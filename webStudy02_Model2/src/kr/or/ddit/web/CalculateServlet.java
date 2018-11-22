package kr.or.ddit.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.web.calculate.Mime;
import kr.or.ddit.web.calculate.Operator;

public class CalculateServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext application = getServletContext();
		String contentFolder = application.getInitParameter("contentFolder");
		File folder = new File(contentFolder);
		application.setAttribute("contentFolder",folder);
		System.out.println(getClass().getSimpleName()+"초기화");
		
	}
	/*@FunctionalInterface
	interface RealOperator{
		public int operate(int leftOp, int rightOp);
	}*/
	//enum Operator{
		/*ADD("+", new RealOperator() {
			@Override
			public int operate(int leftOp, int rightOp) {
				return leftOp + rightOp;
			}
		}),
		MINUS("-",(leftOp, rightOp)->{return leftOp - rightOp;}), 
		MULTIPLY("*",(a, b)->{return a * b;}), 
		DIVIDE("/", (c,d)->{return c/d;});
		
		private String sign;
		private RealOperator realOperator;
		Operator(String sign, RealOperator realOperator){
			this.sign = sign;
			this.realOperator = realOperator;
		}
		public String getSign(){
			return this.sign;
		}
		public int operate(int leftOp, int rightOp) {
			return realOperator.operate(leftOp, rightOp);
		}
	}*/
	/*class Operator{ 이것들을 아예 묶어서 넣어버린게 enum
		final Operator ADD = new Operator();
	}*/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파라미터 확보(입력태그의 name속성값으로 이름 결정)
		//resp.setContentType("text/text;charset=UTF-8");
		
		String left = req.getParameter("leftOp");
		String right = req.getParameter("rightOp");
		String operatorStr = req.getParameterValues("operator")[0];
		//검증
		boolean valid = true;
		int leftOp = 0;
		int rightOp = 0;
		if(left == null || !left.matches("\\d+")||right == null||!right.matches("\\d{1,6}")) {
			//불통 400에러 발생
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			valid = false;
		}
		
		Operator operator = null;
		//연산자검증
		try {
			operator =  Operator.valueOf(operatorStr.toUpperCase());
		}catch(Exception e) {//null, illegal 둘다 잡는다
			valid = false;
		}
		
		if(!valid) {
			//불통 400에러 발생
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		//통과
		//  연산자에 따라 연산 수행
		//		일반 텍스트의형태로 연산 결과를 제공.
		//연산결과 : 2 * 3 = 6
		
		
		leftOp = Integer.parseInt(left);
		rightOp = Integer.parseInt(right);
		String pattern = "%d %s %d = %d";
		String result = String.format(pattern, leftOp, operator.getSign(), rightOp, operator.operate(leftOp,rightOp));
		
		String accept = req.getHeader("Accept");
		Mime mime = Mime.getMime(accept);
		String mimeType = mime.getCode();
		if(accept.toUpperCase().contains(mime.name())) {
			result = "{\"result\":\""+result+"\"}";
		}else if(accept.toUpperCase().contains(mime.name())){
			result = "<p>" + result + "</p>";
		}	
		System.out.println(result);
		/*switch(operator) {
		case ADD : result = leftOp + "+" + rightOp + " = " +(leftOp+rightOp); break;
		case MINUS : result = leftOp + "-" + rightOp + " = " +(leftOp-rightOp); break;
		case MULTIPLY: result = leftOp + "*" + rightOp + " = " +(leftOp*rightOp); break;
		case DIVIDE : result = leftOp + "/" + rightOp + " = " +(leftOp/rightOp); break;
		case ADD:
			result = String.format(pattern, leftOp, operator.getSign(), rightOp,(leftOp+rightOp));
			break;
		case MINUS:
			result = String.format(pattern, leftOp, operator.getSign(), rightOp,(leftOp-rightOp));
			break;
		case MULTIPLY:
			result = String.format(pattern, leftOp, operator.getSign(), rightOp,(leftOp*rightOp));
			break;
		case DIVIDE:
			result = String.format(pattern, leftOp, operator.getSign(), rightOp,(leftOp/rightOp));
			break;
			
		}*/
		  resp.setContentType(mimeType);
		  PrintWriter out = resp.getWriter();
	      out.println(result);
	      out.close();
		
		
	}

}
