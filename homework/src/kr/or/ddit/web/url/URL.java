package kr.or.ddit.web.url;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.web.calculate.Mime;

public enum URL {
	GUGUDAN("/01/gugudanForm.html"),
	LYRICS("/02/musicForm.jsp"),
	CALENDAR("/04/Calendar2.jsp"),
	IMAGEVIEW("/imageForm"),
	GUGUDANVAL("/gugudan.do");
	
	public String url;
	
	public String getUrlAddress() {
		return this.url;
	}
	URL(String url){
		this.url = url;
	}
	public static URL getUrlType(String accept) {
		URL urlAddress = GUGUDAN;
		if(StringUtils.isNotBlank(accept)) {
			for(URL u : URL.values()) {
				if(accept.toUpperCase().equals(u.name())) {
					urlAddress = u;
					break;
				}
			}
		}
		return urlAddress;

	}
}
