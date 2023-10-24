package com.board.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.QnAboardDAO;
import com.board.model.QnAboardDAOImpl;
import com.board.model.QnAboardDTO;

/**
 * Servlet implementation class QnAInsertController
 */
@WebServlet("/board/qnaInsert")
public class QnAInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnAInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("QnAwrite.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		QnAboardDTO board = new QnAboardDTO();
		board.setEmail(request.getParameter("email"));
		board.setSubject(request.getParameter("subject"));
		board.setUserid(request.getParameter("userid"));
		board.setContent(request.getParameter("content"));
		QnAboardDAO qdao = QnAboardDAOImpl.getInstance();
		qdao.boardInsert(board);
		response.sendRedirect("qnaList");
	}

}
