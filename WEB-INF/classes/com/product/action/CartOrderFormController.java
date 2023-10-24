package com.product.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.SMemberDAO;
import com.member.model.SMemberDAOImpl;
import com.member.model.SMemberDTO;
import com.product.model.CartDAO;
import com.product.model.CartDAOImpl;
import com.product.model.CartDTO;
import com.product.model.Product;
import com.product.model.ProductDAO;
import com.product.model.ProductDAOImpl;

/**
 * Servlet implementation class CartPaymentController
 */
@WebServlet("/product/orderform")
public class CartOrderFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartOrderFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		SMemberDTO suser = (SMemberDTO) session.getAttribute("user");		
		String userid = suser.getUserid();
		String shipping = "배송비 3,000원 포함";
		
		int totalPrice = request.getParameter("totalPrice") == null ? 0 : Integer.parseInt(request.getParameter("totalPrice")); // 배송비 계산 안된 금액
		int calPrice = 0; // 배송비 계산된 금액
		
		if(totalPrice == 0) { // 값 없이 넘어온다 -> 개별 상품 구매하기에서 넘어온 것
			// 상품 id, 수량을 받아오기
			Long productId = Long.parseLong(request.getParameter("productId")); 
			int qty = Integer.parseInt(request.getParameter("qty"));
			
			// table에 상품명, 가격, 수량 뿌려주기 -> 상품명, 가격은 Product에 있음
			ProductDAO pdao = ProductDAOImpl.getInstance();
			Product product = pdao.findById(productId);
			String product_name = product.getPname(); // 해당 상품 이름
			int unitPrice = product.getUnitPrice(); // 해당 상품 개당 가격
			totalPrice = unitPrice * qty; // 총 결제할 가격(배송비 고려 X)
			request.setAttribute("unitPrice", unitPrice);
			request.setAttribute("product_name", product_name);
			request.setAttribute("qty", qty);
		} else { // 총 금액을 달고온다 ? 장바구니에서 구매하기로 넘어온 것 -> 장바구니에 담긴 상품을 그대로 주문하기 폼에 뿌려주기
			// id를 통해 장바구니에 있는 내용 가져오기
			CartDAO cdao = CartDAOImpl.getInstance();
			ArrayList<CartDTO> carts = cdao.listCart(userid); // 장바구니에 담긴 상품 목록 받아옴
			request.setAttribute("orders", carts); // 상품 목록을 이제는 orders라는 이름으로 저장
		}
		if(totalPrice >= 30000) {
			shipping = "배송비 무료";
			calPrice = totalPrice;
		} else {
			calPrice = totalPrice + 3000; // 배송비 합친 금액
		}
		request.setAttribute("shipping", shipping); // 변경된 배송 메시지 저장
		request.setAttribute("calPrice", calPrice); // 배송비 합친 최종 결제 금액
		// 회원 정보 전달
		SMemberDAO dao = SMemberDAOImpl.getInstance();
		SMemberDTO member = dao.findById(userid);
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("orderForm.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
