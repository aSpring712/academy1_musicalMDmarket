package com.board.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDTO;
import com.board.model.QnAboardDAO;
import com.board.model.QnAboardDAOImpl;
import com.board.model.QnAboardDTO;
import com.board.model.SBoardDAO;
import com.board.model.SBoardDAOImpl;

/**
 * Servlet implementation class QnAUpdateController
 */
@WebServlet("/board/qnaUpdate")
public class QnAUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnAUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		QnAboardDAO sdao = QnAboardDAOImpl.getInstance();
		QnAboardDTO board = sdao.findByNum(num); // 상세보기
		request.setAttribute("board", board);// 저장
		request.getRequestDispatcher("QnAupdate.jsp").forward(request, response);// 보냄
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		QnAboardDTO board = new QnAboardDTO();
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setContent(request.getParameter("content"));
		board.setEmail(request.getParameter("email"));
		board.setSubject(request.getParameter("subject"));
		board.setUserid(request.getParameter("userid"));
		QnAboardDAO sdao = QnAboardDAOImpl.getInstance();
		sdao.boardUpdate(board);
		response.sendRedirect("qnaDetail?num="+board.getNum()); // 다시 상세보기로 이동
	}

}
