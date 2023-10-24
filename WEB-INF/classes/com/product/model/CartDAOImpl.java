package com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CartDAOImpl implements CartDAO {
	
	// 디비 세팅 ==> 싱글톤
	private static CartDAOImpl instance = new CartDAOImpl();
	
	public static CartDAOImpl getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp"); 
		return ds.getConnection();
	}

	// 장바구니에 추가
	@Override
	public void insert(CartDTO cart) {
		Connection con = null;
		PreparedStatement ps = null;
				
		try {
			con = getConnection();
			String sql = "insert into cart (userid, p_id, cart_id, amount) values(?, ?, cart_seq.nextval, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, cart.getUserid());
			ps.setInt(2, cart.getProduct_id());
			ps.setInt(3, cart.getAmount());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
	}

	// 회원별 장바구니에 담긴 목록 보기
	@Override
	public ArrayList<CartDTO> listCart(String userid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CartDTO> carr = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		
		try {
				con = getConnection();
				
				sb.append("select cart_id, c.regdate, c.userid, m.name, p.p_id, p_name, amount, p_unitprice, (p_unitprice*amount) sumPrice");
				sb.append(" from memberdb m, cart c, product p");
				sb.append(" where m.userid=c.userid and p.p_id = c.p_id and c.userid = '" + userid + "'");
				
				System.out.println(sb.toString());
				
				ps = con.prepareStatement(sb.toString());
				rs = ps.executeQuery();
				
				while(rs.next()) {
					CartDTO cart = new CartDTO();
					cart.setAmount(rs.getInt("amount")); // 개수
					cart.setCart_id(rs.getInt("cart_id"));
					cart.setMoney(rs.getInt("sumPrice"));
					cart.setName(rs.getString("name"));
					cart.setPrice(rs.getInt("p_unitprice")); // 개당 금액
					cart.setProduct_id(rs.getInt("p_id"));
					cart.setProduct_name(rs.getString("p_name"));
					cart.setUserid(rs.getString("userid"));
					carr.add(cart);
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, rs);
		}
		return carr;
	}

	// 장바구니 비우기
	@Override
	public void deleteAll(String userid) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			String sql = "delete from cart where userid = '" + userid + "'";
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, null);
		}
	}

	// 총 지불가격
	@Override
	public int totalPrice(String userid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int totalPrice = 0;
		StringBuilder sb = new StringBuilder();
		
		try {
				con = getConnection();
				
				sb.append("select sum(sumPrice) from (select c.regdate, c.userid, m.name, p.p_id, p_name, amount, p_unitprice, (p_unitprice*amount) sumPrice");
				sb.append(" from memberdb m, cart c, product p");
				sb.append(" where m.userid=c.userid and p.p_id = c.p_id and c.userid = '" + userid + "'");
				sb.append(") cart");
				System.out.println(sb.toString());
				
				ps = con.prepareStatement(sb.toString());
				rs = ps.executeQuery();
				
				while(rs.next()) {
					totalPrice = rs.getInt(1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, rs);
		}
			return totalPrice;
	}
	
	// 닫기
	private void closeConnection(Connection con, PreparedStatement ps, Statement st, ResultSet rs) {
		try {
			if(rs!=null) 
				rs.close();
			if(st!=null) 
				st.close();
			if(ps!=null) 
				ps.close();
			if(con!=null) 
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 장바구니에서 해당 상품 삭제
	@Override
	public void deleteProduct(String userid, int productId, int amount) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			String sql = "delete from cart where userid = '" + userid + "' and p_id = " + productId + " and amount = " + amount ;
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, null);
		}
	}
}
