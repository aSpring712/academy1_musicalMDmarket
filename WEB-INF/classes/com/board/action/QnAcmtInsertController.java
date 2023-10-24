package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.model.QnAboardDAO;
import com.board.model.QnAboardDAOImpl;
import com.board.model.QnAcommentDTO;
import com.google.gson.Gson;
import com.member.model.SMemberDTO;

/**
 * Servlet implementation class QnAcmtInsertController
 */
@WebServlet("/board/qnaCmtInsert")
public class QnAcmtInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnAcmtInsertController() {
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
		HttpSession session = request.getSession();
		SMemberDTO user = (SMemberDTO) session.getAttribute("user");
		
		QnAboardDAO qdao = QnAboardDAOImpl.getInstance();
		QnAcommentDTO comment = new QnAcommentDTO();
		comment.setBnum(bnum);
		comment.setMsg(msg);
		comment.setUserid(user.getUserid());
		qdao.commentInsert(comment);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
