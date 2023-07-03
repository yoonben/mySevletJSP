package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.ConnectionUtil;
import dto.Criteria;
import dto.MvcBoard;
import model2.mvcboard.MVCBoardDto;

public class MvcBoardDao {
	
public List<MvcBoard> getList(){
		
		List<MvcBoard> list = new ArrayList<MvcBoard>();
		
		String sql = "select * from MVCBOARD order by idx desc";
		
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();){
			
		while(rs.next()) {
			MvcBoard mvcBoard = new MvcBoard();
			
			mvcBoard.setIdx(rs.getString("idx"));
			mvcBoard.setName(rs.getString("name"));
			mvcBoard.setTitle(rs.getString("title"));
			mvcBoard.setContent(rs.getString("content"));
			mvcBoard.setPostdate(rs.getString("postdate"));
			mvcBoard.setSfile(rs.getString("sfile"));
			mvcBoard.setDowncount(rs.getInt("downcount"));
			mvcBoard.setPass(rs.getString("pass"));
			mvcBoard.setVisitcount(rs.getInt("visitcount"));
			
			list.add(mvcBoard);
		}
		} catch (SQLException e) {
			System.err.println("게시물 조회 하던중 예외가 발생하였습니다.");
			e.printStackTrace();
		}
		
		return list;
	}
	
public List<MvcBoard> getListPage(Criteria criteria) {

	List<MvcBoard> list = new ArrayList<MvcBoard>();

	
	String sql = "select * "
			+ "from "
			+ "(select rownum rn, MVCBOARD.* "
			+ "from "
			+ "(select * from MVCBOARD order by idx desc) MVCBOARD ";
			
	if(criteria.getSearchWord() != null && !"".equals(criteria.getSearchWord())){
		
		sql += "where " +criteria.getSearchField()+ " like '%"+ criteria.getSearchWord() +"%' order by idx desc ";
		
	}
	sql += ") "
			+ "where rn BETWEEN "+criteria.getStartNo() +" and "+criteria.getEndNo();
	

	try (Connection conn = ConnectionUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();) {

		while (rs.next()) {
			MvcBoard mvcBoard = new MvcBoard();

			mvcBoard.setIdx(rs.getString("idx"));
			mvcBoard.setName(rs.getString("name"));
			mvcBoard.setTitle(rs.getString("title"));
			mvcBoard.setContent(rs.getString("content"));
			mvcBoard.setPostdate(rs.getString("postdate"));
			mvcBoard.setSfile(rs.getString("sfile"));
			mvcBoard.setDowncount(rs.getInt("downcount"));
			mvcBoard.setPass(rs.getString("pass"));
			mvcBoard.setVisitcount(rs.getInt("visitcount"));

			list.add(mvcBoard);
		}
	} catch (SQLException e) {
		System.err.println("게시물 조회 하던중 예외가 발생하였습니다.");
		e.printStackTrace();
	}

	return list;
}

public int getTotalCnt(Criteria criteria) {
	int totalCnt = 0;
	String sql = "select count(*) from MVCBOARD ";
			if(criteria.getSearchWord() != null && !"".equals(criteria.getSearchWord())){
				
				sql += "where " +criteria.getSearchField()+ " like '%"+ criteria.getSearchWord() +"%' order by idx desc";
				
			}else {

				sql	+= "order by idx desc";
				
			}
	try (Connection conn = ConnectionUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();){
		rs.next();
		totalCnt = rs.getInt(1);
	} catch (SQLException e) {
		System.err.println("총 게시물 조회 하던중 예외가 발생하였습니다.");
		e.printStackTrace();
	}
	
	
	
	return totalCnt;
}


	public MvcBoardDao() {
		
	}

	public int isert(MVCBoardDto dto) {
		int res = 0;
		
		String sql = String.format("insert into mvcboard (idx, name, title, content, pass, ofile, sfile)"
				+ " values (seq_board_num1.nextval, ?, ?, ?, ?, ?, ?)");
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
				
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPass());
			pstmt.setString(5, dto.getOfile());
			pstmt.setString(6, dto.getSfile());
			
			// insert, update, delete 실행후 몇건이 처리 되었는지 반환
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("게시물 입력중 예외 발생");
			e.printStackTrace();
		}
		
		return res;
	}
	
	public int upDate(MVCBoardDto dto) {
		int res = 0;
		
		String sql = "UPDATE mvcboard SET name = ?, title = ?, content = ?, pass= ?, ofile = ?, sfile= ? WHERE idx = ?";
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
				
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPass());
			pstmt.setString(5, dto.getOfile());
			pstmt.setString(6, dto.getSfile());
			pstmt.setString(7, dto.getIdx());
			
			// insert, update, delete 실행후 몇건이 처리 되었는지 반환
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("게시물 입력중 예외 발생");
			e.printStackTrace();
		}
		
		return res;
	}
	
	public MvcBoard seletOne(String num) {
		MvcBoard mvcBoard = null;
		
		String sql = "select * from MVCBOARD where idx = ? order by idx desc";
		
		if(num == null || "".equals(num)) {
			return null;
		}
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
				
			pstmt.setString(1, num);
			
			ResultSet rs = pstmt.executeQuery();
			
		if(rs.next()) {
			mvcBoard = new MvcBoard();
			
			mvcBoard.setIdx(rs.getString("idx"));
			mvcBoard.setName(rs.getString("name"));
			mvcBoard.setTitle(rs.getString("title"));
			mvcBoard.setContent(rs.getString("content"));
			mvcBoard.setPostdate(rs.getString("postdate"));
			mvcBoard.setSfile(rs.getString("sfile"));
			mvcBoard.setDowncount(rs.getInt("downcount"));
			mvcBoard.setPass(rs.getString("pass"));
			mvcBoard.setVisitcount(rs.getInt("visitcount"));
			
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mvcBoard;
	}

	
}

