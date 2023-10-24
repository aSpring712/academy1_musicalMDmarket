package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SMemberDAOImpl implements SMemberDAO {

	// 디비세팅 ==> 싱글톤
	private static SMemberDAOImpl instance = new SMemberDAOImpl();
	public static SMemberDAOImpl getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}
	
	// 회원가입
	@Override
	public void memberJoin(SMemberDTO member) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "insert into memberdb values(?, ?, ?, ?, ?, ?, ?, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getUserid());
			ps.setString(3, member.getPwd());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getPhone());
			ps.setInt(6, member.getAdmin());
			ps.setString(7, member.getAddress());
			ps.setString(8, member.getZipcode());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
	}

	// 회원 전체 목록
	@Override
	public ArrayList<SMemberDTO> getMember() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null; // 결과 존재
		ArrayList<SMemberDTO> arr = new ArrayList<>(); // 회원 목록을 담을 배열
		
		try {
			con = getConnection();
			String sql = "select * from memberdb";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				SMemberDTO dto = new SMemberDTO();
				dto.setName(rs.getString("name"));
				dto.setUserid(rs.getString("userid"));
				dto.setPwd(rs.getString("pwd"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setAdmin(rs.getInt("admin"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress(rs.getString("address"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return arr;
	}

	// 수정하기
	@Override
	public int memberUpdate(SMemberDTO member) {
		Connection con = null;
		PreparedStatement ps = null;
		int flag = 0;
		
		try {
			con = getConnection();
			String sql = "update memberdb set name=?, pwd=?, email=?, phone=?, address=?, zipcode=? where userid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getPwd());
			ps.setString(3, member.getEmail());
			ps.setString(4, member.getPhone());
			ps.setString(5, member.getAddress());
			ps.setString(6, member.getZipcode());
			ps.setString(7, member.getUserid());
			
			flag = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
		
		return flag;
	}
	
	// 회원 삭제
	@Override
	public void memberDelete(String userid) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "delete from memberdb where userid = '" + userid + "'";			
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, null);
		}
	}

	// 상세보기
	@Override
	public SMemberDTO findById(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		SMemberDTO member = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from memberdb where userid = '" + userid + "'";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				member = new SMemberDTO();
				member.setName(rs.getString("name"));
				member.setPwd(rs.getString("pwd"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setAdmin(rs.getInt("admin"));
				member.setUserid(rs.getString("userid"));
				member.setAddress(rs.getString("address"));
				member.setZipcode(rs.getString("zipcode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return member;
	}
	
	// 회원수
	@Override
	public int memberCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select count(*) from memberdb";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return count;
	}

	// 아이디 중복 체크
	@Override
	public String memberIdCheck(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String flag = "yes"; // 사용 가능
		
		try {
			con = getConnection();
			String sql = "select * from memberdb where userid = '" + userid + "'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) { // rs 존재 -> 이미 존재하는 id
				flag = "no"; // 사용 불가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		
		return flag;
	}

	// 로그인 체크
	@Override
	public SMemberDTO memberLoginCheck(String userid, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		SMemberDTO member = new SMemberDTO();
		// 비회원 -> member 없음
		member.setAdmin(-1); // member가 없으므로 Admin 값에 -1

		try {
			con = getConnection();
			String sql = "select * from memberdb where userid = '" + userid + "'";
			st = con.createStatement(); // sql문 실행시킴
			rs = st.executeQuery(sql); 
			if(rs.next()) { // rx가 있다 -> 회원이다
				if(rs.getString("pwd").equals(pwd)) { // 비밀번호 일치 -> 회원(일반회원 또는 관리자)
					member.setAdmin(rs.getInt("admin"));
					member.setEmail(rs.getString("email"));
					member.setName(rs.getString("name"));
					member.setPhone(rs.getString("phone"));
					member.setPwd(rs.getString("pwd"));
					member.setUserid(rs.getString("userid"));
					member.setAddress(rs.getString("address"));
					member.setZipcode(rs.getString("zipcode"));
				} else { // 비번 오류
					member.setAdmin(2);
				}
			} // -1 회원 아님, 2면 비번 오류, 0 또는 1이면 회원 또는 관리자
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		
		return member;
	}

	// 닫기
	private void closeConnection(Connection con, PreparedStatement ps, Statement st, ResultSet rs) {
		try {
			if(rs != null) 
				rs.close();
			if(st != null) 
				st.close();
			if(ps != null) 
				ps.close();
			if(con != null) 
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
