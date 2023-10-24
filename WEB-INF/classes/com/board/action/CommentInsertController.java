package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.model.CommentDTO;
import com.board.model.SBoardDAO;
import com.board.model.SBoardDAOImpl;
import com.member.model.SMemberDTO;

/**
 * Servlet implementation class CommentInsertController
 */
@WebServlet("/board/commentInsert")
public class CommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String msg = request.getParameter("msg");
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		// 로그인을 하고 insert가 잘되면 그 내용을 들고 다시 뿌려주고 -> Session
		HttpSession session = request.getSession(); // Session 객체를 구해옴
		SMemberDTO user = (SMemberDTO) session.getAttribute("user");
		// 로그인을 안했으면 로그인하라고 알려줘야 함
		if(user == null) { // 로그인 안 됨
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println("1");
		} else { // 로그인 됨
			SBoardDAO sdao = SBoardDAOImpl.getInstance();
			CommentDTO comment = new CommentDTO();
			comment.setBnum(bnum); // 위에서 구해온 bnum, msg. 날짜 등은 자동으로 입력됨
			comment.setMsg(msg);
			comment.setUserid(user.getUserid()); // userid는 session user에 들어있음
			sdao.commentInsert(comment);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
