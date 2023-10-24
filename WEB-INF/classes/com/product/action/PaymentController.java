package com.product.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/product/payment")
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 결제 완료했다 치고 -> 결제 내역을 DB(orderlist) 테이블에 저장
		
		
		
		/* 받아올 내용1 : 유저id, 수취인 이름, 수취인 주소, 배송 요청사항, 전화번호 필요, 결제 수단, 결제 금액 */
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String rcvName = request.getParameter("name");
		String rcvAddress = request.getParameter("address");
		String msg = request.getParameter("msg");
		String rcvPhone = request.getParameter("phone");
		String payMethod = request.getParameter("cal_info");
		int productId = Integer.parseInt(request.getParameter("productId"));
		int calPrice = Integer.parseInt(request.getParameter("calPrice"));
		
		/* 받아올 내용2 : 카트 번호 + productId -> 카트 내용 저장 */
		
		// DB에 저장
		/*
		 * OrderDAO odao = OrderDAOImpl.getInstance(); OrderDTO order = new OrderDTO();
		 * order.setUserId(userid); order.setAddress(rcvAddress);
		 * order.setCalPrice(calPrice);
		 * 
		 * 
		 * 
		 * order.setAmount(amount);
		 */
		
		// 해당 유저의 Cart DB에 상품번호, 수량이 일치하는 제품 존재 : 모두 일치 시 -> 해당 유저의 장바구니 비우기 기능 수행
		// 일부만 일치 시 일치하는 상품만 삭제하기
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
