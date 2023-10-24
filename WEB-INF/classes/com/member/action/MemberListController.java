package com.member.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.SMemberDAO;
import com.member.model.SMemberDAOImpl;
import com.member.model.SMemberDTO;

/**
 * Servlet implementation class MemberListController
 */
@WebServlet("/member/memberlist")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 관리자로 로그인 시 회원 전체 목록을 보는 페이지 */
		request.setCharacterEncoding("utf-8");
		SMemberDAO dao = SMemberDAOImpl.getInstance();
		ArrayList<SMemberDTO> members = dao.getMember(); // 회원 전체 목록
		int count = dao.memberCount(); // 회원 수
		// members, count를 저장해서 -> 가지고 가야함
		request.setAttribute("members", members); // member라는 이름으로 member 저장
		request.setAttribute("count", count); // 저장
		request.getRequestDispatcher("memberlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
