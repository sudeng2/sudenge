package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * totalRecord와 currentPage를 결정하면, 나머지 속성들이 연산됨.
 * setTotalRecord/setCurrentPage호출 필요.
 */

@Data
@NoArgsConstructor
public class PagingInfoVO {
	
	public PagingInfoVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
	private long totalRecord;
	private int screenSize=10;
	private int blockSize=5;
	private long currentPage;
	private long totalPage;
	private long startPage;
	private long endPage;
	private long startRow;
	private long endRow;
	private List<MemberVO> dataList;
	private List<BuyerVO> dataList2;
	
	
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = totalRecord%screenSize == 0?totalRecord/screenSize:totalRecord/screenSize+1;
	}
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize-1);
		startPage = (currentPage-1)/blockSize*blockSize+1;
		endPage = startPage + (blockSize-1);
	}
//	
//    
//    <li class="page-item"><a class="page-link" href="?page=1">1</a></li>
//    <li class="page-item"><a class="page-link" href="?page=2">2</a></li>
//    <li class="page-item"><a class="page-link" href="?page=3">3</a></li>
//    <li class="page-item"><a class="page-link" href="#">Next</a></li>
//  </ul>
	public String getPagingHTML() {
		String pattern = "<li class='page-item %s'><a class='page-link' href='?page=%d'>%s</a></li>";
		StringBuffer html = new StringBuffer();
		html.append("<ul class='pagination'>");
		if(startPage>1) {
			html.append(String.format(pattern,"",(startPage-1),"이전"));
		}
		if(endPage> totalPage) endPage = totalPage;
		for(long page = startPage; page<=endPage;page++) {
			String active = "";
			String pageStr = page+"";
			if(page == currentPage) {
				active = "active";
				pageStr += " <span class=\"sr-only\">(current)</span>";
			}
			html.append(String.format(pattern,active,page,pageStr));
		}
		if(endPage<totalPage) {
			html.append(String.format(pattern,"",endPage+1,"다음"));
		}
		html.append("</ul>");
		return html.toString();
	}
	
	
}
