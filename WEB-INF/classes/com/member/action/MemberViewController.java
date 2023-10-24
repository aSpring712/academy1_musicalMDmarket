package com.member.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.SMemberDAO;
import com.member.model.SMemberDAOImpl;
import com.member.model.SMemberDTO;

/**
 * Servlet implementation class MemberViewController
 */
@WebServlet("/member/view")
public class MemberViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션에서 userid 를 구해서 DAO에 있는 findById(userid) 실행
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		SMemberDTO suser = (SMemberDTO) session.getAttribute("user"); // session에 있는 user를 가져옴
		String userid = suser.getUserid();
		SMemberDAO dao = SMemberDAOImpl.getInstance();
		SMemberDTO member = dao.findById(userid);
		request.setAttribute("member", member);
		// 후 memberView.jsp로 이동
		request.getRequestDispatcher("memberView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
