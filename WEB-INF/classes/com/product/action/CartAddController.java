package com.product.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.CartDAO;
import com.product.model.CartDAOImpl;
import com.product.model.CartDTO;

/**
 * Servlet implementation class CartAddController
 */
@WebServlet("/product/addCart")
public class CartAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartAddController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		int productId = Integer.parseInt(request.getParameter("productId"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		
		CartDAO cdao = CartDAOImpl.getInstance();
		CartDTO cart = new CartDTO();
		cart.setAmount(qty);
		cart.setUserid(userid);
		cart.setProduct_id(productId);
		cdao.insert(cart); // 장바구니에 추가
		
		response.sendRedirect("myCart"); // 장바구니로 이동
	}

}
