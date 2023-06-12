package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.ConnectionUtil;
import dto.Member;

public class MemberDao {

	public MemberDao() {

	}

	/**
	 * 로그인 아이디/ 비밀번호가 일치하는 회원을 조회 합니다.
	 * 
	 * 회언이 존재하는경우 Member 객체 반환 존재하지 않는경우 null 반환
	 * 
	 * @param id : 입력한 회원 id
	 * @param pw : 입력한 회원 pw
	 * @return
	 */
	public Member login(String id, String pw) {
		Member member = null;
		String sql = "Select * from member where id = ? and PASS = ?";

		// 리소드/자원을 자동 반환(close())
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement psmt = con.prepareStatement(sql);) {
			psmt.setString(1, id);
			psmt.setString(2, pw);

			ResultSet rs = psmt.executeQuery();

			// 아이디/ 비밀번호가 일치 하는 회원이 있으며 조회
			if (rs.next()) {
				String loginID = rs.getString("id");
				String name = rs.getString("name");
				String regidate = rs.getString("regidate");
				member = new Member(loginID, "", name, regidate);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return member;
	}
	
	/**
	 * 회원 등록
	 * @param member
	 * @return 몇건이 처리 되었는지 반환
	 */
	public int insert(Member member) {
		int i = 0;
		String sql = "insert into member values (?,?,?,sysdate)";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement psmt = con.prepareStatement(sql);) {
			
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getPass());
			psmt.setString(3, member.getName());
			
			i = psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
