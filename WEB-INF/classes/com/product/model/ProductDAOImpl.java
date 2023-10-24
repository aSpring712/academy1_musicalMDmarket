package com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDAOImpl implements ProductDAO {
	// 디비 세팅 ==> 싱글톤
	private static ProductDAOImpl instance = new ProductDAOImpl();
	
	public static ProductDAOImpl getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp"); 
		return ds.getConnection();
	}
	
	// 추가
	@Override
	public void productInsert(Product product) {
		Connection con = null;
		PreparedStatement ps = null;
				
		try {
			con = getConnection();
			String sql = "insert into product values(product_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, product.getPname());
			ps.setInt(2, product.getUnitPrice());
			ps.setString(3, product.getDescription());
			ps.setString(4, product.getCategory());
			ps.setString(5, product.getManufacturer());
			ps.setLong(6, product.getUnitsInStock());
			ps.setString(7, product.getFilename());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
		
	}

	// 전체보기(카테고리별)
	@Override
	public ArrayList<Product> productAllfind(String category) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Product> parr = new ArrayList<>();
		String sql = "";
		

		try {
			if(category == null) {
				con = getConnection();
				sql = "select * from product";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					Product product = new Product();
					product.setProductId(rs.getInt("p_id"));
					product.setPname(rs.getString("p_name"));
					product.setUnitPrice(rs.getInt("p_unitprice"));
					product.setDescription(rs.getString("p_description"));
					product.setCategory(rs.getString("p_category"));
					product.setManufacturer(rs.getString("p_manufacturer"));
					product.setUnitsInStock(rs.getLong("p_unitsinstock"));
					product.setFilename(rs.getString("p_filename"));
					parr.add(product);
				}
			} else {
				con = getConnection();
				sql = "select * from product where p_category = '" + category + "'";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					Product product = new Product();
					product.setProductId(rs.getInt("p_id"));
					product.setPname(rs.getString("p_name"));
					product.setUnitPrice(rs.getInt("p_unitprice"));
					product.setDescription(rs.getString("p_description"));
					product.setCategory(rs.getString("p_category"));
					product.setManufacturer(rs.getString("p_manufacturer"));
					product.setUnitsInStock(rs.getLong("p_unitsinstock"));
					product.setFilename(rs.getString("p_filename"));
					parr.add(product);
				}
			}
			System.out.println(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, rs);
		}
		return parr;
		}

	// 전체보기
	@Override
	public ArrayList<Product> productAllfind() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Product> parr = new ArrayList<>();
		String sql = "";
		
		try {
				con = getConnection();
				sql = "select * from product";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					Product product = new Product();
					product.setProductId(rs.getInt("p_id"));
					product.setPname(rs.getString("p_name"));
					product.setUnitPrice(rs.getInt("p_unitprice"));
					product.setDescription(rs.getString("p_description"));
					product.setCategory(rs.getString("p_category"));
					product.setManufacturer(rs.getString("p_manufacturer"));
					product.setUnitsInStock(rs.getLong("p_unitsinstock"));
					product.setFilename(rs.getString("p_filename"));
					parr.add(product);
				}
			System.out.println(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, rs);
		}
		return parr;
	}
	
	// 상세보기
	@Override
	public Product findById(Long productId) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Product product = null;
		
		try {
			con = getConnection();
			String sql = "select * from product where p_id = " + productId;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				product = new Product();
				product.setProductId(rs.getInt("p_id"));
				product.setPname(rs.getString("p_name"));
				product.setUnitPrice(rs.getInt("p_unitprice"));
				product.setDescription(rs.getString("p_description"));
				product.setCategory(rs.getString("p_category"));
				product.setManufacturer(rs.getString("p_manufacturer"));
				product.setUnitsInStock(rs.getLong("p_unitsinstock"));
				product.setFilename(rs.getString("p_filename"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return product;
	}
	
	// 상품 수정
	@Override
	public void productUpdate(Product product) {
		Connection con = null;
		PreparedStatement ps = null;
				
		try {
			con = getConnection();
			String sql = "update product set p_name=?, p_unitprice=?, p_description=?, p_category=?, p_manufacturer=?, p_unitsinstock=?, p_filename=? where p_id=?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, product.getPname());
			ps.setInt(2, product.getUnitPrice());
			ps.setString(3, product.getDescription());
			ps.setString(4, product.getCategory());
			ps.setString(5, product.getManufacturer());
			ps.setLong(6, product.getUnitsInStock());
			ps.setString(7, product.getFilename());
			ps.setLong(8, product.getProductId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
	}

	// 상품 삭제
	@Override
	public void productDelete(Long productId) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			String sql = "delete from product where p_id = '" + productId + "'";
			st = con.createStatement();
			st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, null);
		}
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

	@Override
	public ArrayList<Product> productSearch(String word) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Product> parr = new ArrayList<>();
		String sql = "";
		
		try {
				con = getConnection();
				sql = "select * from product where p_name like '%" + word + "%'";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					Product product = new Product();
					product.setProductId(rs.getInt("p_id"));
					product.setPname(rs.getString("p_name"));
					product.setUnitPrice(rs.getInt("p_unitprice"));
					product.setDescription(rs.getString("p_description"));
					product.setCategory(rs.getString("p_category"));
					product.setManufacturer(rs.getString("p_manufacturer"));
					product.setUnitsInStock(rs.getLong("p_unitsinstock"));
					product.setFilename(rs.getString("p_filename"));
					parr.add(product);
				}
			System.out.println(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, rs);
		}
		return parr;
	}
}
