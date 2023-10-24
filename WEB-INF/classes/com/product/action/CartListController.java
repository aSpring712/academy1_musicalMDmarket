package com.product.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.SMemberDTO;
import com.product.model.CartDAO;
import com.product.model.CartDAOImpl;
import com.product.model.CartDTO;

/**
 * Servlet implementation class CartListController
 */
@WebServlet("/product/myCart")
public class CartListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("utf-8");
		// 세션에서 userid를 구해서 DAO를 통해 listCart(userid) 실행 -> 해당 회원의 장바구니 목록 조회
		HttpSession session = request.getSession();
		SMemberDTO suser = (SMemberDTO) session.getAttribute("user"); // 로그인 시 저장한 user 가져옴
		String userid = suser.getUserid();
		CartDAO cdao = CartDAOImpl.getInstance();
		ArrayList<CartDTO> carts = cdao.listCart(userid); // 해당 user의 장바구니 목록 조회하는 sql문 실행 -> 결과값을 carts라는 이름으로 담음
		int totalPrice = cdao.totalPrice(userid);
		int calPrice = totalPrice >= 30000 ? totalPrice : totalPrice+3000;
		String shipping = totalPrice >= 30000 ? "배송비 무료" : "3,000원" ;
		request.setAttribute("carts", carts); // 저장
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("shipping", shipping);
		request.setAttribute("calPrice", calPrice);
		request.setAttribute("userid", userid);
		request.getRequestDispatcher("myCart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
