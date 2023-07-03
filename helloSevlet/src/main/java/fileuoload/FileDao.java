package fileuoload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.ConnectionUtil;


public class FileDao {

	public FileDao() {

	}

	// 파일 정보를 저장 합니다
	public int insertFile(FileDto dto) {
		int res = 0;
		String sql = String.format("insert into myfile (idx, name, title, cate, ofile, sfile, postdate) "
				+ "values (seq_board_num1.nextval, ?, ?, ?, ?, ?, sysdate)");
		;

		try (Connection conn = ConnectionUtil.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getCate());
			pstmt.setString(4, dto.getOfile());
			pstmt.setString(5, dto.getSfile());
			
			// insert, update, delete 실행후 몇건이 처리 되었는지 반환
			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}

	// 파일 목록을 조회 합니다.
	public List<FileDto> getFileList(){
		List<FileDto> list = new ArrayList<FileDto>();
		
		String sql = "select * from myfile order by idx desc";
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();){
		
		while(rs.next()) {
			String idx = rs.getString("idx");
			String name = rs.getString("name");
			String title = rs.getString("title");
			String cate = rs.getString("cate");
			String ofile = rs.getString("ofile");
			String SFILE = rs.getString("SFILE");
			String postdate = rs.getString("postdate");
			
			FileDto dto = new FileDto(idx, name, title, cate, ofile, SFILE, postdate);
			
			list.add(dto);
		}
		} catch (SQLException e) {
			System.err.println("조회 하던중 예외가 발생하였습니다.");
			e.printStackTrace();
		}
		
		return list;
	}
}
