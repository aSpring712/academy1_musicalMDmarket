package com.product.action;
// 전체보기
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.Product;
import com.product.model.ProductDAO;
import com.product.model.ProductDAOImpl;

/**
 * Servlet implementation class ProductListController
 */
@WebServlet("/product/plist")
public class ProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String category = request.getParameter("category");
		ProductDAO pdao = ProductDAOImpl.getInstance(); // DB에 있는 값 받아오기 위해
		ArrayList<Product> products = pdao.productAllfind(category); // 전체보기 함수 호출
		request.setAttribute("products", products); // products 값을 "products"라는 이름으로 저장
		request.getRequestDispatcher("productList.jsp").forward(request, response); // 저장한 값을 가지고 전체보기 페이지로 이동
		// 제품명 pname, 제품 설명 description만 화면에 출력해주기
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
