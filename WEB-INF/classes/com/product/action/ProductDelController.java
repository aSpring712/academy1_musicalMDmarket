package com.product.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.product.model.Product;
import com.product.model.ProductDAO;
import com.product.model.ProductDAOImpl;

/**
 * Servlet implementation class ProductDelController
 */
@WebServlet("/product/pDelete")
public class ProductDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 상품 리스트에서 삭제 클릭 시 상품 삭제
		request.setCharacterEncoding("utf-8");
		Long productId = Long.parseLong(request.getParameter("productId"));
		ProductDAO dao = ProductDAOImpl.getInstance();
		dao.productDelete(productId); // 삭제
		ArrayList<Product> arr = dao.productAllfind(); // 삭제 후 전체멤버 조회한 결과를 배열에 담음
		
		// json 출력해야 함
		JSONArray jarr = new JSONArray();
		for(Product p : arr) {
			JSONObject obj = new JSONObject();
			obj.put("category", p.getCategory());
			obj.put("pname", p.getPname());
			obj.put("productId", p.getProductId());
			obj.put("unitPrice", p.getUnitPrice());
			obj.put("manufacturer", p.getManufacturer());
			obj.put("unitsInStock", p.getUnitsInStock());
			jarr.add(obj);
		}
		// json 출력
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jarr.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
