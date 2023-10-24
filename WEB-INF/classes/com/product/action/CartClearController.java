package com.product.action;

import java.io.IOException;
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
 * Servlet implementation class CartClearController
 */
@WebServlet("/product/clearCart")
public class CartClearController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartClearController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("utf-8");
		HttpSession session = request.getSession();
		SMemberDTO suser = (SMemberDTO) session.getAttribute("user");
		String userid = suser.getUserid();
		CartDAO cdao = CartDAOImpl.getInstance();
		cdao.deleteAll(userid);
		response.sendRedirect("myCart");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
