package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class QnAboardDAOImpl implements QnAboardDAO {

	// DB 세팅 ==> 싱글톤
	private static QnAboardDAOImpl instance = new QnAboardDAOImpl();
	
	public static QnAboardDAOImpl getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}
	
	// 글쓰기
	@Override
	public void boardInsert(QnAboardDTO board) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "insert into qnaboard values(qnaboard_seq.nextval, ?, ?, ?, sysdate, 0, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getSubject());
			ps.setString(2, board.getEmail());
			ps.setString(3, board.getContent());
			ps.setString(4, board.getUserid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
	}

	// 수정하기
	@Override
	public void boardUpdate(QnAboardDTO board) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "update qnaboard set subject=?, email=?, content=?, regdate=sysdate where num=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getSubject());
			ps.setString(2, board.getEmail());
			ps.setString(3, board.getContent());
			ps.setInt(4, board.getNum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
	}

	// 전체보기
	@Override
	public ArrayList<QnAboardDTO> boardList(int startRow, int endRow, String field, String word) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<QnAboardDTO> arr = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		
		try {
			con = getConnection();
			sb.append("select * from (");
			sb.append("select rownum rn, aa.* from(");
			sb.append("select * from qnaboard where "+ field + " like '%" + word + "%'");
			sb.append(" order by num desc) aa");
			sb.append(" where rownum <= ?");
			sb.append(") where rn >= ?");
			
			System.out.println(sb.toString());
			
			ps = con.prepareStatement(sb.toString());
			ps.setInt(1, endRow); // 첫번째 ?에 endRow
			ps.setInt(2, startRow); // 두번째 ?에 startRow
			rs = ps.executeQuery();
			
			while(rs.next()) {
				QnAboardDTO board = new QnAboardDTO();
				board.setContent(rs.getString("content"));
				board.setEmail(rs.getString("email"));
				board.setNum(rs.getInt("num"));
				board.setReadcount(rs.getInt("readcount"));
				board.setRegdate(rs.getString("regdate"));
				board.setSubject(rs.getString("subject"));
				board.setUserid(rs.getString("userid"));
				arr.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, rs);
		}
		return arr;
	}

	// 게시글 삭제
	@Override
	public int boardDelete(int num) {
		Connection con = null;
		Statement st = null;
		int flag = 0;
		
		try {
			con = getConnection();
			String sql = "delete from qnaboard where num = " + num;
			st = con.createStatement();
			flag = st.executeUpdate(sql); // 수행된 행 수 반환 -> 삭제가 되었으면 1
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, null);
		}
		return flag; // 0이면 삭제안된 것, 1이면 삭제 1개 된 것
	}

	// 개수
	@Override
	public int boardCount(String field, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con = getConnection();
			String sql = "select count(*) from qnaboard where " + field + " like '%" + word + "%'";
			st = con.createStatement();
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

	// 상세보기
	@Override
	public QnAboardDTO findByNum(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		QnAboardDTO board = null;
		String sql = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
			sql = "Update qnaboard set readcount = readcount+1 where num = " + num;
			st.executeUpdate(sql);
			sql = "select * from qnaboard where num = " + num;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				board = new QnAboardDTO();
				board.setContent(rs.getString("content"));
				board.setEmail(rs.getString("email"));
				board.setNum(rs.getInt("num"));
				board.setReadcount(rs.getInt("readcount"));
				board.setRegdate(rs.getString("regdate"));
				board.setSubject(rs.getString("subject"));
				board.setUserid(rs.getString("userid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return board;
	}

	// comment 전체보기
	@Override
	public ArrayList<QnAcommentDTO> findAllComment(int bnum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<QnAcommentDTO> carr = new ArrayList<>();
		
		try {
			con = getConnection();
			String sql = "select * from qnacomboard where bnum = " + bnum + " order by cnum desc";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				QnAcommentDTO comment = new QnAcommentDTO();
				comment.setBnum(rs.getInt("bnum"));
				comment.setCnum(rs.getInt("cnum"));
				comment.setMsg(rs.getString("msg"));
				comment.setRegdate(rs.getString("regdate"));
				comment.setUserid(rs.getString("userid"));
				carr.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return carr;
	}

	// Comment 입력
	@Override
	public void commentInsert(QnAcommentDTO comment) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "insert into qnacomboard values(qnacomboard_seq.nextval, ?, ?, ?, sysdate)";
			ps = con.prepareStatement(sql);
			ps.setString(1, comment.getMsg());
			ps.setInt(2, comment.getBnum());
			ps.setString(3, comment.getUserid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
	}
	
	// 닫기
		private void closeConnection(Connection con, PreparedStatement ps, Statement st, ResultSet rs) {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
