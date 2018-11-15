<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>04/calendar2.jsp</title>
<style type = "text/css">
	.sunday{
		color : red;
	}
	.saturday{
		color : blue;
	}
	table{
		width:100%;
		height:500px;
		border-collapse:collapse;
	}
	td, th{
		border:1px solid black;
	}
</style>
<script type = "text/javascript">
	function eventHandler(year, month){
		var form = document.calForm;
		if((year && month) || month==0){
			form.year.value = year;
			form.month.value = month;
		}
			form.submit();
			return false;
	}
</script>


<%
	request.setCharacterEncoding("UTF-8");//UTF-8로 인코딩한다.
	String yearStr = request.getParameter("year");//파라미터값으로 year받음
	String monthStr = request.getParameter("month");//파라미터값으로 month받음
	String language = request.getParameter("language");//파라미터값으로 language받음
	
	
	Locale clientLocale = request.getLocale(); /* request는 클라이언트에대한 모든 정보를 가지고있음 */
	//언어선택하면 선택한 언어로 바뀌게한다.
	if(language!=null && language.trim().length()>0){//만약 선택한 언어가 있으면 그언어로 바뀜
		clientLocale = Locale.forLanguageTag(language);
	}
	DateFormatSymbols symbols = new DateFormatSymbols(clientLocale);
	
	//Calendar cal = Calendar.getInstance(); /* 위에 Calendar선언해줘서 calendar.을 지워도됨 */
	Calendar cal = getInstance();//캘린더객체불러온다.
	//만약if문 조건이 만족하면 파라미터값으로 받은 년/월이 셋팅이된다.
	if(yearStr!=null && yearStr.matches("\\d{4}")&&monthStr != null && monthStr.matches("1[0-1]|\\d")){
		cal.set(YEAR, Integer.parseInt(yearStr));
		cal.set(MONTH, Integer.parseInt(monthStr));
		
	}
	
	//int currentYear = cal.get(Calendar.YEAR);/*  몇년몇월인지 꺼내야함 */
	int currentYear = cal.get(YEAR);/*  몇년몇월인지 꺼내야함, 현재년도 */
	//int currentMonth = cal.get(Calendar.MONTH);
	int currentMonth = cal.get(MONTH);//현재 월
	
	
	//1일이 몇요일인지 셋팅해주기위함
	//cal.set(Calendar.DAY_OF_MONTH, 1); /*한달에서 몇번째 날, calendar에서 11월1일날짜를 먼저 셋팅해놓고 요일을 꺼내야한다. */
	cal.set(DAY_OF_MONTH, 1); /*한달에서 몇번째 날, calendar에서 11월1일날짜를 먼저 셋팅해놓고 요일을 꺼내야한다. */
	int firstDayOfweek = cal.get(DAY_OF_WEEK); /* 캘린더는 일욜을 1로 반환 1~7 */
	int offset = firstDayOfweek-1;
	int lastDate = cal.getActualMaximum(DAY_OF_MONTH); /* 한달에서 제일 큰 값 */
	
	cal.add(MONTH, -1); /*이전달가는거,날짜연산, 월에서 1을 차감하겠다. amount는 전 달이니까 -1 */
	int beforeYear = cal.get(YEAR);
	int beforeMonth = cal.get(MONTH);
	
	cal.add(MONTH, +2);/* 다음달 가는거, 전 달로 이미 가있는 상태기 떄문에 +2*/
	int nextYear = cal.get(YEAR);
	int nextMonth = cal.get(MONTH);
	
	Locale[] locales = Locale.getAvailableLocales();//현재 시스템에서 지원되는 모든 로케일을 받아올수 있음
%>
<form name = "calForm" action = "./" method = "post">
<h4>
<a href = "javascript:eventHandler(<%=beforeYear%>, <%=beforeMonth%>);">이전달</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<input type = "number" name = "year" value = "<%=currentYear %>" onblur="eventHandler();"/>년 <!-- onblur는 현재 엘리먼트가 포커스 잃엇을때, focus는 얻엇을때-->
<select name = "month" onchange="eventHandler();">
<!-- 	//<option value = "0">1월</option> -->
	<%
	//월출력
		String[] monthStrings = symbols.getShortMonths();
		for(int idx = 0; idx<monthStrings.length-1; idx++){
			out.println(String.format("<option value = '%d' %s>%s</option>", 
					idx,idx==currentMonth?"selected":"",monthStrings[idx]));
		}
	%>
</select>
<select name = "language" onchange = "eventHandler();">
	<%
	//언어선택 UI
		for(Locale tmp:locales){
			out.println(String.format("<option value = '%s'%s>%s</option>",
					tmp.toLanguageTag(),
					tmp.equals(clientLocale)?"selected":"",
					tmp.getDisplayLanguage(clientLocale)));
		}
	%>

</select>
&nbsp;&nbsp;&nbsp;&nbsp;
<a onclick = "eventHandler(<%=nextYear%>, <%=nextMonth%>);">다음달</a>

</h4>
<input type = "hidden" name = "command" value = "CALENDAR"/>
</form>
<table>
<thead>
	<tr>
		<%
	//요일데이터출력 
			//String[] dayStr = new String[]{"일","월"};
			
			/* Locale clientLocale = request.getLocale(); /* request는 클라이언트에대한 모든 정보를 가지고있음 
			DateFormatSymbols symbols = new DateFormatSymbols(); */
			String[] dateStrings = symbols.getShortWeekdays();
			for(int idx = Calendar.SUNDAY; idx<=Calendar.SATURDAY; idx++){
				out.println(String.format("<th>%s</th>", dateStrings[idx]));
			}
		%>
	</tr>
</thead>
<tbody>
<%
	//날짜출력
	int dayCount = 1;
	for(int row = 1; row<=6; row++){
		%>
		<tr>
		<%
		for(int col = 1; col<=7; col++){
			int dateChar = dayCount++ - offset;//날짜를 맞게 출력
			if(dateChar < 1 || dateChar > lastDate){
				out.println("<td>&nbsp;</td>");
			}else{
				String clzValue = "normal";
				if(col==1){
					clzValue = "sunday";//스타일적용
				}else if(col==7){
					clzValue = "saturday";//스타일적용
				}
				out.println(String.format("<td class = '%s'>%d</td>",clzValue, dateChar));
			}
		}
		%>
		</tr>
		<%
	}
%>
</tbody>
</table>
