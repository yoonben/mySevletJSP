package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.ConnectionUtil;
import common.JSFunction;
import dto.Board;
import dto.Criteria;

public class BoardDao {

	public BoardDao() {
	}
	
	/**
	 * 게시물의 갯수를 반환
	 * @return
	 */
	public int getTotalCnt(Criteria criteria) {
		int totalCnt = 0;
		String sql = "select count(*) from board ";
		
				if(criteria.getSearchWord() != null && !"".equals(criteria.getSearchWord())){
					
					sql += "where " +criteria.getSearchField()+ " like '%"+ criteria.getSearchWord() +"%' order by num desc";
					
				}else {

					sql	+= "order by num desc";
					
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
	
	/**
	 * 게시글을 조회 합니다.
	 * @return
	 */
	public List<Board> getList(String searchField, String searchWord){
		
		List<Board> list = new ArrayList<Board>();
		
		String sql = "Select * "
				+ "from board ";
				
		if(searchWord != null && !"".equals(searchWord)){
			
			sql += "where " +searchField+ " like '%"+ searchWord +"%' order by num desc";
			
		}else {

			sql	+= "order by num desc";
			
		}
		
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();){
			
		while(rs.next()) {
			String num = rs.getString(1);
			String title = rs.getString(2);
			String content = rs.getString(3);
			String id = rs.getString(4);
			String postdate = rs.getString(5);
			String visitcount = rs.getString(6);
			
			Board board = new Board(num, title, content, id, postdate, visitcount);
			list.add(board);
		}
		} catch (SQLException e) {
			System.err.println("게시물 조회 하던중 예외가 발생하였습니다.");
			e.printStackTrace();
		}
		
		return list;
	}
	
public List<Board> getListPage(Criteria criteria){
		
		List<Board> list = new ArrayList<Board>();
		
		String sql = "select * "
				+ "from "
				+ "(select rownum rn, board.* "
				+ "from "
				+ "(select * from board order by num desc) board ";
				
		if(criteria.getSearchWord() != null && !"".equals(criteria.getSearchWord())){
			
			sql += "where " +criteria.getSearchField()+ " like '%"+ criteria.getSearchWord() +"%' order by num desc ";
			
		}
		sql += ") "
				+ "where rn BETWEEN "+criteria.getStartNo() +" and "+criteria.getEndNo();
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();){
			
		while(rs.next()) {
			String num = rs.getString("num");
			String title = rs.getString("title");
			String content = rs.getString("content").replace("\r\n", "<br/>");
			String id = rs.getString("id");
			String postDate = rs.getString("postdate");
			String visitCount = rs.getString("visitcount");
			
			Board board  = new Board(num,title,content,id,postDate,visitCount);
			
			list.add(board);
		}
		} catch (SQLException e) {
			System.err.println("게시물 조회 하던중 예외가 발생하였습니다.");
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 게시글을 등록 합니다.
	 * @param book
	 * @return
	 */
	public int insert(Board board) {
		int res = 0;
		
		String sql = String.format("insert into board (num, title, content, id, postdate, visitcount) "
				+ "values (seq_board_num.nextval, ?, ?, ?, sysdate, 0)");
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
				
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getId());
			
			// insert, update, delete 실행후 몇건이 처리 되었는지 반환
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public Board seletOne(String num) {
		Board board = null;
		
		String sql = "select * from board where num = ? order by num desc";
		
		if(num == null || "".equals(num)) {
			return null;
		}
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
				
			pstmt.setString(1, num);
			
			ResultSet rs = pstmt.executeQuery();
			
			// 1건의 게시글을 조회 하여 board 객체에 담아줍니다.
			if(rs.next()) {
				board = new Board();
				
				board.setNum(rs.getString(1));
				board.setTitle(rs.getString(2));
				board.setContent(rs.getString(3));
				board.setId(rs.getString(4));
				board.setPostdate(rs.getString(5));
				board.setVisitcount(rs.getString(6));
				
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board;
	}
	
	/**
	 * 게시물의 조회수를 1증가 시킵니다
	 * @param num 게시물 번호
	 * @return 업데이트된 건수
	 */
	public int updateVisitCount(String num) {
		int res = 0;
		
		JSFunction js = new JSFunction();
		
		String sql = "UPDATE board SET visitcount = visitcount + 1 WHERE num = ?";
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setString(1, num);
			
			ResultSet rs = pstmt.executeQuery();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public int Edit(Board board) {
		int res = 0;
		
		String sql = "UPDATE board SET TITLE = ?, CONTENT = ? WHERE num = ?";
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getNum());
			
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public int Delete(String num) {
		int res = 0;
		String sql = "DELETE board where num = "+num;
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return res;
	}
}
