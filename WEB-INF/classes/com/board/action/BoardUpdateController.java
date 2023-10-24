package com.board.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDTO;
import com.board.model.SBoardDAO;
import com.board.model.SBoardDAOImpl;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/board/boardupdate")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		SBoardDAO sdao = SBoardDAOImpl.getInstance();
		BoardDTO board = sdao.findByNum(num); // 상세보기
		request.setAttribute("board", board);// 저장
		request.getRequestDispatcher("boardUpdate.jsp").forward(request, response);// 보냄
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// jsp에서 넘겨주는 값을 받아서 DAO를 부름
		request.setCharacterEncoding("utf-8");
		BoardDTO board = new BoardDTO();
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setContent(request.getParameter("content"));
		board.setEmail(request.getParameter("email"));
		board.setSubject(request.getParameter("subject"));
		board.setUserid(request.getParameter("userid"));
		SBoardDAO sdao = SBoardDAOImpl.getInstance();
		sdao.boardUpdate(board);
		// response.sendRedirect("boardlist"); // 전체 글 목록으로 이동
		// 상세보기로 가고싶은 경우 번호 받아서 가야 함
		response.sendRedirect("boardDetail?num="+board.getNum()); // 다시 상세보기로 이동
	}

}
