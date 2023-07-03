package dto;

public class PageDto {
	
	int startNo; 		// 페이지 블럭 시작 번호
	int endNo; 			// 페이지 블럭 끝 번호
	int realEnd;		// 페이지 끝 페이지 번호 (<< 끝페이지로 이동 >>)
 	boolean prev, next;	// 이전, 다음 버튼(true: 보여주기)
 	
 	// 페이지블럭을 생성하기 위해 필요한 요소
 	int total;			// 총 게시물의 수
 	Criteria criteria;	// 페이지 번호, 페이지당 게시물
	
	public PageDto() {
		
	}
	
	// 페이지 블럭 초기화
	public PageDto(int total, Criteria criteria) {
		super();
		this.total = total;
		this.criteria = criteria;
		
		// 페이지 블럭의 끝번호 구하기
		// 7페이지 요청 : 올림(7/10.0) * 10
		endNo = (int)(Math.ceil(criteria.pageNo/10.0) * 10);
		startNo = endNo - (10-1);
		
		// 끝 페이지 번호
		// 55/10 = 5 -> 더블타입으로 계산후 올림 처리
		realEnd = (int)(Math.ceil((total*1.0)/criteria.getAmoute()));
		
		// 페이지 블럭의 끝번호를 설정
		// 게시물의 끝페이지보다 큰경우, 게시물의 끝페이지로 설정
		endNo = endNo>realEnd ?  realEnd : endNo;
		
		// 앞 뒤로 이동설정
		prev = startNo > 1 ? true : false;
		next = endNo == realEnd ? false : true;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public int getRealEnd() {
		return realEnd;
	}

	public void setRealEnd(int realEnd) {
		this.realEnd = realEnd;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

}
