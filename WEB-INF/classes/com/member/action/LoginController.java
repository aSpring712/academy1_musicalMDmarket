package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.SMemberDAO;
import com.member.model.SMemberDAOImpl;
import com.member.model.SMemberDTO;
import com.member.util.SHA256;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response); // Dispatcher -> login.jsp로 가라, forward 값을 가지고 가라
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		SMemberDAO dao = SMemberDAOImpl.getInstance();
		String encPwd = SHA256.getEncrypt(pwd, userid); // 비번 암호화
		SMemberDTO member = dao.memberLoginCheck(userid, encPwd); // 암호화된 비번으로 일치하는지 확인
		
		int flag = member.getAdmin(); 
		if(flag == 0 || flag == 1) { // flag가 0(일반회원) 또는 1(관리자) 일 때 session을 넣어서 로그인 상태 유지
			HttpSession session = request.getSession();
			session.setAttribute("user", member); // member 전체 다를 session에 넣음
		} // 로그인 성공 시 Header의 회원가입 부분이 로그아웃 등으로 바뀌어야 함
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(flag);
	}

}
