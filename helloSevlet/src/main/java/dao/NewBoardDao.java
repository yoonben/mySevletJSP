package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.ConnectionUtil;
import dto.Board;
import dto.Criteria;

public class NewBoardDao {

	public NewBoardDao() {
		// TODO Auto-generated constructor stub
	}

	public List<Board> getList(Criteria criteria){
		List<Board> list = new ArrayList<>();
		
		String sql = "select  NUM,TITLE,CONTENT,ID"
				+ ",decode(TRUNC(sysdate),TRUNC(postdate),to_char(postdate, 'hh24:mi:ss')"
													+ ", to_char(postdate, 'yyyy-mm-dd')),VISITCOUNT from board ";

		if(criteria.getSearchWord()!= null 
				&& criteria.getSearchField() != null) {
			sql += "where "+criteria.getSearchField()+" Like '%"+criteria.getSearchWord()+"%'";
		}
		
		
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();){
			
			while(rs.next()) {
				String num = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String id = rs.getString(4);
				String postDate = rs.getString(5);
				String visitCount = rs.getString(6);
				
				Board board  = new Board(num,title,content,id,postDate,visitCount);
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Board> getListPage(Criteria criteria){
		List<Board> list = new ArrayList<>();
		
		String sql = "select * from (select rownum rn, t.* "
				+ "from (select  NUM,TITLE,CONTENT,ID"
				+ ",decode(TRUNC(sysdate),TRUNC(postdate),to_char(postdate, 'hh24:mi:ss'), to_char(postdate, 'yyyy-mm-dd'))POSTDATE,VISITCOUNT "
				+ "from board ";

		if(criteria.getSearchWord() != null && !"".equals(criteria.getSearchWord())) {
			sql += "where "+criteria.getSearchField()+" like '%"+criteria.getSearchWord()+"%' ";
		}
		
		sql += "order by num desc) t )"
				+ "where rn between "+criteria.getStartNo()+" and "+criteria.getEndNo();
		
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();){
			
			while(rs.next()) {
				String num = rs.getString("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				String postDate = rs.getString("postdate");
				String visitCount = rs.getString("visitcount");
				
				Board board  = new Board(num,title,content,id,postDate,visitCount);
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int insert(Board board) {
		int res = 0;
		
		String sql = String.format("insert into board "
				+ "values (seq_board_num.nextval, ?, ?, ?, sysdate, 0)");
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
				
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getId());
			
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public Board selectOne(String num) {
		String sql = "select * from board where num = ?";
		
		Board board = null;
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement psmt = conn.prepareStatement(sql);){
			
			psmt.setString(1, num);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				
				board = new Board();
				
				board.setContent(rs.getString("content"));
				board.setId(rs.getString("id"));
				board.setNum(rs.getString("num"));
				board.setPostdate(rs.getString("postdate"));
				board.setTitle(rs.getString("title"));
				board.setVisitcount(rs.getString("visitcount"));
				
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return board;
	}
	
	public void updateVisitCount(String num) {
		
		String sql = "update board set visitcount = visitcount+1 where num = "+num;
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){

				ResultSet rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
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
	}