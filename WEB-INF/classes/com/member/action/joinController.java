package com.member.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.SMemberDAO;
import com.member.model.SMemberDAOImpl;
import com.member.model.SMemberDTO;
import com.member.util.SHA256;

/**
 * Servlet implementation class joinController
 */
@WebServlet("/member/join")
public class joinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public joinController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("join.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		SMemberDTO member = new SMemberDTO();
		member.setAdmin(Integer.parseInt(request.getParameter("admin")));
		member.setEmail(request.getParameter("email"));
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		member.setZipcode(request.getParameter("zipcode"));
		member.setAddress(request.getParameter("address"));
		
		String userid = request.getParameter("userid");
		String encPwd = SHA256.getEncrypt(request.getParameter("pwd"), userid); // 16진수로 변화시켜서
		// member.setPwd(request.getParameter("pwd")); -> 암호화 시켜서 넣을 예정 : SHA256.java
		member.setPwd(encPwd); // 암호화된 비번
		member.setUserid(userid);
		
		SMemberDAO dao = SMemberDAOImpl.getInstance();
		dao.memberJoin(member);
		response.sendRedirect("login"); // get 방식
	}

}
