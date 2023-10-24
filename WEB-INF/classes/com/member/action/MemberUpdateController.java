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
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/member/update")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
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
		// 수정 후 로그인으로 감
		request.setCharacterEncoding("utf-8");
		SMemberDTO member = new SMemberDTO(); // member 객체 받음
		
		member.setName(request.getParameter("name"));// member에 집어넣음
		member.setEmail(request.getParameter("email"));
		member.setPhone(request.getParameter("phone"));
		member.setZipcode(request.getParameter("zipcode"));
		member.setAddress(request.getParameter("address"));
		String userid = request.getParameter("userid"); // 받아 옴
		String pwd = request.getParameter("pwd");
		String encPwd = SHA256.getEncrypt(pwd, userid);
		member.setPwd(encPwd); // 암호화된 비번으로 집어넣음
		member.setUserid(userid);
		SMemberDAO dao = SMemberDAOImpl.getInstance();
		dao.memberUpdate(member);
		response.sendRedirect("login");
	}

}
