package com.product.action;
// 추가
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.product.model.Product;
import com.product.model.ProductDAO;
import com.product.model.ProductDAOImpl;

/**
 * Servlet implementation class ProductInsertController
 */
@WebServlet("/product/pinsert")
public class ProductInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// header에서 상품등록 클릭 시 addProduct.jsp로 이동
		request.getRequestDispatcher("addProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 	
		// product.setFilename(request.getParameter("productImage")); multipart는 이렇게 가져올 수 없음
		String savePath = "upload"; // 저장될 파일위 위치(폴더)
		int uploadFileSizeLimit = 5*1024*1024; // 최대 5mb로 제한
		String encType="UTF-8";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("서버상의 실제 디렉토리 : " + uploadFilePath);
		
		MultipartRequest multi = new MultipartRequest(
				request, // request 객체
				uploadFilePath, // 서버상의 실제 디렉토리 
				uploadFileSizeLimit, // 최대 업로드 파일 크기 
				encType, // 인코딩 방법 
				new DefaultFileRenamePolicy()); // 동일한 이름 존재할 때 새로운 이름 부여됨
		// 업로드한 파일 이름
		String fileName = multi.getFilesystemName("productImage");
		if(fileName == null) fileName = "";
		System.out.println("파일명 : " + fileName);
		
		ProductDAO pdao = ProductDAOImpl.getInstance();
		Product product = new Product();
		product.setPname(multi.getParameter("name"));
		product.setUnitPrice(Integer.parseInt(multi.getParameter("unitPrice")));
		product.setDescription(multi.getParameter("description"));
		product.setManufacturer(multi.getParameter("manufacturer"));
		product.setCategory(multi.getParameter("category"));
		product.setUnitsInStock(Long.parseLong(multi.getParameter("unitsInStock")));
		product.setFilename(fileName);
		pdao.productInsert(product);
		// 전체보기 페이지로 이동시킴 -> get 방식 : 아무런 값도 가지고오지 않음
		response.sendRedirect("pAdminlist");
	}
}
